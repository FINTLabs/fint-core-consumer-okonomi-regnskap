package no.fintlabs.consumer.model.leverandorgruppe;

import no.fintlabs.core.consumer.shared.resource.kafka.EventKafkaProducer;
import no.fintlabs.kafka.event.EventProducerFactory;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class LeverandorgruppeEventKafkaProducer extends EventKafkaProducer {
    public LeverandorgruppeEventKafkaProducer(EventProducerFactory eventProducerFactory, LeverandorgruppeConfig leverandorgruppeConfig, EventTopicService eventTopicService) {
        super(eventProducerFactory, leverandorgruppeConfig, eventTopicService);
    }
}
