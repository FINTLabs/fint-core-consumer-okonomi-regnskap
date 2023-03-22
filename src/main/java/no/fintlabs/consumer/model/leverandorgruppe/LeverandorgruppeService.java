package no.fintlabs.consumer.model.leverandorgruppe;

import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.okonomi.regnskap.LeverandorgruppeResource;
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
public class LeverandorgruppeService extends CacheService<LeverandorgruppeResource> {

    private final LeverandorgruppeKafkaConsumer leverandorgruppeKafkaConsumer;

    private final LeverandorgruppeLinker linker;

    public LeverandorgruppeService(
            LeverandorgruppeConfig leverandorgruppeConfig,
            CacheManager cacheManager,
            LeverandorgruppeKafkaConsumer leverandorgruppeKafkaConsumer,
            LeverandorgruppeLinker linker) {
        super(leverandorgruppeConfig, cacheManager, leverandorgruppeKafkaConsumer);
        this.leverandorgruppeKafkaConsumer = leverandorgruppeKafkaConsumer;
        this.linker = linker;
    }

    @Override
    protected Cache<LeverandorgruppeResource> initializeCache(CacheManager cacheManager, ConsumerConfig<LeverandorgruppeResource> consumerConfig, String s) {
        return cacheManager.create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        long retension = leverandorgruppeKafkaConsumer.registerListener(LeverandorgruppeResource.class, this::addResourceToCache);
        getCache().setRetentionPeriodInMs(retension);
    }

    private void addResourceToCache(ConsumerRecord<String, LeverandorgruppeResource> consumerRecord) {
        this.eventLogger.logDataRecieved();
        if (consumerRecord.value() == null) {
            getCache().remove(consumerRecord.key());
        } else {
            LeverandorgruppeResource leverandorgruppeResource = consumerRecord.value();
            linker.mapLinks(leverandorgruppeResource);
            getCache().put(consumerRecord.key(), leverandorgruppeResource, linker.hashCodes(leverandorgruppeResource));
        }
    }

    @Override
    public Optional<LeverandorgruppeResource> getBySystemId(String systemId) {
        return getCache().getLastUpdatedByFilter(systemId.hashCode(),
                resource -> Optional
                        .ofNullable(resource)
                        .map(LeverandorgruppeResource::getSystemId)
                        .map(Identifikator::getIdentifikatorverdi)
                        .map(systemId::equals)
                        .orElse(false));
    }
}