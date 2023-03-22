package no.fintlabs.consumer.model.leverandor;

import lombok.extern.slf4j.Slf4j;
import no.fint.antlr.FintFilterService;
import no.fint.model.resource.okonomi.regnskap.LeverandorResource;
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
@RequestMapping(name = "Leverandor", value = RestEndpoints.LEVERANDOR, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class LeverandorController extends ConsumerRestController<LeverandorResource> {

    public LeverandorController(LeverandorService leverandorService, LeverandorLinker leverandorLinker, FintFilterService oDataFilterService) {
        super(leverandorService, leverandorLinker, oDataFilterService);
    }
}