package no.fintlabs.consumer.model.person;

import no.fint.model.resource.felles.PersonResource;
import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class PersonRequestKafkaConsumer extends EventRequestKafkaConsumer<PersonResource> {
    public PersonRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, PersonConfig personConfig, EventTopicService eventTopicService) {
        super(eventConsumerFactoryService, personConfig, eventTopicService);
    }
}
