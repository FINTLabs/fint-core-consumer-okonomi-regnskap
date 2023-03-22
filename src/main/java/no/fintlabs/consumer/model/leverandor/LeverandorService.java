package no.fintlabs.consumer.model.leverandor;

import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.okonomi.regnskap.LeverandorResource;
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
public class LeverandorService extends CacheService<LeverandorResource> {

    private final LeverandorKafkaConsumer leverandorKafkaConsumer;

    private final LeverandorLinker linker;

    public LeverandorService(
            LeverandorConfig leverandorConfig,
            CacheManager cacheManager,
            LeverandorKafkaConsumer leverandorKafkaConsumer,
            LeverandorLinker linker) {
        super(leverandorConfig, cacheManager, leverandorKafkaConsumer);
        this.leverandorKafkaConsumer = leverandorKafkaConsumer;
        this.linker = linker;
    }

    @Override
    protected Cache<LeverandorResource> initializeCache(CacheManager cacheManager, ConsumerConfig<LeverandorResource> consumerConfig, String s) {
        return cacheManager.create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        long retension = leverandorKafkaConsumer.registerListener(LeverandorResource.class, this::addResourceToCache);
        getCache().setRetentionPeriodInMs(retension);
    }

    private void addResourceToCache(ConsumerRecord<String, LeverandorResource> consumerRecord) {
        this.eventLogger.logDataRecieved();
        if (consumerRecord.value() == null) {
            getCache().remove(consumerRecord.key());
        } else {
            LeverandorResource leverandorResource = consumerRecord.value();
            linker.mapLinks(leverandorResource);
            getCache().put(consumerRecord.key(), leverandorResource, linker.hashCodes(leverandorResource));
        }
    }

    @Override
    public Optional<LeverandorResource> getBySystemId(String systemId) {
        return getCache().getLastUpdatedByFilter(systemId.hashCode(),
                resource -> Optional
                        .ofNullable(resource)
                        .map(LeverandorResource::getSystemId)
                        .map(Identifikator::getIdentifikatorverdi)
                        .map(systemId::equals)
                        .orElse(false));
    }
}