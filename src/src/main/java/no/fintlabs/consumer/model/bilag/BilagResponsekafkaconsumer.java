package no.fintlabs.consumer.model.bilag;

import no.fint.model.resource.okonomi.regnskap.BilagResource;
import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class BilagResponseKafkaConsumer extends EventResponseKafkaConsumer<BilagResource> {

    public BilagResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, BilagConfig bilagConfig, BilagLinker bilagLinker) {
        super(eventConsumerFactoryService, bilagConfig, bilagLinker);
    }

}
