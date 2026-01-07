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
public class WartungsprotokollSeite8 {

    // ============================================================
    // SECTION 11 — Prüfung Zaunanlage, Gelände, Straßen, Gebäude
    // ============================================================

    @Embeddable
    @Getter
    @Setter
    public static class AussenRow {
        private Boolean ja;
        private Boolean nein;
        private Boolean nz;
    }

    // 11.1 – 11.9 (9 rows)
    @ElementCollection
    private List<AussenRow> pruefungAussen = new ArrayList<>();



    // ============================
    // ZUSATZ-TABELLE #1 — Außen
    // ============================

    @Embeddable
    @Getter
    @Setter
    public static class ZusatzAussenRow {
        private String zupunkt;
        private String bemerkung;
        private String standort;

        private Boolean plan;
        private String bildnr;

        private Boolean beh;
        private Boolean nbeh;
    }

    @ElementCollection
    private List<ZusatzAussenRow> zusatzAussen = new ArrayList<>();



    // ============================================================
    // SECTION 12 — Prüfung Diebstahlschutz
    // ============================================================

    @Embeddable
    @Getter
    @Setter
    public static class DiebstahlRow {
        private Boolean ja;
        private Boolean nein;
        private Boolean nz;
    }

    // 12.1 – 12.5 (5 rows)
    @ElementCollection
    private List<DiebstahlRow> pruefungDiebstahl = new ArrayList<>();



    // ============================
    // ZUSATZ-TABELLE #2 — Diebstahl
    // ============================

    @Embeddable
    @Getter
    @Setter
    public static class ZusatzDiebstahlRow {
        private String zupunkt;
        private String bemerkung;
        private String standort;

        private Boolean plan;
        private String bildnr;

        private Boolean beh;
        private Boolean nbeh;
    }

    @ElementCollection
    private List<ZusatzDiebstahlRow> zusatzDiebstahl = new ArrayList<>();



    public boolean hasContent() {
        return hasListContent(pruefungAussen)
                || hasListContent(zusatzAussen)
                || hasListContent(pruefungDiebstahl)
                || hasListContent(zusatzDiebstahl);
    }

    private boolean hasListContent(List<?> list) {
        return list != null && !list.isEmpty();
    }


}