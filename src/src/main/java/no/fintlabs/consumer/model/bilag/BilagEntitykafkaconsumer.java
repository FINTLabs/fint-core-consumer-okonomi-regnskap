package no.fintlabs.consumer.model.bilag;

import no.fint.model.resource.okonomi.regnskap.BilagResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class BilagEntityKafkaConsumer extends EntityKafkaConsumer<BilagResource> {

    public BilagEntityKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            BilagConfig bilagConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, bilagConfig);
    }
}
