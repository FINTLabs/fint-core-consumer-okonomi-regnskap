package no.fintlabs.consumer.model.transaksjon;

import no.fint.model.resource.okonomi.regnskap.TransaksjonResource;
import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class TransaksjonRequestKafkaConsumer extends EventRequestKafkaConsumer<TransaksjonResource> {
    public TransaksjonRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, TransaksjonConfig transaksjonConfig, EventTopicService eventTopicService) {
        super(eventConsumerFactoryService, transaksjonConfig, eventTopicService);
    }
}
