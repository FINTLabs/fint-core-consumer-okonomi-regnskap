package no.fintlabs.consumer.model.leverandør;

import no.fint.model.resource.okonomi.regnskap.LeverandørResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class LeverandørEntityKafkaConsumer extends EntityKafkaConsumer<LeverandørResource> {

    public LeverandørEntityKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            LeverandørConfig leverandørConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, leverandørConfig);
    }
}
