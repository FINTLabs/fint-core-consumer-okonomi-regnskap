package no.fintlabs.consumer.model.leverandor;

import no.fint.model.resource.okonomi.regnskap.LeverandorResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class LeverandorEntityKafkaConsumer extends EntityKafkaConsumer<LeverandorResource> {

    public LeverandorEntityKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            LeverandorConfig leverandorConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, leverandorConfig);
    }
}
