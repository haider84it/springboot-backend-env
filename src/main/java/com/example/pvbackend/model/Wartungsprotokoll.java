package com.example.pvbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name = "wartungsprotokoll")
public class Wartungsprotokoll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // @OneToMany(mappedBy = "protokoll", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<WartungsprotokollImage> bilder = new ArrayList<>();    //maybe later

    // ---------------------------------------
    // Seite 1
    // ---------------------------------------

    private String vorgang;
    private String anlagenbezeichnung;
    private String auftraggeber;
    private String wartungspaket;



    private Boolean dcMessungen;                    // Haupt-Checkbox
    private Boolean dcNurBeiUnregelmaessigkeiten;   // Unterpunkt
    private String  dcVollstaendigOderBereich;      // „vollständig“ oder Bereich-Text

    private Boolean vollstaendigGemaessDin;


    private Boolean acMessungen;
    private Boolean acNurBeiUnregelmaessigkeiten;
    private String  acVollstaendigOderBereich;


    private Boolean zentralwechselrichter;
    private Boolean mittelspannungsanlagenErweitert;
    private Boolean erdungsmessungenStationen;
    private Boolean sichtpruefungMittelspannungsanlagen;


    private Boolean reinigung;

    private Boolean reinigungWr;
    private Boolean reinigungGak;
    private Boolean reinigungModule;


    private Boolean thermografie;          // Hauptpunkt

    private Boolean thermografieVerteiler; // Unterpunkt
    private Boolean thermografieModule;    // Unterpunkt
    private Boolean thermografieMspAnlagen; // Unterpunkt

    private Boolean kennlinienmessungen;


    private String erstErsatzPunkt;
    private String zweitErsatzPunkt;
    private String drittErsatzPunkt;




}
