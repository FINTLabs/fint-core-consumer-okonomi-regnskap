package no.fintlabs.consumer.model.leverandørgruppe;

import no.fint.model.resource.okonomi.regnskap.LeverandørgruppeResource;
import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class LeverandørgruppeResponseKafkaConsumer extends EventResponseKafkaConsumer<LeverandørgruppeResource> {

    public LeverandørgruppeResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, LeverandørgruppeConfig leverandørgruppeConfig, LeverandørgruppeLinker leverandørgruppeLinker) {
        super(eventConsumerFactoryService, leverandørgruppeConfig, leverandørgruppeLinker);
    }

}
