package com.example.pvbackend.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
@Setter
public class WartungsprotokollSeite11b {

    // Schulnoten (1–6), each checkbox = Boolean
    @ElementCollection
    private List<Boolean> noten = new ArrayList<>();

    // "Abschluss der Arbeiten" – 6 checkboxes
    @ElementCollection
    private List<Boolean> abschluss = new ArrayList<>();


    // "Anmerkung" – 6 textfields
    @ElementCollection
    private List<String> anmerkungen = new ArrayList<>();

    // bottom checkbox
    private Boolean allesErledigt;

    // date field
    private String datum;

    // signature field
    private String unterschrift;
}