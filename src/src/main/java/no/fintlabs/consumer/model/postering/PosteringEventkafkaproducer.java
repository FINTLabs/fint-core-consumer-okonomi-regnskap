package no.fintlabs.consumer.model.postering;

import no.fintlabs.core.consumer.shared.resource.kafka.EventKafkaProducer;
import no.fintlabs.kafka.event.EventProducerFactory;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class PosteringEventKafkaProducer extends EventKafkaProducer {
    public PosteringEventKafkaProducer(EventProducerFactory eventProducerFactory, PosteringConfig posteringConfig, EventTopicService eventTopicService) {
        super(eventProducerFactory, posteringConfig, eventTopicService);
    }
}
