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
public class WartungsprotokollSeite5 {


    // SECTION 5 – Messungen PV-Anlage (5 rows)
    @ElementCollection
    private List<RowPV> messungenPV = new ArrayList<>();

    @Embeddable
    @Getter @Setter
    public static class RowPV {
        private Boolean ja;
        private Boolean nein;
        private Boolean nz;
    }

    // Zusatz-Tabelle #1 (5 rows)
    @ElementCollection
    private List<ZusatzRow> zusatz1 = new ArrayList<>();

    // SECTION 6 – Prüfung GAKs (13 rows)
    @ElementCollection
    private List<RowGAK> pruefungGAKs = new ArrayList<>();

    @Embeddable
    @Getter @Setter
    public static class RowGAK {
        private Boolean ja;
        private Boolean nein;
        private Boolean nz;
        private Boolean sbm;
        private Boolean sbeiblatt;
    }

    // Zusatz-Tabelle #2 (5 rows)
    @ElementCollection
    private List<ZusatzRow> zusatz2 = new ArrayList<>();

    // shared Zusatz row
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


    public boolean hasContent() {
        return hasRows(messungenPV)
                || hasRows(pruefungGAKs)
                || hasZusatz(zusatz1)
                || hasZusatz(zusatz2);
    }

    private boolean hasRows(List<? extends Object> rows) {
        return rows != null && !rows.isEmpty();
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


}