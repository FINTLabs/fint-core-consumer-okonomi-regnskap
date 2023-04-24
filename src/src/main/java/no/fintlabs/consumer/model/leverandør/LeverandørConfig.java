package no.fintlabs.consumer.model.leverandør;

import no.fint.model.resource.okonomi.regnskap.LeverandørResource;
import no.fintlabs.core.consumer.shared.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class LeverandørConfig extends ConsumerConfig<LeverandørResource> {

    public LeverandørConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String resourceName() {
        return "leverandør";
    }
}
