package no.fintlabs.consumer.model.postering;

import no.fint.model.resource.okonomi.regnskap.PosteringResource;
import no.fintlabs.core.consumer.shared.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class PosteringConfig extends ConsumerConfig<PosteringResource> {

    public PosteringConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String domainName() {
        return "okonomi";
    }

    @Override
    protected String packageName() {
        return "regnskap";
    }

    @Override
    protected String resourceName() {
        return "postering";
    }
}
