package no.fintlabs.consumer.model.postering;

import no.fint.model.resource.okonomi.regnskap.PosteringResource;
import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class PosteringRequestKafkaConsumer extends EventRequestKafkaConsumer<PosteringResource> {
    public PosteringRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, PosteringConfig posteringConfig) {
        super(eventConsumerFactoryService, posteringConfig);
    }
}
