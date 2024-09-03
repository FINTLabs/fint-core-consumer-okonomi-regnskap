package no.fintlabs.consumer.model.postering;

import no.fint.model.resource.okonomi.regnskap.PosteringResource;
import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class PosteringRequestKafkaConsumer extends EventRequestKafkaConsumer<PosteringResource> {
    public PosteringRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, PosteringConfig posteringConfig, EventTopicService eventTopicService) {
        super(eventConsumerFactoryService, posteringConfig, eventTopicService);
    }
}
