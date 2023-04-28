package no.fintlabs.consumer.model.leverandørgruppe;

import no.fint.model.resource.okonomi.regnskap.LeverandørgruppeResource;
import no.fintlabs.core.consumer.shared.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class LeverandørgruppeConfig extends ConsumerConfig<LeverandørgruppeResource> {

    public LeverandørgruppeConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String resourceName() {
        return "leverandørgruppe";
    }
}
