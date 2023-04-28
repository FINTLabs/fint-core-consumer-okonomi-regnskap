package no.fintlabs.consumer.model.leverandørgruppe;

import no.fint.model.resource.okonomi.regnskap.LeverandørgruppeResource;
import no.fint.model.resource.okonomi.regnskap.LeverandørgruppeResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class LeverandørgruppeLinker extends FintLinker<LeverandørgruppeResource> {

    public LeverandørgruppeLinker() {
        super(LeverandørgruppeResource.class);
    }

    public void mapLinks(Leverandørgruppe_REOSURCE resource) {
        super.mapLinks(resource);
    }

    @Override
    public LeverandørgruppeResources toResources(Collection<LeverandørgruppeResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public LeverandørgruppeResources toResources(Stream<LeverandørgruppeResource> stream, int offset, int size, int totalItems) {
        LeverandørgruppeResources resources = new LeverandørgruppeResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(LeverandørgruppeResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(LeverandørgruppeResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(LeverandørgruppeResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(resource.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}