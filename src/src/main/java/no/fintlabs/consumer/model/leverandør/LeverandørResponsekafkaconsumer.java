package no.fintlabs.consumer.model.leverandør;

import no.fint.model.resource.okonomi.regnskap.LeverandørResource;
import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class LeverandørResponseKafkaConsumer extends EventResponseKafkaConsumer<LeverandørResource> {

    public LeverandørResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, LeverandørConfig leverandørConfig, LeverandørLinker leverandørLinker) {
        super(eventConsumerFactoryService, leverandørConfig, leverandørLinker);
    }

}
