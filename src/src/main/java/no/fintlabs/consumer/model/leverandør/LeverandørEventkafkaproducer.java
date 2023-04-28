package no.fintlabs.consumer.model.leverandør;

import no.fintlabs.core.consumer.shared.resource.kafka.EventKafkaProducer;
import no.fintlabs.kafka.event.EventProducerFactory;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class LeverandørEventKafkaProducer extends EventKafkaProducer {
    public LeverandørEventKafkaProducer(EventProducerFactory eventProducerFactory, LeverandørConfig leverandørConfig, EventTopicService eventTopicService) {
        super(eventProducerFactory, leverandørConfig, eventTopicService);
    }
}
