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
public class WartungsprotokollSeite3 {

    // ---------- SECTION 1 (1.1–1.3) ----------
    // each row: Ja / Nein / NZ
    @ElementCollection
    private List<CheckRow3> moduleZustand = new ArrayList<>();


    // ---------- ZUSATZ-TABELLE #1 ----------
    @ElementCollection
    private List<ZusatzEintrag3> zusatz1 = new ArrayList<>();


    // ---------- SECTION 2 (2.1–2.9) ----------
    @ElementCollection
    private List<CheckRow5State3> anlageZustand = new ArrayList<>();


    // ---------- VERSCHMUTZUNGSGRAD ----------
    private Boolean verschmutzungLeicht;
    private Boolean verschmutzungMittel;
    private Boolean verschmutzungStark;
    private Boolean verschmutzungPartiell;
    private Boolean verschmutzungFlaechig;
    private Boolean verschmutzungRand;


    // ---------- REINIGUNG EMPFOHLEN ----------
    private Boolean reinigungEmpfohlenJa;
    private Boolean reinigungEmpfohlenNein;


    // ---------- ZUSATZ-TABELLE #2 ----------
    @ElementCollection
    private List<ZusatzEintrag3> zusatz2 = new ArrayList<>();


    // --------------------------------------------------------
    // SUB-STRUCTURES
    // --------------------------------------------------------

    @Embeddable
    @Getter @Setter
    public static class CheckRow3 {
        private Boolean ja;
        private Boolean nein;
        private Boolean nz;
    }

    @Embeddable
    @Getter @Setter
    public static class ZusatzEintrag3 {
        private String zupunkt;
        private String bemerkung;
        private String standort;
        private Boolean plan;
        private String bildnr;
        private Boolean beh;
        private Boolean nbeh;
    }

    @Embeddable
    @Getter @Setter
    public static class CheckRow5State3 {
        private Boolean ja;
        private Boolean nein;
        private Boolean nz;
        private Boolean sbm;
        private Boolean sbeiblatt;
    }


    public boolean hasContent() {
        return hasListContent(moduleZustand)
                || hasListContent(zusatz1)
                || hasListContent(anlageZustand)
                || verschmutzungLeicht != null
                || verschmutzungMittel != null
                || verschmutzungStark != null
                || verschmutzungPartiell != null
                || verschmutzungFlaechig != null
                || verschmutzungRand != null
                || reinigungEmpfohlenJa != null
                || reinigungEmpfohlenNein != null
                || hasListContent(zusatz2);
    }

    private boolean hasListContent(List<?> list) {
        return list != null && !list.isEmpty();
    }
}