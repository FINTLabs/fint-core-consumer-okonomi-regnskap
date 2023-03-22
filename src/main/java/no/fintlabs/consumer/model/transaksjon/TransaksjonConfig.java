package no.fintlabs.consumer.model.transaksjon;

import no.fint.model.resource.okonomi.regnskap.TransaksjonResource;
import no.fintlabs.core.consumer.shared.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class TransaksjonConfig extends ConsumerConfig<TransaksjonResource> {

    public TransaksjonConfig(ConsumerProps consumerProps) {
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
        return "transaksjon";
    }
}
