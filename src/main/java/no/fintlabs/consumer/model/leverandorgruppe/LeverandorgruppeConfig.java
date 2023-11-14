package no.fintlabs.consumer.model.leverandorgruppe;

import no.fint.model.resource.okonomi.regnskap.LeverandorgruppeResource;
import no.fintlabs.core.consumer.shared.config.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class LeverandorgruppeConfig extends ConsumerConfig<LeverandorgruppeResource> {

    public LeverandorgruppeConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String resourceName() {
        return "leverandorgruppe";
    }
}
