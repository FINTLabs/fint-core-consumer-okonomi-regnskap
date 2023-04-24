package no.fintlabs.consumer.model.leverandorgruppe;

import no.fint.model.resource.okonomi.regnskap.LeverandorgruppeResource;
import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class LeverandorgruppeRequestKafkaConsumer extends EventRequestKafkaConsumer<LeverandorgruppeResource> {
    public LeverandorgruppeRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, LeverandorgruppeConfig leverandorgruppeConfig) {
        super(eventConsumerFactoryService, leverandorgruppeConfig);
    }
}
