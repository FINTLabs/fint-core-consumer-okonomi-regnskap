package no.fintlabs.consumer.config;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class LinkMapper {

    public static Map<String, String> linkMapper(String contextPath) {
        return ImmutableMap.<String,String>builder()
            .put("no.fint.model.okonomi.regnskap.Transaksjon", "/okonomi/regnskap/transaksjon")
            .put("no.fint.model.okonomi.regnskap.Postering", "/okonomi/regnskap/postering")
            .put("no.fint.model.okonomi.regnskap.Leverandørgruppe", "/okonomi/regnskap/leverandørgruppe")
            .put("no.fint.model.okonomi.regnskap.Leverandør", "/okonomi/regnskap/leverandør")
            .put("no.fint.model.okonomi.regnskap.Virksomhet", "/okonomi/regnskap/virksomhet")
            .put("no.fint.model.okonomi.regnskap.Person", "/okonomi/regnskap/person")
            .put("no.fint.model.okonomi.regnskap.Bilag", "/okonomi/regnskap/bilag")
            .build();
    }

}