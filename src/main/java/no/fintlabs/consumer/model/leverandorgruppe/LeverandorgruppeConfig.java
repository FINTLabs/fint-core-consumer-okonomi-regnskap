package no.fintlabs.consumer.model.leverandorgruppe;

import no.fint.model.resource.okonomi.regnskap.LeverandorgruppeResource;
import no.fintlabs.core.consumer.shared.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class LeverandorgruppeConfig extends ConsumerConfig<LeverandorgruppeResource> {

    public LeverandorgruppeConfig(ConsumerProps consumerProps) {
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
        return "leverandorgruppe";
    }
}
