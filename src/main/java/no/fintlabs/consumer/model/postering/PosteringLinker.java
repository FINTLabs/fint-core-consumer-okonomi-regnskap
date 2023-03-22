package no.fintlabs.consumer.model.postering;

import no.fint.model.resource.okonomi.regnskap.PosteringResources;
import no.fint.model.resource.okonomi.regnskap.PosteringResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class PosteringLinker extends FintLinker<PosteringResource> {

    public PosteringLinker() {
        super(PosteringResource.class);
    }

    public void mapLinks(PosteringResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public PosteringResources toResources(Collection<PosteringResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public PosteringResources toResources(Stream<PosteringResource> stream, int offset, int size, int totalItems) {
        PosteringResources resources = new PosteringResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(PosteringResource postering) {
        return getAllSelfHrefs(postering).findFirst().orElse(null);
    }


    @Override
    public Stream<String> getAllSelfHrefs(PosteringResource postering) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(postering.getSystemId()) && !StringUtils.isEmpty(postering.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(postering.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(PosteringResource postering) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(postering.getSystemId()) && !StringUtils.isEmpty(postering.getSystemId().getIdentifikatorverdi())) {
            builder.add(postering.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}