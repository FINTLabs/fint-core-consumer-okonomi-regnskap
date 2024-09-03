package no.fintlabs.consumer.model.postering;

import no.fint.model.resource.okonomi.regnskap.PosteringResource;
import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class PosteringResponseKafkaConsumer extends EventResponseKafkaConsumer<PosteringResource> {

    public PosteringResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, PosteringConfig posteringConfig, PosteringLinker posteringLinker, EventTopicService eventTopicService) {
        super(eventConsumerFactoryService, posteringConfig, posteringLinker, eventTopicService);
    }

}
