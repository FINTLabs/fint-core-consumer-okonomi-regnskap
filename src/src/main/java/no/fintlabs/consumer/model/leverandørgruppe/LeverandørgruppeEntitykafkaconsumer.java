package no.fintlabs.consumer.model.leverandørgruppe;

import no.fint.model.resource.okonomi.regnskap.LeverandørgruppeResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class LeverandørgruppeEntityKafkaConsumer extends EntityKafkaConsumer<LeverandørgruppeResource> {

    public LeverandørgruppeEntityKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            LeverandørgruppeConfig leverandørgruppeConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, leverandørgruppeConfig);
    }
}
