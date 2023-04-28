package no.fintlabs.consumer.model.leverandor;

import no.fint.model.resource.okonomi.regnskap.LeverandorResource;
import no.fintlabs.core.consumer.shared.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class LeverandorConfig extends ConsumerConfig<LeverandorResource> {

    public LeverandorConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String resourceName() {
        return "leverandor";
    }
}
