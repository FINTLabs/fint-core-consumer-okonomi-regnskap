package no.fintlabs.consumer.model.virksomhet;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import no.fint.antlr.FintFilterService;
import no.fint.model.resource.felles.VirksomhetResource;
import no.fint.model.resource.okonomi.regnskap.TransaksjonResource;
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
@RequestMapping(name = "Virksomhet", value = RestEndpoints.VIRKSOMHET, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class VirksomhetController extends ConsumerRestController<VirksomhetResource> {

    public VirksomhetController(VirksomhetService virksomhetService, VirksomhetLinker virksomhetLinker, FintFilterService oDataFilterService) {
        super(virksomhetService, virksomhetLinker, oDataFilterService);
    }

    @PostConstruct
    private void registerIdentificators() {
        super.registerIdenficatorHandler("virksomhetsid", VirksomhetResource::getVirksomhetsId);
        super.registerIdenficatorHandler("organisasjonsnummer", VirksomhetResource::getOrganisasjonsnummer);
    }
}
