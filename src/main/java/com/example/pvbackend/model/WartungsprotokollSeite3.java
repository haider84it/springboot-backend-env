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
        return hasCheckRow3(moduleZustand)
                || hasZusatz(zusatz1)
                || hasCheckRow5(anlageZustand)
                || Boolean.TRUE.equals(verschmutzungLeicht)
                || Boolean.TRUE.equals(verschmutzungMittel)
                || Boolean.TRUE.equals(verschmutzungStark)
                || Boolean.TRUE.equals(verschmutzungPartiell)
                || Boolean.TRUE.equals(verschmutzungFlaechig)
                || Boolean.TRUE.equals(verschmutzungRand)
                || Boolean.TRUE.equals(reinigungEmpfohlenJa)
                || Boolean.TRUE.equals(reinigungEmpfohlenNein)
                || hasZusatz(zusatz2);
    }

    private boolean hasCheckRow3(List<CheckRow3> list) {
        return list != null && list.stream().anyMatch(r ->
                Boolean.TRUE.equals(r.getJa()) ||
                        Boolean.TRUE.equals(r.getNein()) ||
                        Boolean.TRUE.equals(r.getNz())
        );
    }


    private boolean hasCheckRow5(List<CheckRow5State3> list) {
        return list != null && list.stream().anyMatch(r ->
                Boolean.TRUE.equals(r.getJa()) ||
                        Boolean.TRUE.equals(r.getNein()) ||
                        Boolean.TRUE.equals(r.getNz()) ||
                        Boolean.TRUE.equals(r.getSbm()) ||
                        Boolean.TRUE.equals(r.getSbeiblatt())
        );
    }

    private boolean hasZusatz(List<ZusatzEintrag3> list) {
        return list != null && list.stream().anyMatch(r ->
                (r.getZupunkt() != null && !r.getZupunkt().isBlank()) ||
                        (r.getBemerkung() != null && !r.getBemerkung().isBlank()) ||
                        (r.getStandort() != null && !r.getStandort().isBlank()) ||
                        Boolean.TRUE.equals(r.getPlan()) ||
                        (r.getBildnr() != null && !r.getBildnr().isBlank()) ||
                        Boolean.TRUE.equals(r.getBeh()) ||
                        Boolean.TRUE.equals(r.getNbeh())
        );
    }


}