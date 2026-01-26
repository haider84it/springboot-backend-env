package com.example.pvbackend.model;


import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class WartungsprotokollSeite1 {

    private Long kundeId;

    private String anlagenbezeichnung;
    private String vorgang;
    private String anlagenName;

    @Enumerated(EnumType.STRING)
    private Wartungspaket wartungspaket;

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


    public boolean hasContent() {
        return notEmpty(anlagenbezeichnung)
                || kundeId != null
                || notEmpty(vorgang)
                || notEmpty(anlagenName)
                || wartungspaket != null
                || dcMessungen != null
                || dcNurBeiUnregelmaessigkeiten != null
                || notEmpty(dcVollstaendigOderBereich)
                || vollstaendigGemaessDin != null
                || acMessungen != null
                || acNurBeiUnregelmaessigkeiten != null
                || notEmpty(acVollstaendigOderBereich)
                || zentralwechselrichter != null
                || mittelspannungsanlagenErweitert != null
                || erdungsmessungenStationen != null
                || sichtpruefungMittelspannungsanlagen != null
                || reinigung != null
                || reinigungWr != null
                || reinigungGak != null
                || reinigungModule != null
                || thermografie != null
                || thermografieVerteiler != null
                || thermografieModule != null
                || thermografieMspAnlagen != null
                || kennlinienmessungen != null
                || notEmpty(ersatzpunkt1)
                || notEmpty(ersatzpunkt2)
                || notEmpty(ersatzpunkt3);
    }

    private boolean notEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
