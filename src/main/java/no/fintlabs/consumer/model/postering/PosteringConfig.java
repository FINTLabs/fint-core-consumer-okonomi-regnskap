package no.fintlabs.consumer.model.postering;

import no.fint.model.resource.okonomi.regnskap.PosteringResource;
import no.fintlabs.core.consumer.shared.config.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class PosteringConfig extends ConsumerConfig<PosteringResource> {

    public PosteringConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String resourceName() {
        return "postering";
    }
}
