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


    // SECTION 7 – Prüfung WR
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


    // SECTION 8 – Prüfung AC-Verteiler
    @ElementCollection
    private List<RowAC> pruefungAC = new ArrayList<>();

    @Embeddable
    @Getter @Setter
    public static class RowAC {
        private Boolean ja;
        private Boolean nein;
        private Boolean nz;
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


    private boolean hasPruefungWR() {
        return pruefungWR != null && pruefungWR.stream().anyMatch(r ->
                Boolean.TRUE.equals(r.getJa()) ||
                        Boolean.TRUE.equals(r.getNein()) ||
                        Boolean.TRUE.equals(r.getNz())
        );
    }

    private boolean hasPruefungAC() {
        return pruefungAC != null && pruefungAC.stream().anyMatch(r ->
                Boolean.TRUE.equals(r.getJa()) ||
                        Boolean.TRUE.equals(r.getNein()) ||
                        Boolean.TRUE.equals(r.getNz())
        );
    }

    private boolean hasZusatz(List<ZusatzRow> list) {
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


    public boolean hasContent() {
        return hasPruefungWR()
                || hasZusatz(zusatzWR)
                || hasPruefungAC()
                || hasZusatz(zusatzAC);
    }


}
