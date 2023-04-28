package no.fintlabs.consumer.model.bilag;

import no.fint.model.resource.okonomi.regnskap.BilagResource;
import no.fint.model.resource.okonomi.regnskap.BilagResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class BilagLinker extends FintLinker<BilagResource> {

    public BilagLinker() {
        super(BilagResource.class);
    }

    public void mapLinks(Bilag_REOSURCE resource) {
        super.mapLinks(resource);
    }

    @Override
    public BilagResources toResources(Collection<BilagResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public BilagResources toResources(Stream<BilagResource> stream, int offset, int size, int totalItems) {
        BilagResources resources = new BilagResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(BilagResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(BilagResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(BilagResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(resource.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}