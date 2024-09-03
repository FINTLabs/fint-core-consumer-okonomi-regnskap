package no.fintlabs.consumer.model.leverandor;

import no.fint.model.resource.okonomi.regnskap.LeverandorResource;
import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class LeverandorResponseKafkaConsumer extends EventResponseKafkaConsumer<LeverandorResource> {

    public LeverandorResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, LeverandorConfig leverandorConfig, LeverandorLinker leverandorLinker, EventTopicService eventTopicService) {
        super(eventConsumerFactoryService, leverandorConfig, leverandorLinker, eventTopicService);
    }

}
