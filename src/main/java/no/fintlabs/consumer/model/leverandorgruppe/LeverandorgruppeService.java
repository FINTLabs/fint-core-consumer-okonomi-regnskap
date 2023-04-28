package no.fintlabs.consumer.model.leverandorgruppe;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.okonomi.regnskap.LeverandorgruppeResource;
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
public class LeverandorgruppeService extends CacheService<LeverandorgruppeResource> {

    private final EntityKafkaConsumer<LeverandorgruppeResource> entityKafkaConsumer;

    private final LeverandorgruppeLinker linker;

    private final LeverandorgruppeResponseKafkaConsumer leverandorgruppeResponseKafkaConsumer;

    public LeverandorgruppeService(
            LeverandorgruppeConfig consumerConfig,
            CacheManager cacheManager,
            LeverandorgruppeEntityKafkaConsumer entityKafkaConsumer,
            LeverandorgruppeLinker linker, LeverandorgruppeResponseKafkaConsumer leverandorgruppeResponseKafkaConsumer) {
        super(consumerConfig, cacheManager, entityKafkaConsumer);
        this.entityKafkaConsumer = entityKafkaConsumer;
        this.linker = linker;
        this.leverandorgruppeResponseKafkaConsumer = leverandorgruppeResponseKafkaConsumer;
    }

    @Override
    protected Cache<LeverandorgruppeResource> initializeCache(CacheManager cacheManager, ConsumerConfig<LeverandorgruppeResource> consumerConfig, String s) {
        return cacheManager.create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        long retention = entityKafkaConsumer.registerListener(LeverandorgruppeResource.class, this::addResourceToCache);
        getCache().setRetentionPeriodInMs(retention);
    }

    private void addResourceToCache(ConsumerRecord<String, LeverandorgruppeResource> consumerRecord) {
        this.eventLogger.logDataRecieved();
        LeverandorgruppeResource resource = consumerRecord.value();
        if (resource == null) {
            getCache().remove(consumerRecord.key());
        } else {
            linker.mapLinks(resource);
            this.getCache().put(consumerRecord.key(), resource, linker.hashCodes(resource));
            if (consumerRecord.headers().lastHeader("event-corr-id") != null){
                String corrId = new String(consumerRecord.headers().lastHeader("event-corr-id").value(), StandardCharsets.UTF_8);
                log.debug("Adding corrId to EntityResponseCache: {}", corrId);
                leverandorgruppeResponseKafkaConsumer.getEntityCache().add(corrId, resource);
            }
        }
    }

    @Override
    public Optional<LeverandorgruppeResource> getBySystemId(String systemId) {
        return getCache().getLastUpdatedByFilter(systemId.hashCode(),
                (resource) -> Optional
                        .ofNullable(resource)
                        .map(LeverandorgruppeResource::getSystemId)
                        .map(Identifikator::getIdentifikatorverdi)
                        .map(systemId::equals)
                        .orElse(false)
        );
    }
}
