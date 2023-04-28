package no.fintlabs.consumer.model.postering;

import no.fint.model.resource.okonomi.regnskap.PosteringResource;
import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class PosteringResponseKafkaConsumer extends EventResponseKafkaConsumer<PosteringResource> {

    public PosteringResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, PosteringConfig posteringConfig, PosteringLinker posteringLinker) {
        super(eventConsumerFactoryService, posteringConfig, posteringLinker);
    }

}
