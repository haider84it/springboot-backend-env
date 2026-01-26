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

    private static final float MARGIN_X = 40;
    private static final float START_Y  = 780;
    private static final float LINE_GAP = 18;

    private static final String[] ABSCHLUSS_LABELS = {
            "Alle WR sind in Betrieb und speisen ein",
            "Anlage und Gebäude geschlossen",
            "Schlüssel zurückgegeben oder Tresor hinterlegt",
            "Dokumentationsbilder und Messprotokoll abgelegt",
            "Beiblätter vorhanden"
    };

    public void render(PDDocument doc, WartungsprotokollSeite11b s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            drawTitle(cs, "Seite 12 – Abschluss & Bewertung", MARGIN_X, START_Y);

            float y = START_Y - 40;

            // Abschluss der Arbeiten
            y = drawSectionTitle(cs, "Abschluss der Arbeiten", MARGIN_X, y);

            for (int i = 0; i < ABSCHLUSS_LABELS.length; i++) {
                Boolean b = (s.getAbschluss() != null && s.getAbschluss().size() > i)
                        ? s.getAbschluss().get(i)
                        : null;

                y = drawCheckboxRow(cs, (i + 1) + " - " + ABSCHLUSS_LABELS[i], Boolean.TRUE.equals(b), MARGIN_X, y);
            }

            y -= 8;

            // Anmerkungen
            y = drawSectionTitle(cs, "Anmerkungen", MARGIN_X, y);

            if (s.getAnmerkungen() != null) {
                for (int i = 0; i < s.getAnmerkungen().size(); i++) {
                    y = drawInputRow(cs, "Anmerkung " + (i + 1), safe(s.getAnmerkungen().get(i)), MARGIN_X, y);
                }
            }

            y -= 10;

            // Alles erledigt
            y = drawCheckboxRow(
                    cs,
                    "Alle Arbeiten erledigt und dokumentiert, Protokoll vollständig ausgefüllt",
                    Boolean.TRUE.equals(s.getAllesErledigt()),
                    MARGIN_X,
                    y
            );

            y -= 10;

            // Datum + Unterschrift
            y = drawInputRow(cs, "Datum", safe(s.getDatum()), MARGIN_X, y);
            y = drawInputRow(cs, "Unterschrift", safe(s.getUnterschrift()), MARGIN_X, y);
        }
    }

    // ---------------- UI helpers ----------------

    private void drawTitle(PDPageContentStream cs, String text, float x, float y) throws IOException {
        cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(text);
        cs.endText();
    }

    private float drawSectionTitle(PDPageContentStream cs, String text, float x, float y) throws IOException {
        cs.setFont(PDType1Font.HELVETICA_BOLD, 11);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(text);
        cs.endText();
        return y - LINE_GAP;
    }

    private float drawCheckboxRow(PDPageContentStream cs, String label, boolean checked, float x, float y) throws IOException {
        float boxSize = 10;
        float boxX = x;
        float boxY = y - 2;

        // checkbox square
        cs.addRect(boxX, boxY, boxSize, boxSize);
        cs.stroke();

        // check mark
        if (checked) {
            cs.moveTo(boxX + 2, boxY + 5);
            cs.lineTo(boxX + 4, boxY + 2);
            cs.lineTo(boxX + 9, boxY + 9);
            cs.stroke();
        }

        // label text
        cs.setFont(PDType1Font.HELVETICA, 10);
        cs.beginText();
        cs.newLineAtOffset(boxX + boxSize + 8, y);
        cs.showText(label);
        cs.endText();

        return y - LINE_GAP;
    }

    private float drawInputRow(PDPageContentStream cs, String label, String value, float x, float y) throws IOException {
        float labelWidth = 90;
        float fieldX = x + labelWidth;
        float fieldWidth = 420;

        // label
        cs.setFont(PDType1Font.HELVETICA, 10);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(label);
        cs.endText();

        // input line
        cs.moveTo(fieldX, y - 2);
        cs.lineTo(fieldX + fieldWidth, y - 2);
        cs.stroke();

        // value
        if (value != null && !value.isBlank()) {
            cs.setFont(PDType1Font.HELVETICA, 10);
            cs.beginText();
            cs.newLineAtOffset(fieldX + 4, y);
            cs.showText(value);
            cs.endText();
        }

        return y - LINE_GAP;
    }

    private String safe(String s) {
        return s == null ? "" : s;
    }
}
