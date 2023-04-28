package no.fintlabs.consumer.model.leverandorgruppe;

import no.fint.model.resource.okonomi.regnskap.LeverandorgruppeResource;
import no.fint.model.resource.okonomi.regnskap.LeverandorgruppeResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class LeverandorgruppeLinker extends FintLinker<LeverandorgruppeResource> {

    public LeverandorgruppeLinker() {
        super(LeverandorgruppeResource.class);
    }

    public void mapLinks(LeverandorgruppeResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public LeverandorgruppeResources toResources(Collection<LeverandorgruppeResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public LeverandorgruppeResources toResources(Stream<LeverandorgruppeResource> stream, int offset, int size, int totalItems) {
        LeverandorgruppeResources resources = new LeverandorgruppeResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(LeverandorgruppeResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(LeverandorgruppeResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(LeverandorgruppeResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(resource.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}