package no.fintlabs.consumer.model.bilag;

import no.fint.model.resource.okonomi.regnskap.BilagResource;
import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class BilagRequestKafkaConsumer extends EventRequestKafkaConsumer<BilagResource> {
    public BilagRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, BilagConfig bilagConfig) {
        super(eventConsumerFactoryService, bilagConfig);
    }
}
