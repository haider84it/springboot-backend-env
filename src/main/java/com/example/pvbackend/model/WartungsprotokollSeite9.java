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
public class WartungsprotokollSeite9 {

    // ============================================================
    // SECTION 13 — Zentralwechselrichter
    // ============================================================

    @Embeddable
    @Getter
    @Setter
    public static class ZentralWRRow {
        private Boolean ja;
        private Boolean nein;
        private Boolean nz;
    }

    // 13.1 – 13.23  → 23 rows
    @ElementCollection
    private List<ZentralWRRow> pruefungWRZentral = new ArrayList<>();



    // ============================================================
    // ZUSATZ-TABELLE
    // ============================================================

    @Embeddable
    @Getter
    @Setter
    public static class ZusatzWRZentralRow {

        private String zupunkt;
        private String bemerkung;
        private String standort;

        private Boolean plan;
        private String bildnr;

        private Boolean beh;
        private Boolean nbeh;
    }


    @ElementCollection
    private List<ZusatzWRZentralRow> zusatzWRZentral = new ArrayList<>();

}
