package no.fintlabs.consumer.model.postering;

import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class PosteringKafkaConsumer extends EntityKafkaConsumer<PosteringResource> {
    public PosteringKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            PosteringConfig posteringConfig) {
        super(entityConsumerFactoryService,
                listenerBeanRegistrationService,
                entityTopicService,
                posteringConfig);
    }
}
