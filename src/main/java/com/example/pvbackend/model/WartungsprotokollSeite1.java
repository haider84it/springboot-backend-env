package com.example.pvbackend.model;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class WartungsprotokollSeite1 {

    private String anlagenbezeichnung;
    private String vorgang;
    private String auftraggeber;
    private String wartungspaket;

    // DC-Messungen
    private Boolean dcMessungen;
    private Boolean dcNurBeiUnregelmaessigkeiten;
    private String dcVollstaendigOderBereich;
    private Boolean vollstaendigGemaessDin;

    // AC-Messungen
    private Boolean acMessungen;
    private Boolean acNurBeiUnregelmaessigkeiten;
    private String acVollstaendigOderBereich;

    // Weitere Optionen
    private Boolean zentralwechselrichter;
    private Boolean mittelspannungsanlagenErweitert;
    private Boolean erdungsmessungenStationen;
    private Boolean sichtpruefungMittelspannungsanlagen;

    // Reinigung
    private Boolean reinigung;
    private Boolean reinigungWr;
    private Boolean reinigungGak;
    private Boolean reinigungModule;

    // Thermografie
    private Boolean thermografie;
    private Boolean thermografieVerteiler;
    private Boolean thermografieModule;
    private Boolean thermografieMspAnlagen;

    private Boolean kennlinienmessungen;

    // Ersatzpunkte
    private String ersatzpunkt1;
    private String ersatzpunkt2;
    private String ersatzpunkt3;
}
