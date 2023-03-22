package no.fintlabs.consumer.model.transaksjon;

import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.okonomi.regnskap.TransaksjonResource;
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
public class TransaksjonService extends CacheService<TransaksjonResource> {

    private final TransaksjonKafkaConsumer transaksjonKafkaConsumer;

    private final TransaksjonLinker linker;

    public TransaksjonService(
            TransaksjonConfig transaksjonConfig,
            CacheManager cacheManager,
            TransaksjonKafkaConsumer transaksjonKafkaConsumer,
            TransaksjonLinker linker) {
        super(transaksjonConfig, cacheManager, transaksjonKafkaConsumer);
        this.transaksjonKafkaConsumer = transaksjonKafkaConsumer;
        this.linker = linker;
    }

    @Override
    protected Cache<TransaksjonResource> initializeCache(CacheManager cacheManager, ConsumerConfig<TransaksjonResource> consumerConfig, String s) {
        return cacheManager.create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        long retension = transaksjonKafkaConsumer.registerListener(TransaksjonResource.class, this::addResourceToCache);
        getCache().setRetentionPeriodInMs(retension);
    }

    private void addResourceToCache(ConsumerRecord<String, TransaksjonResource> consumerRecord) {
        this.eventLogger.logDataRecieved();
        if (consumerRecord.value() == null) {
            getCache().remove(consumerRecord.key());
        } else {
            TransaksjonResource transaksjonResource = consumerRecord.value();
            linker.mapLinks(transaksjonResource);
            getCache().put(consumerRecord.key(), transaksjonResource, linker.hashCodes(transaksjonResource));
        }
    }

    @Override
    public Optional<TransaksjonResource> getBySystemId(String systemId) {
        return getCache().getLastUpdatedByFilter(systemId.hashCode(),
                resource -> Optional
                        .ofNullable(resource)
                        .map(TransaksjonResource::getTransaksjonsId)
                        .map(Identifikator::getIdentifikatorverdi)
                        .map(systemId::equals)
                        .orElse(false));
    }
}