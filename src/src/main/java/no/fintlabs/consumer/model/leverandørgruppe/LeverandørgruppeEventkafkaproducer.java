package no.fintlabs.consumer.model.leverandørgruppe;

import no.fintlabs.core.consumer.shared.resource.kafka.EventKafkaProducer;
import no.fintlabs.kafka.event.EventProducerFactory;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class LeverandørgruppeEventKafkaProducer extends EventKafkaProducer {
    public LeverandørgruppeEventKafkaProducer(EventProducerFactory eventProducerFactory, LeverandørgruppeConfig leverandørgruppeConfig, EventTopicService eventTopicService) {
        super(eventProducerFactory, leverandørgruppeConfig, eventTopicService);
    }
}
