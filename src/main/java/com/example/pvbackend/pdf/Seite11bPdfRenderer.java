package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite11b;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.pvbackend.util.PdfRenderUtils.*;

@Component
public class Seite11bPdfRenderer {

    public void render(PDDocument doc, WartungsprotokollSeite11b s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            // Header
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 11b – Abschluss & Bewertung");
            cs.endText();

            float y = 740;

            y -= 7; //vertical space

            // Abschluss der Arbeiten
            text(cs, "Abschluss der Arbeiten", 40, y, 11);
            y -= 20;

            for (int i = 0; i < ABSCHLUSS_LABELS.length; i++) {
                Boolean b = (s.getAbschluss() != null && s.getAbschluss().size() > i)
                        ? s.getAbschluss().get(i)
                        : null;

                text(cs,
                        (i + 1) + " - " + ABSCHLUSS_LABELS[i] + ": " + checkbox(Boolean.TRUE.equals(b)),
                        40, y, 9);
                y -= 15;
            }

            y -= 10;

            // Anmerkungen
            text(cs, "Anmerkungen", 40, y, 11);
            y -= 20;

            for (int i = 0; i < s.getAnmerkungen().size(); i++) {
                text(cs,
                        "Anmerkung " + (i + 1) + ": " + safe(s.getAnmerkungen().get(i)),
                        40, y, 9);
                y -= 15;
            }

            y -= 20;

            // Alles erledigt
            text(cs,
                    "Alle Arbeiten erledigt und dokumentiert, Protokoll vollständig ausgefüllt: " + checkbox(Boolean.TRUE.equals(s.getAllesErledigt())),
                    40, y, 10);
            y -= 25;

            // Datum + Unterschrift
            text(cs, "Datum: " + safe(s.getDatum()), 40, y, 10);
            y -= 15;

            text(cs, "Unterschrift: " + safe(s.getUnterschrift()), 40, y, 10);
        }
    }

    private void text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
        cs.setFont(PDType1Font.HELVETICA, size);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(txt == null ? "" : txt);
        cs.endText();
    }

    // Convert boolean → checkbox symbol
    private String checkbox(boolean b) {
        return b ? "[X]" : "[ ]";
    }


    private static final String[] ABSCHLUSS_LABELS = {
            "Alle WR sind in Betrieb und speisen ein",
            "Anlage und Gebäude geschlossen",
            "Unterlagen, Wartungsmappe und Schlüssel zurückgegeben",
            "Messgeräte funktionsfähig",
            "Dokumentationsbilder und Messprotokoll abgelegt",
            "Beiblätter vorhanden"
    };


}
