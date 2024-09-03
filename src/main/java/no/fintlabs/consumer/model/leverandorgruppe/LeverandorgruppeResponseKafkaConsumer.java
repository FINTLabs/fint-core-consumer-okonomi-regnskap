package no.fintlabs.consumer.model.leverandorgruppe;

import no.fint.model.resource.okonomi.regnskap.LeverandorgruppeResource;
import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class LeverandorgruppeResponseKafkaConsumer extends EventResponseKafkaConsumer<LeverandorgruppeResource> {

    public LeverandorgruppeResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, LeverandorgruppeConfig leverandorgruppeConfig, LeverandorgruppeLinker leverandorgruppeLinker, EventTopicService eventTopicService) {
        super(eventConsumerFactoryService, leverandorgruppeConfig, leverandorgruppeLinker, eventTopicService);
    }

}
