package no.fintlabs.consumer.config;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class LinkMapper {

    public static Map<String, String> linkMapper() {
        return ImmutableMap.<String,String>builder()
                .put("no.fint.model.okonomi.regnskap.Transaksjon", "/okonomi/regnskap/transaksjon")
                .put("no.fint.model.okonomi.regnskap.Postering", "/okonomi/regnskap/postering")
                .put("no.fint.model.okonomi.regnskap.Leverandorgruppe", "/okonomi/regnskap/leverandorgruppe")
                .put("no.fint.model.okonomi.regnskap.Leverandor", "/okonomi/regnskap/leverandor")
                .put("no.fint.model.felles.Virksomhet", "/okonomi/regnskap/virksomhet")
                .put("no.fint.model.felles.Person", "/okonomi/regnskap/person")
                .build();
    }

}