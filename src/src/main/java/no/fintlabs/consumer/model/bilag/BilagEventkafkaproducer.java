package no.fintlabs.consumer.model.bilag;

import no.fintlabs.core.consumer.shared.resource.kafka.EventKafkaProducer;
import no.fintlabs.kafka.event.EventProducerFactory;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class BilagEventKafkaProducer extends EventKafkaProducer {
    public BilagEventKafkaProducer(EventProducerFactory eventProducerFactory, BilagConfig bilagConfig, EventTopicService eventTopicService) {
        super(eventProducerFactory, bilagConfig, eventTopicService);
    }
}
