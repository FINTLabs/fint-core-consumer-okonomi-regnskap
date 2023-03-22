package no.fintlabs.consumer.model.leverandorgruppe;

import lombok.extern.slf4j.Slf4j;
import no.fint.antlr.FintFilterService;
import no.fint.model.resource.okonomi.regnskap.LeverandorgruppeResource;
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
@RequestMapping(name = "Leverandorgruppe", value = RestEndpoints.LEVERANDORGRUPPE, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class LeverandorgruppeController extends ConsumerRestController<LeverandorgruppeResource> {

    public LeverandorgruppeController(LeverandorgruppeService leverandorgruppeService, LeverandorgruppeLinker leverandorgruppeLinker, FintFilterService oDataFilterService) {
        super(leverandorgruppeService, leverandorgruppeLinker, oDataFilterService);
    }
}
