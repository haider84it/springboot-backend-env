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
public class WartungsprotokollSeite10 {

    // ============================================================
    //   SECTION 14 — Prüfung Mittelspannungsanlagen (29 rows)
    // ============================================================

    @Embeddable
    @Getter
    @Setter
    public static class MSPRow {
        private Boolean ja;
        private Boolean nein;
        private Boolean nz;
    }

    // 14.1 – 14.29
    @ElementCollection
    private List<MSPRow> pruefungMSP = new ArrayList<>();

    @ElementCollection
    private List<ZusatzMSPRow> zusatzMSP = new ArrayList<>();


    // ============================================================
    //   ZUSATZ-TABELLE
    // ============================================================

    @Embeddable
    @Getter
    @Setter
    public static class ZusatzMSPRow {
        private String zupunkt;
        private String bemerkung;
        private String standort;

        private Boolean plan;
        private String bildnr;

        private Boolean beh;
        private Boolean nbeh;
    }


}