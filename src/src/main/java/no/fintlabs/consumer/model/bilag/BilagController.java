package no.fintlabs.consumer.model.bilag;

import lombok.extern.slf4j.Slf4j;
import no.fint.antlr.FintFilterService;
import no.fint.model.resource.okonomi.regnskap.BilagResource;
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
@RequestMapping(name = "Bilag", value = RestEndpoints.BILAG, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class BilagController extends WriteableConsumerRestController<BilagResource> {

    public BilagController(
            CacheService<BilagResource> cacheService,
            BilagLinker fintLinker,
            BilagConfig bilagConfig,
            BilagEventKafkaProducer bilagEventKafkaProducer,
            BilagResponseKafkaConsumer bilagResponseKafkaConsumer,
            FintFilterService odataFilterService,
            BilagRequestKafkaConsumer bilagRequestKafkaConsumer) {
        super(cacheService, fintLinker, bilagConfig, bilagEventKafkaProducer, bilagResponseKafkaConsumer, odataFilterService, bilagRequestKafkaConsumer);
    }
}
