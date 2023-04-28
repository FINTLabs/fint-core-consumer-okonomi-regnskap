package no.fintlabs.consumer.model.leverandørgruppe;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.okonomi.regnskap.LeverandørgruppeResource;
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
public class LeverandørgruppeService extends CacheService<LeverandørgruppeResource> {

    private final EntityKafkaConsumer<LeverandørgruppeResource> entityKafkaConsumer;

    private final LeverandørgruppeLinker linker;

    private final LeverandørgruppeResponseKafkaConsumer leverandørgruppeResponseKafkaConsumer;

    public LeverandørgruppeService(
            LeverandørgruppeConfig consumerConfig,
            CacheManager cacheManager,
            LeverandørgruppeEntityKafkaConsumer entityKafkaConsumer,
            LeverandørgruppeLinker linker, LeverandørgruppeResponseKafkaConsumer leverandørgruppeResponseKafkaConsumer) {
        super(consumerConfig, cacheManager, entityKafkaConsumer);
        this.entityKafkaConsumer = entityKafkaConsumer;
        this.linker = linker;
        this.leverandørgruppeResponseKafkaConsumer = leverandørgruppeResponseKafkaConsumer;
    }

    @Override
    protected Cache<LeverandørgruppeResource> initializeCache(CacheManager cacheManager, ConsumerConfig<LeverandørgruppeResource> consumerConfig, String s) {
        return cacheManager.create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        long retention = entityKafkaConsumer.registerListener(LeverandørgruppeResource.class, this::addResourceToCache);
        getCache().setRetentionPeriodInMs(retention);
    }

    private void addResourceToCache(ConsumerRecord<String, LeverandørgruppeResource> consumerRecord) {
        this.eventLogger.logDataRecieved();
        LeverandørgruppeResource resource = consumerRecord.value();
        if (resource == null) {
            getCache().remove(consumerRecord.key());
        } else {
            linker.mapLinks(resource);
            this.getCache().put(consumerRecord.key(), resource, linker.hashCodes(resource));
            if (consumerRecord.headers().lastHeader("event-corr-id") != null){
                String corrId = new String(consumerRecord.headers().lastHeader("event-corr-id").value(), StandardCharsets.UTF_8);
                log.debug("Adding corrId to EntityResponseCache: {}", corrId);
                leverandørgruppeResponseKafkaConsumer.getEntityCache().add(corrId, resource);
            }
        }
    }

    @Override
    public Optional<LeverandørgruppeResource> getBySystemId(String systemId) {
        return getCache().getLastUpdatedByFilter(systemId.hashCode(),
                (resource) -> Optional
                        .ofNullable(resource)
                        .map(LeverandørgruppeResource::getSystemId)
                        .map(Identifikator::getIdentifikatorverdi)
                        .map(systemId::equals)
                        .orElse(false)
        );
    }
}
