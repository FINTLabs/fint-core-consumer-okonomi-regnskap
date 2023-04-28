package no.fintlabs.consumer.model.postering;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.okonomi.regnskap.PosteringResource;
import no.fintlabs.cache.Cache;
import no.fintlabs.cache.CacheManager;
import no.fintlabs.cache.packing.PackingTypes;
import no.fintlabs.core.consumer.shared.resource.CacheService;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Service
public class PosteringService extends CacheService<PosteringResource> {

    private final EntityKafkaConsumer<PosteringResource> entityKafkaConsumer;

    private final PosteringLinker linker;

    private final PosteringResponseKafkaConsumer posteringResponseKafkaConsumer;

    public PosteringService(
            PosteringConfig consumerConfig,
            CacheManager cacheManager,
            PosteringEntityKafkaConsumer entityKafkaConsumer,
            PosteringLinker linker, PosteringResponseKafkaConsumer posteringResponseKafkaConsumer) {
        super(consumerConfig, cacheManager, entityKafkaConsumer);
        this.entityKafkaConsumer = entityKafkaConsumer;
        this.linker = linker;
        this.posteringResponseKafkaConsumer = posteringResponseKafkaConsumer;
    }

    @Override
    protected Cache<PosteringResource> initializeCache(CacheManager cacheManager, ConsumerConfig<PosteringResource> consumerConfig, String s) {
        return cacheManager.create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        long retention = entityKafkaConsumer.registerListener(PosteringResource.class, this::addResourceToCache);
        getCache().setRetentionPeriodInMs(retention);
    }

    private void addResourceToCache(ConsumerRecord<String, PosteringResource> consumerRecord) {
        this.eventLogger.logDataRecieved();
        PosteringResource resource = consumerRecord.value();
        if (resource == null) {
            getCache().remove(consumerRecord.key());
        } else {
            linker.mapLinks(resource);
            this.getCache().put(consumerRecord.key(), resource, linker.hashCodes(resource));
            if (consumerRecord.headers().lastHeader("event-corr-id") != null){
                String corrId = new String(consumerRecord.headers().lastHeader("event-corr-id").value(), StandardCharsets.UTF_8);
                log.debug("Adding corrId to EntityResponseCache: {}", corrId);
                posteringResponseKafkaConsumer.getEntityCache().add(corrId, resource);
            }
        }
    }

    @Override
    public Optional<PosteringResource> getBySystemId(String systemId) {
        return getCache().getLastUpdatedByFilter(systemId.hashCode(),
                (resource) -> Optional
                        .ofNullable(resource)
                        .map(PosteringResource::getPosteringsId)
                        .map(Identifikator::getIdentifikatorverdi)
                        .map(systemId::equals)
                        .orElse(false)
        );
    }
}
