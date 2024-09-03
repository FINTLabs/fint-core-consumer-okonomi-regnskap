package no.fintlabs.consumer.model.leverandor;

import no.fint.model.resource.okonomi.regnskap.LeverandorResource;
import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class LeverandorRequestKafkaConsumer extends EventRequestKafkaConsumer<LeverandorResource> {
    public LeverandorRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, LeverandorConfig leverandorConfig, EventTopicService eventTopicService) {
        super(eventConsumerFactoryService, leverandorConfig, eventTopicService);
    }
}
