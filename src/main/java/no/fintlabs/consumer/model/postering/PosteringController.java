package no.fintlabs.consumer.model.postering;

import lombok.extern.slf4j.Slf4j;
import no.fint.antlr.FintFilterService;
import no.fint.model.resource.okonomi.regnskap.PosteringResource;
import no.fint.relations.FintRelationsMediaType;
import no.fintlabs.consumer.config.RestEndpoints;
import no.fintlabs.core.consumer.shared.resource.ConsumerRestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(name = "Postering", value = RestEndpoints.POSTERING, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class PosteringController extends ConsumerRestController<PosteringResource> {

    public PosteringController(PosteringService posteringService, PosteringLinker posteringLinker, FintFilterService oDataFilterService) {
        super(posteringService, posteringLinker, oDataFilterService);
    }
}
