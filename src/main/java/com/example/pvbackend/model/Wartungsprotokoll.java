package com.example.pvbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "wartungsprotokoll")
public class Wartungsprotokoll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "protokoll", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WartungsprotokollImage> bilder = new ArrayList<>();

    // ---------------------------------------
    // SECTION 1 – Bedingungen vor Ort
    // ---------------------------------------

    private String anlagennummer;
    private String datum;
    private String uhrzeitVon;
    private String uhrzeitBis;
    private String temperatur;

    // ---------------------------------------
    // SECTION 2 – Betriebszustand
    // ---------------------------------------
    private String betriebszustand;
    private String teilbetriebWert;

    // ---------------------------------------
    // SECTION 3 – Einstrahlung
    // ---------------------------------------
    private String einstrahlung;

    // ---------------------------------------
    // SECTION 4 – Verschattung (store as CSV)
    // ---------------------------------------
    private String verschattung; // e.g. "Bäume,Schornstein,keine"

    // ---------------------------------------
    // SECTION 5 – Allgemeiner Zustand
    // ---------------------------------------
    private String dach;
    private String gebaeude;
    private String verschmutzung;
    private String verformung;
    private String korrosion;
    private String dehnungsabstaende;
    private String dachdurchdringung;
    private String unterkonstruktion;

    // Documentation checkboxes
    private Boolean  panorama;
    private Boolean  schienensystem;
    private Boolean  dachbefestigung;
    private Boolean  beschwerung;
    private Boolean  modulbefestigung;

    // ---------------------------------------
    // SECTION 6 – Schäden an Modulen
    // ---------------------------------------
    private String schaeden; // CSV format, same as verschattung
    private Boolean  reinigungNotwendig;
    private Boolean  einzelbilder;
    private Boolean  detailbilder;
    private Boolean  typenschild;

    // ---------------------------------------
    // SECTION 7 – Potenzialausgleich
    // ---------------------------------------
    private String potenzialausgleich;

    // ---------------------------------------
    // SECTION 8 – Funktionserdung
    // ---------------------------------------
    private String funktionserdung;

    // ---------------------------------------
    // SECTION 9 – DC-Verkabelung
    // ---------------------------------------
    private String kritischeStellen;
    private String dokumentationFehlerhaft;
    private String biegeradienMantelUv;
    private String kabelInWasserschicht;
    private String markierungBeschriftung;
    private String lochFrassKnick;
    private String verlaengerungenQuetschungen;
    private String dacheinfuehrungUndicht;
}
