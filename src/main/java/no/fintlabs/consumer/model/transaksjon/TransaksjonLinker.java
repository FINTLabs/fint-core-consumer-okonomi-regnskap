package no.fintlabs.consumer.model.transaksjon;

import no.fint.model.resource.okonomi.regnskap.TransaksjonResource;
import no.fint.model.resource.okonomi.regnskap.TransaksjonResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class TransaksjonLinker extends FintLinker<TransaksjonResource> {

    public TransaksjonLinker() {
        super(TransaksjonResource.class);
    }

    public void mapLinks(TransaksjonResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public TransaksjonResources toResources(Collection<TransaksjonResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public TransaksjonResources toResources(Stream<TransaksjonResource> stream, int offset, int size, int totalItems) {
        TransaksjonResources resources = new TransaksjonResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(TransaksjonResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(TransaksjonResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getTransaksjonsId()) && !StringUtils.isEmpty(resource.getTransaksjonsId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getTransaksjonsId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(TransaksjonResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getTransaksjonsId()) && !StringUtils.isEmpty(resource.getTransaksjonsId().getIdentifikatorverdi())) {
            builder.add(resource.getTransaksjonsId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}