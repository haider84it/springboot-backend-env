package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite5;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;


import static com.example.pvbackend.util.PdfRenderUtils.drawCheckTriple;

import static com.example.pvbackend.util.PdfRenderUtils.*;

@Component
public class Seite5PdfRenderer {

    private static final String[] PV_LABELS = {
            "Modultragende Teile gemessen",
            "Alle Modulstränge gemessen",
            "Erdungswiderstand gemessen",
            "Erdung durchgeführt",
            "Nach DIN 62446 geprüft"
    };

    private static final String[] GAK_LABELS = {
            "Alle GAKs sind frei und zugänglich",
            "Die Strangsicherungen sind funktionsfähig",
            "Die ÜSS-Einrichtungen sind funktionsfähig",
            "Die Schalter sind funktionsfähig",
            "Die GAKs sind frei von Schäden",
            "Die GAKs sind frei von Feuchtigkeit",
            "Der innere Zustand der GAKs ist in Ordnung",
            "Die Erdung der Verteiler ist frei von Schäden und richtig montiert",
            "Die Beschriftung ist vollständig und i.O.",
            "Die Kabelverschraubungen sind fest und dicht",
            "Die Anzugsmomente aller Schrauben sind korrekt",
            "Die GAKs wurden gereinigt",
            "Keine Auffälligkeiten aus Thermografieuntersuchung"
    };

    public void render(PDDocument doc, WartungsprotokollSeite5 s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            // Header
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 5 – Messungen PV & GAKs");
            cs.endText();

            float y = 740;

            // SECTION 5 – Messungen PV-Anlage
            // SECTION 5
            text(cs, "SECTION 5 – Messungen PV-Anlage", 40, y, 11);
            y -= 20;

            for (int i = 0; i < 5; i++) {
                var row = s.getMessungenPV().get(i);

                y = drawCheckTriple(cs, "5." + (i + 1) + " " + PV_LABELS[i],
                        row.getJa(), row.getNein(), row.getNz(), y);

                drawLine(cs, 40, y + 2, 555, y + 2);
                y -= 5;
            }

            y -= 10;
            y = drawZusatzTabelle(cs, s.getZusatz1(), "Zusatz-Tabelle #1", y);
            y -= 10;

// SECTION 6
            text(cs, "SECTION 6 – Prüfung GAKs", 40, y, 11);
            y -= 20;

            for (int i = 0; i < 13; i++) {
                var row = s.getPruefungGAKs().get(i);

                y = drawCheckThree(cs, "6." + (i + 1) + " " + GAK_LABELS[i],
                        row.getJa(), row.getNein(), row.getNz(), y);

                drawLine(cs, 40, y + 2, 555, y + 2);
                y -= 5;
            }

            y -= 10;
            drawZusatzTabelle(cs, s.getZusatz2(), "Zusatz-Tabelle #2", y);
        }
    }
}