package no.fintlabs.consumer.model.leverandørgruppe;

import no.fint.model.resource.okonomi.regnskap.LeverandørgruppeResource;
import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class LeverandørgruppeRequestKafkaConsumer extends EventRequestKafkaConsumer<LeverandørgruppeResource> {
    public LeverandørgruppeRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, LeverandørgruppeConfig leverandørgruppeConfig) {
        super(eventConsumerFactoryService, leverandørgruppeConfig);
    }
}
