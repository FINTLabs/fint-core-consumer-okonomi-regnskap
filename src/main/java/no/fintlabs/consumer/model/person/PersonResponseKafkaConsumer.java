package no.fintlabs.consumer.model.person;

import no.fint.model.resource.felles.PersonResource;
import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class PersonResponseKafkaConsumer extends EventResponseKafkaConsumer<PersonResource> {

    public PersonResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, PersonConfig personConfig, PersonLinker personLinker, EventTopicService eventTopicService) {
        super(eventConsumerFactoryService, personConfig, personLinker, eventTopicService);
    }

}
