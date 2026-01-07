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
public class WartungsprotokollSeite12 {

    // 10 empty recommendation rows (zunr, empfehlung, 3 checkboxes, kosten)
    @ElementCollection
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

    public boolean hasContent() {
        if (empfehlungen == null) return false;

        for (EmpfehlungRow r : empfehlungen) {
            if (r == null) continue;

            if (notEmpty(r.getZunr())
                    || notEmpty(r.getEmpfehlung())
                    || r.getSicherheit() != null
                    || r.getErtrag() != null
                    || r.getErhaltung() != null
                    || notEmpty(r.getKosten())) {
                return true;
            }
        }
        return false;
    }

    private boolean notEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }


}