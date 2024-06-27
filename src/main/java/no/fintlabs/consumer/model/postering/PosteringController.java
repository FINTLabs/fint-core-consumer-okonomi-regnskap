package no.fintlabs.consumer.model.postering;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import no.fint.antlr.FintFilterService;
import no.fint.model.resource.felles.PersonResource;
import no.fint.model.resource.okonomi.regnskap.PosteringResource;
import no.fint.relations.FintRelationsMediaType;
import no.fintlabs.consumer.config.RestEndpoints;
import no.fintlabs.core.consumer.shared.resource.CacheService;
import no.fintlabs.core.consumer.shared.resource.WriteableConsumerRestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(name = "Postering", value = RestEndpoints.POSTERING, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class PosteringController extends WriteableConsumerRestController<PosteringResource> {

    public PosteringController(
            CacheService<PosteringResource> cacheService,
            PosteringLinker fintLinker,
            PosteringConfig posteringConfig,
            PosteringEventKafkaProducer posteringEventKafkaProducer,
            PosteringResponseKafkaConsumer posteringResponseKafkaConsumer,
            FintFilterService odataFilterService,
            PosteringRequestKafkaConsumer posteringRequestKafkaConsumer) {
        super(cacheService, fintLinker, posteringConfig, posteringEventKafkaProducer, posteringResponseKafkaConsumer, odataFilterService, posteringRequestKafkaConsumer);
    }

    @PostConstruct
    private void registerIdentificators() {
        super.registerIdenficatorHandler("posteringsid", PosteringResource::getPosteringsId);
    }
}
