package no.fintlabs.consumer.model.leverandør;

import no.fint.model.resource.okonomi.regnskap.LeverandørResource;
import no.fint.model.resource.okonomi.regnskap.LeverandørResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class LeverandørLinker extends FintLinker<LeverandørResource> {

    public LeverandørLinker() {
        super(LeverandørResource.class);
    }

    public void mapLinks(Leverandør_REOSURCE resource) {
        super.mapLinks(resource);
    }

    @Override
    public LeverandørResources toResources(Collection<LeverandørResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public LeverandørResources toResources(Stream<LeverandørResource> stream, int offset, int size, int totalItems) {
        LeverandørResources resources = new LeverandørResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(LeverandørResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(LeverandørResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(LeverandørResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(resource.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}