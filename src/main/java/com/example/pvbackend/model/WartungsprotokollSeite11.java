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
public class WartungsprotokollSeite11 {

    // -----------------------------
    //  SECTION 15 – Sichtprüfung MS
    // -----------------------------
    @Embeddable
    @Getter
    @Setter
    public static class MSsichtRow {
        private Boolean ja;
        private Boolean nein;
        private Boolean nz;
    }

    // 15.1 – 15.4
    @ElementCollection
    private List<MSsichtRow> pruefungMSsicht = new ArrayList<>();


    @Embeddable
    @Getter
    @Setter
    public static class ZusatzMSsichtRow {
        private String zupunkt;
        private String bemerkung;
        private String standort;

        private Boolean plan;
        private String bildnr;

        private Boolean beh;
        private Boolean nbeh;
    }


    @ElementCollection
    private List<ZusatzMSsichtRow> zusatzMSsicht = new ArrayList<>();


    // -----------------------------
    //  SECTION 16 – Sonstiges
    // -----------------------------
    @Embeddable
    @Getter
    @Setter
    public static class SonstigesRow {
        private Boolean ja;
        private Boolean nein;
        private Boolean nz;
    }

    // 16.1 – 16.4
    @ElementCollection    // ← REQUIRED
    private List<SonstigesRow> sonstiges = new ArrayList<>();

    @ElementCollection
    private List<ZusatzSonstigesRow> zusatzSonstiges = new ArrayList<>();



    @Embeddable
    @Getter
    @Setter
    public static class ZusatzSonstigesRow {
        private String zupunkt;
        private String bemerkung;
        private String standort;

        private Boolean plan;
        private String bildnr;

        private Boolean beh;
        private Boolean nbeh;
    }

}
