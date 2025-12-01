package com.example.pvbackend.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

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
}