package no.fintlabs.consumer.model.transaksjon;

import no.fintlabs.core.consumer.shared.resource.kafka.EventKafkaProducer;
import no.fintlabs.kafka.event.EventProducerFactory;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class TransaksjonEventKafkaProducer extends EventKafkaProducer {
    public TransaksjonEventKafkaProducer(EventProducerFactory eventProducerFactory, TransaksjonConfig transaksjonConfig, EventTopicService eventTopicService) {
        super(eventProducerFactory, transaksjonConfig, eventTopicService);
    }
}
