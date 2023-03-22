package no.fintlabs.consumer.model.postering;

import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.okonomi.regnskap.PosteringResource;
import no.fintlabs.cache.Cache;
import no.fintlabs.cache.CacheManager;
import no.fintlabs.cache.packing.PackingTypes;
import no.fintlabs.core.consumer.shared.resource.CacheService;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class PosteringService extends CacheService<PosteringResource> {

    private final PosteringKafkaConsumer posteringKafkaConsumer;

    private final PosteringLinker linker;

    public PosteringService(
            PosteringConfig posteringConfig,
            CacheManager cacheManager,
            PosteringKafkaConsumer posteringKafkaConsumer,
            PosteringLinker linker) {
        super(posteringConfig, cacheManager, posteringKafkaConsumer);
        this.posteringKafkaConsumer = posteringKafkaConsumer;
        this.linker = linker;
    }

    @Override
    protected Cache<PosteringResource> initializeCache(CacheManager cacheManager, ConsumerConfig<PosteringResource> consumerConfig, String s) {
        return cacheManager.create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        long retension = posteringKafkaConsumer.registerListener(PosteringResource.class, this::addResourceToCache);
        getCache().setRetentionPeriodInMs(retension);
    }

    private void addResourceToCache(ConsumerRecord<String, PosteringResource> consumerRecord) {
        this.eventLogger.logDataRecieved();
        if (consumerRecord.value() == null) {
            getCache().remove(consumerRecord.key());
        } else {
            PosteringResource posteringResource = consumerRecord.value();
            linker.mapLinks(posteringResource);
            getCache().put(consumerRecord.key(), posteringResource, linker.hashCodes(posteringResource));
        }
    }

    @Override
    public Optional<PosteringResource> getBySystemId(String systemId) {
        return getCache().getLastUpdatedByFilter(systemId.hashCode(),
                resource -> Optional
                        .ofNullable(resource)
                        .map(PosteringResource::getPosteringsId)
                        .map(Identifikator::getIdentifikatorverdi)
                        .map(systemId::equals)
                        .orElse(false));
    }
}