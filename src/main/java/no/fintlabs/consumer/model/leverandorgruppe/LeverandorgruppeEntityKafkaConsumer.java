package no.fintlabs.consumer.model.leverandorgruppe;

import no.fint.model.resource.okonomi.regnskap.LeverandorgruppeResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class LeverandorgruppeEntityKafkaConsumer extends EntityKafkaConsumer<LeverandorgruppeResource> {

    public LeverandorgruppeEntityKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            LeverandorgruppeConfig leverandorgruppeConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, leverandorgruppeConfig);
    }
}
