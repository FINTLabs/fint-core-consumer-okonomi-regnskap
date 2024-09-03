package no.fintlabs.consumer.model.transaksjon;

import no.fint.model.resource.okonomi.regnskap.TransaksjonResource;
import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class TransaksjonResponseKafkaConsumer extends EventResponseKafkaConsumer<TransaksjonResource> {

    public TransaksjonResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, TransaksjonConfig transaksjonConfig, TransaksjonLinker transaksjonLinker, EventTopicService eventTopicService) {
        super(eventConsumerFactoryService, transaksjonConfig, transaksjonLinker, eventTopicService);
    }

}
