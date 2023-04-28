package no.fintlabs.consumer.model.leverandørgruppe;

import lombok.extern.slf4j.Slf4j;
import no.fint.antlr.FintFilterService;
import no.fint.model.resource.okonomi.regnskap.LeverandørgruppeResource;
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
@RequestMapping(name = "Leverandørgruppe", value = RestEndpoints.LEVERANDØRGRUPPE, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class LeverandørgruppeController extends WriteableConsumerRestController<LeverandørgruppeResource> {

    public LeverandørgruppeController(
            CacheService<LeverandørgruppeResource> cacheService,
            LeverandørgruppeLinker fintLinker,
            LeverandørgruppeConfig leverandørgruppeConfig,
            LeverandørgruppeEventKafkaProducer leverandørgruppeEventKafkaProducer,
            LeverandørgruppeResponseKafkaConsumer leverandørgruppeResponseKafkaConsumer,
            FintFilterService odataFilterService,
            LeverandørgruppeRequestKafkaConsumer leverandørgruppeRequestKafkaConsumer) {
        super(cacheService, fintLinker, leverandørgruppeConfig, leverandørgruppeEventKafkaProducer, leverandørgruppeResponseKafkaConsumer, odataFilterService, leverandørgruppeRequestKafkaConsumer);
    }
}
