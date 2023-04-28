package no.fintlabs.consumer.model.leverandor;

import no.fint.model.resource.okonomi.regnskap.LeverandorResource;
import no.fint.model.resource.okonomi.regnskap.LeverandorResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class LeverandorLinker extends FintLinker<LeverandorResource> {

    public LeverandorLinker() {
        super(LeverandorResource.class);
    }

    public void mapLinks(LeverandorResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public LeverandorResources toResources(Collection<LeverandorResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public LeverandorResources toResources(Stream<LeverandorResource> stream, int offset, int size, int totalItems) {
        LeverandorResources resources = new LeverandorResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(LeverandorResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(LeverandorResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(LeverandorResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(resource.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}