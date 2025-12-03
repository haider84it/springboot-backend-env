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
public class WartungsprotokollSeite7 {

    // =========================
    // SECTION 9 — PRÜFUNG ZÄHLER
    // =========================
    @Embeddable
    @Getter
    @Setter
    public static class ZaehlerRow {
        private Boolean ja;
        private Boolean nein;
        private Boolean nz;
    }

    // 4 rows for 9.1 – 9.4
    @ElementCollection
    private List<ZaehlerRow> pruefungZaehler = new ArrayList<>();



    // =========================
    // ZUSATZ-TABELLE ZÄHLER
    // =========================
    @Embeddable
    @Getter
    @Setter
    public static class ZusatzZaehlerRow {
        private String zupunkt;
        private String bemerkung;
        private String standort;

        private Boolean plan;
        private String bildnr;

        private Boolean beh;
        private Boolean nbeh;
    }

    // 5 rows
    @ElementCollection
    private List<ZusatzZaehlerRow> zusatzZaehler = new ArrayList<>();



    // =========================
    // SECTION 10 — ÜBERWACHUNGSSYSTEM
    // =========================
    @Embeddable
    @Getter
    @Setter
    public static class UeberwachungRow {
        private Boolean ja;
        private Boolean nein;
        private Boolean nz;
    }

    // 10 rows for 10.1 – 10.10
    @ElementCollection
    private List<UeberwachungRow> pruefungUeberwachung = new ArrayList<>();



    // =========================
    // ZUSATZ-TABELLE ÜBERWACHUNG
    // =========================
    @Embeddable
    @Getter
    @Setter
    public static class ZusatzUeberwachungRow {
        private String zupunkt;
        private String bemerkung;
        private String standort;

        private Boolean plan;
        private String bildnr;

        private Boolean beh;
        private Boolean nbeh;
    }

    // 5 rows
    @ElementCollection
    private List<ZusatzUeberwachungRow> zusatzUeberwachung = new ArrayList<>();

}