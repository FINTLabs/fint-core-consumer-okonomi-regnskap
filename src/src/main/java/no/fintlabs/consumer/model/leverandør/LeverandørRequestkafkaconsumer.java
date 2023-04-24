package no.fintlabs.consumer.model.leverandør;

import no.fint.model.resource.okonomi.regnskap.LeverandørResource;
import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class LeverandørRequestKafkaConsumer extends EventRequestKafkaConsumer<LeverandørResource> {
    public LeverandørRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, LeverandørConfig leverandørConfig) {
        super(eventConsumerFactoryService, leverandørConfig);
    }
}
