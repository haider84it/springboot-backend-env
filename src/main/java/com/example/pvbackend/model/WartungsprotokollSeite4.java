package com.example.pvbackend.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Embeddable
public class WartungsprotokollSeite4 {

    // ---------- SECTION 3 (3.1 – 3.10) ----------
    @ElementCollection
    @CollectionTable(name = "seite4_section3")
    private List<RowCheckSimple> pruefungMontage = new ArrayList<>();


    // ---------- ZUSATZ-TABELLE #1 ----------
    @ElementCollection
    @CollectionTable(name = "seite4_zusatz1")
    private List<ZusatzRow> zusatz1 = new ArrayList<>();


    // ---------- SECTION 4 (4.1 – 4.4) ----------
    @ElementCollection
    @CollectionTable(name = "seite4_section4")
    private List<RowCheckExtended> sichtkontrolle = new ArrayList<>();


    // ---------- ZUSATZ-TABELLE #2 ----------
    @ElementCollection
    @CollectionTable(name = "seite4_zusatz2")
    private List<ZusatzRow> zusatz2 = new ArrayList<>();


    // ---------------------------------------------------
    // Inner classes for table rows
    // ---------------------------------------------------

    /**
     * SECTION 3 rows → Ja / Nein / n.z.
     */
    @Embeddable
    @Getter @Setter
    public static class RowCheckSimple {
        private Boolean ja;
        private Boolean nein;
        private Boolean nz;
    }

    /**
     * SECTION 4 rows → Ja / Nein / n.z. / s.B./M.
     */
    @Embeddable
    @Getter @Setter
    public static class RowCheckExtended {
        private Boolean ja;
        private Boolean nein;
        private Boolean nz;
        private Boolean sbm; // s.B./M.
    }

    /**
     * Zusatz-Tabelle rows (same for section 3 & 4)
     */
    @Embeddable
    @Getter @Setter
    public static class ZusatzRow {
        private String zupunkt;
        private String bemerkung;
        private String standort;
        private Boolean plan;
        private String bildnr;
        private Boolean beh;
        private Boolean nbeh;
    }
}
