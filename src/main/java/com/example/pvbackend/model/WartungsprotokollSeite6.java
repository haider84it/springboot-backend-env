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
public class WartungsprotokollSeite6 {

    // ========================
    // SECTION 7 – Prüfung WR
    // ========================
    @ElementCollection
    private List<RowWR> pruefungWR = new ArrayList<>();

    @Embeddable
    @Getter @Setter
    public static class RowWR {
        private Boolean ja;
        private Boolean nein;
        private Boolean nz;
    }

    // Zusatz-Tabelle WR (5 rows)
    @ElementCollection
    private List<ZusatzRow> zusatzWR = new ArrayList<>();


    // ============================
    // SECTION 8 – Prüfung AC-Verteiler
    // ============================
    @ElementCollection
    private List<RowAC> pruefungAC = new ArrayList<>();

    @Embeddable
    @Getter @Setter
    public static class RowAC {
        private Boolean ja;
        private Boolean nein;
        private Boolean nz;
        private Boolean sbm;       // s.B./M.
        private Boolean sbeiblatt; // s.Beiblatt Nr.
    }

    // Zusatz-Tabelle AC (5 rows)
    @ElementCollection
    private List<ZusatzRow> zusatzAC = new ArrayList<>();


    // Shared Zusatz-row model
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
