package no.fintlabs.consumer.model.bilag;

import no.fint.model.resource.okonomi.regnskap.BilagResource;
import no.fintlabs.core.consumer.shared.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class BilagConfig extends ConsumerConfig<BilagResource> {

    public BilagConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String resourceName() {
        return "bilag";
    }
}
