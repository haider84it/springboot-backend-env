package com.example.pvbackend.pdf;


import com.example.pvbackend.model.WartungsprotokollSeite6;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class Seite6PdfRenderer {



    public void render(PDDocument doc, WartungsprotokollSeite6 s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            // Header
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 6 – Wechselrichter (WR) & AC-Verteiler");
            cs.endText();

            float y = 740;

            while (s.getPruefungWR().size() < WR_LABELS.length) {
                s.getPruefungWR().add(new WartungsprotokollSeite6.RowWR());
            }
            while (s.getPruefungAC().size() < AC_LABELS.length) {
                s.getPruefungAC().add(new WartungsprotokollSeite6.RowAC());
            }
            // SECTION 7 – Prüfung WR
            text(cs, "SECTION 7 – Prüfung Wechselrichter (7.1 – 7.x)", 40, y, 11);
            y -= 20;

            // ADD THIS LINE HERE
            text(cs, "Ja        Nein        n.z.", 420, y, 9);
            y -= 10;

            int nr = 1;
            for (WartungsprotokollSeite6.RowWR row : s.getPruefungWR()) {
                text(cs, "7." + nr + " " + WR_LABELS[nr - 1], 40, y, 9);

                drawCheckbox(cs, 420, y, Boolean.TRUE.equals(row.getJa()));
                drawCheckbox(cs, 470, y, Boolean.TRUE.equals(row.getNein()));
                drawCheckbox(cs, 520, y, Boolean.TRUE.equals(row.getNz()));

                y -= 15;
                nr++;
            }

            y -= 10;

            // Zusatz-Tabelle WR
            y = PdfTableUtils.drawZusatzTabelle(cs, s.getZusatzWR(), "Zusatz-Tabelle WR", y);

            y -= 10;

            // SECTION 8 – Prüfung AC-Verteiler
            text(cs, "SECTION 8 – Prüfung AC-Verteiler (8.1 – 8.x)", 40, y, 11);
            y -= 20;


            // ADD THIS LINE HERE
            text(cs, "Ja        Nein        n.z.", 420, y, 9);
            y -= 10;

            nr = 1;

            for (WartungsprotokollSeite6.RowAC row : s.getPruefungAC()) {

                text(cs, "8." + nr + " " + AC_LABELS[nr - 1], 40, y, 9);

                drawCheckbox(cs, 420, y, Boolean.TRUE.equals(row.getJa()));
                drawCheckbox(cs, 470, y, Boolean.TRUE.equals(row.getNein()));
                drawCheckbox(cs, 520, y, Boolean.TRUE.equals(row.getNz()));

                y -= 15;
                nr++;
            }

            y -= 10;

            // Zusatz-Tabelle AC
            y = PdfTableUtils.drawZusatzTabelle(cs, s.getZusatzAC(), "Zusatz-Tabelle AC-Verteiler", y);        }
    }

    private void text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
        cs.setFont(PDType1Font.HELVETICA, size);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(txt == null ? "" : txt);
        cs.endText();
    }


    private static final String[] WR_LABELS = {
            "Alle WR sind frei und zugänglich",
            "Alle WR sind funktionsfähig und speisen ein, ohne Fehlermeldungen",
            "Alle DC-Stecker sind richtig angeschlossen an den WR",
            "Die Lüfter / Kühler / Staubfilter aller WR sind in Ordnung und sauber",
            "Die DC-Schalter der WR sind alle in Ordnung",
            "Die Beschriftung der Wechselrichter ist in Ordnung",
            "Der innerliche Zustand der WR ist in Ordnung",
            "Der äußerliche Zustand der WR ist in Ordnung",
            "Die Kabelverschraubungen sind fest und dicht",
            "Die Netzfreischaltstellen der WR sind alle in Ordnung",
            "Die Phasensymmetrieüberwachung der WR ist in Ordnung",
            "Aufsteller / Montagestelle der WR sind alle in Ordnung",
            "Der Diebstahlschutz der WR ist vorhanden und frei von Schäden",
            "Die WR wurden gereinigt (Foto Verschmutzungsgrad!)"
    };


    private static final String[] AC_LABELS = {
            "Die Verteiler sind frei und zugänglich",
            "Die Automaten / WR-Sicherungen / FI-Schalter sind alle funktionsfähig",
            "Die Hauptsicherungen sind alle funktionsfähig und richtig montiert",
            "Die Sicherungen für das ÜSS sind funktionsfähig und richtig montiert",
            "Die ÜSS-Einrichtungen sind alle funktionsfähig",
            "Die Verteiler sind frei von Schäden",
            "Der innerliche Zustand der Verteiler ist in Ordnung / frei von Feuchtigkeit",
            "Die Erdung der Verteiler ist frei von Schäden und richtig montiert",
            "Die Beschriftung der Verteiler ist in Ordnung",
            "Die Kabelverschraubungen sind fest und dicht",
            "Die Anzugsmomente aller Schrauben sind richtig (Kabelanschlüsse)",
            "Keine Auffälligkeiten durch die Thermografieuntersuchung der Verteiler",
            "AC-Messungen wurden durchgeführt (Verdacht / Teilbereich / vollständig)"
    };



    private static void drawCheckbox(PDPageContentStream cs, float x, float y, boolean checked)
            throws IOException {

        // box
        cs.addRect(x, y - 8, 8, 8);
        cs.stroke();

        // X if checked
        if (checked) {
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 8);
            cs.newLineAtOffset(x + 1.5f, y - 7);
            cs.showText("X");
            cs.endText();
        }
    }


}
