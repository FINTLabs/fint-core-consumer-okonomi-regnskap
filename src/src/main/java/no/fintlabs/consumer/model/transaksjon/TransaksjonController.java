package no.fintlabs.consumer.model.transaksjon;

import lombok.extern.slf4j.Slf4j;
import no.fint.antlr.FintFilterService;
import no.fint.model.resource.okonomi.regnskap.TransaksjonResource;
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
@RequestMapping(name = "Transaksjon", value = RestEndpoints.TRANSAKSJON, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class TransaksjonController extends WriteableConsumerRestController<TransaksjonResource> {

    public TransaksjonController(
            CacheService<TransaksjonResource> cacheService,
            TransaksjonLinker fintLinker,
            TransaksjonConfig transaksjonConfig,
            TransaksjonEventKafkaProducer transaksjonEventKafkaProducer,
            TransaksjonResponseKafkaConsumer transaksjonResponseKafkaConsumer,
            FintFilterService odataFilterService,
            TransaksjonRequestKafkaConsumer transaksjonRequestKafkaConsumer) {
        super(cacheService, fintLinker, transaksjonConfig, transaksjonEventKafkaProducer, transaksjonResponseKafkaConsumer, odataFilterService, transaksjonRequestKafkaConsumer);
    }
}
