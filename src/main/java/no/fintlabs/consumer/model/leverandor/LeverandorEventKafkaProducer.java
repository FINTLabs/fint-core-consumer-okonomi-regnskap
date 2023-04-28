package no.fintlabs.consumer.model.leverandor;

import no.fintlabs.core.consumer.shared.resource.kafka.EventKafkaProducer;
import no.fintlabs.kafka.event.EventProducerFactory;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class LeverandorEventKafkaProducer extends EventKafkaProducer {
    public LeverandorEventKafkaProducer(EventProducerFactory eventProducerFactory, LeverandorConfig leverandorConfig, EventTopicService eventTopicService) {
        super(eventProducerFactory, leverandorConfig, eventTopicService);
    }
}
