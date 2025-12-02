package com.example.pvbackend.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
@Setter
public class WartungsprotokollSeite12 {

    // 10 empty recommendation rows (zunr, empfehlung, 3 checkboxes, kosten)
    private List<EmpfehlungRow> empfehlungen = new ArrayList<>();

    @Embeddable
    @Getter
    @Setter
    public static class EmpfehlungRow {
        private String zunr;
        private String empfehlung;
        private Boolean sicherheit;
        private Boolean ertrag;
        private Boolean erhaltung;
        private String kosten;
    }
}