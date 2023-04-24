package no.fintlabs.consumer.model.leverandør;

import lombok.extern.slf4j.Slf4j;
import no.fint.antlr.FintFilterService;
import no.fint.model.resource.okonomi.regnskap.LeverandørResource;
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
@RequestMapping(name = "Leverandør", value = RestEndpoints.LEVERANDØR, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class LeverandørController extends WriteableConsumerRestController<LeverandørResource> {

    public LeverandørController(
            CacheService<LeverandørResource> cacheService,
            LeverandørLinker fintLinker,
            LeverandørConfig leverandørConfig,
            LeverandørEventKafkaProducer leverandørEventKafkaProducer,
            LeverandørResponseKafkaConsumer leverandørResponseKafkaConsumer,
            FintFilterService odataFilterService,
            LeverandørRequestKafkaConsumer leverandørRequestKafkaConsumer) {
        super(cacheService, fintLinker, leverandørConfig, leverandørEventKafkaProducer, leverandørResponseKafkaConsumer, odataFilterService, leverandørRequestKafkaConsumer);
    }
}
