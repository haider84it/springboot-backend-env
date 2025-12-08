package com.example.pvbackend.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Embeddable
public class WartungsprotokollSeite2 {

    // Zugangsschlüssel
    private Boolean zugangsschluesselVorhanden;
    private String zugangsschluesselAnmerkung;

    // Thermokamera
    private Boolean thermoKameraVorhanden;
    private String thermoKameraAnmerkung;

    // Vorgehensweise – vor Abfahrt
    private Boolean betreiberKontaktiert;
    private String betreiberAnmerkung;

    private Boolean eigentuemerKontaktiert;
    private String eigentuemerAnmerkung;

    // Elektrofachkraft Name
    private String elektrofachkraftName;


    @ElementCollection
    private List<ArbeitszeitRow> arbeitszeiten = new ArrayList<>();

    @Embeddable
    @Getter
    @Setter
    public static class ArbeitszeitRow {
        private String datum;
        private String name;
        private String beginn;
        private String ende;
        private String stunden;
        private String wetter;
        private String temperatur;
    }
}