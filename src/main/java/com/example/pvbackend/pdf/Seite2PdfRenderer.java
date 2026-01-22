package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite2;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;


import java.io.IOException;
@Component
public class Seite2PdfRenderer {

    private static final float MARGIN_X = 40;
    private static final float START_Y  = 780;
    private static final float LINE_GAP = 18;

    public void render(PDDocument doc, WartungsprotokollSeite2 s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            // Title
            drawTitle(cs, "Seite 2 – Zugang & Arbeitszeiten", MARGIN_X, START_Y);

            float y = START_Y - 40;

            // Zugangsschlüssel
            y = drawSectionTitle(cs, "Zugangsschlüssel", MARGIN_X, y);
            y = drawCheckboxRow(cs, "Vorhanden", Boolean.TRUE.equals(s.getZugangsschluesselVorhanden()), MARGIN_X, y);
            y = drawInputRow(cs, "Anmerkung", safe(s.getZugangsschluesselAnmerkung()), MARGIN_X, y);

            // Thermokamera
            y -= 8;
            y = drawSectionTitle(cs, "Thermografiekamera für Wartungsarbeiten", MARGIN_X, y);
            y = drawCheckboxRow(cs, "Vorhanden", Boolean.TRUE.equals(s.getThermoKameraVorhanden()), MARGIN_X, y);
            y = drawInputRow(cs, "Anmerkung", safe(s.getThermoKameraAnmerkung()), MARGIN_X, y);

            // Vorgehensweise
            y -= 8;
            y = drawSectionTitle(cs, "Vorgehensweise – vor Abfahrt", MARGIN_X, y);
            y = drawCheckboxRow(cs, "Anlagenbetreiber wurde kontaktiert", Boolean.TRUE.equals(s.getBetreiberKontaktiert()), MARGIN_X, y);
            y = drawInputRow(cs, "Anmerkung", safe(s.getBetreiberAnmerkung()), MARGIN_X, y);

            y = drawCheckboxRow(cs, "Grundstückseigentümer kontaktiert (Zugang)", Boolean.TRUE.equals(s.getEigentuemerKontaktiert()), MARGIN_X, y);
            y = drawInputRow(cs, "Anmerkung", safe(s.getEigentuemerAnmerkung()), MARGIN_X, y);

            // Elektrofachkraft
            y -= 8;
            y = drawSectionTitle(cs, "Verantwortliche ausführende Elektrofachkraft", MARGIN_X, y);
            y = drawInputRow(cs, "Name", safe(s.getElektrofachkraftName()), MARGIN_X, y);

            // Arbeitszeiten
            y -= 10;
            y = drawSectionTitle(cs, "Arbeitszeiten (pro Tag 1 Zeile)", MARGIN_X, y);

            y = drawTableHeader(cs, MARGIN_X, y);

            for (var r : s.getArbeitszeiten()) {
                y = drawTableRow(cs, MARGIN_X, y,
                        safe(r.getDatum()),
                        safe(r.getName()),
                        safe(r.getBeginn()),
                        safe(r.getEnde()),
                        safe(r.getStunden()),
                        safe(r.getWetter()),
                        safe(r.getTemperatur())
                );
            }
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
        float labelWidth = 70;
        float fieldX = x + labelWidth;
        float fieldWidth = 420;

        // label
        cs.setFont(PDType1Font.HELVETICA, 10);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(label);
        cs.endText();

        // input field line (like UI)
        cs.moveTo(fieldX, y - 2);
        cs.lineTo(fieldX + fieldWidth, y - 2);
        cs.stroke();

        // value text
        if (value != null && !value.isBlank()) {
            cs.setFont(PDType1Font.HELVETICA, 10);
            cs.beginText();
            cs.newLineAtOffset(fieldX + 4, y);
            cs.showText(value);
            cs.endText();
        }

        return y - LINE_GAP;
    }

    // ---------------- Table helpers ----------------

    private float drawTableHeader(PDPageContentStream cs, float x, float y) throws IOException {
        cs.setFont(PDType1Font.HELVETICA_BOLD, 9);

        String[] headers = {"Datum", "Name", "Beginn", "Ende", "Std.", "Wetter", "Temp"};
        float[] widths = {70, 90, 55, 55, 35, 70, 50};

        float cx = x;
        for (int i = 0; i < headers.length; i++) {
            drawCell(cs, headers[i], cx, y, widths[i], true);
            cx += widths[i];
        }
        return y - 16;
    }

    private float drawTableRow(PDPageContentStream cs, float x, float y,
                               String datum, String name, String beginn, String ende,
                               String std, String wetter, String temp) throws IOException {

        cs.setFont(PDType1Font.HELVETICA, 9);

        String[] values = {datum, name, beginn, ende, std, wetter, temp};
        float[] widths = {70, 90, 55, 55, 35, 70, 50};

        float cx = x;
        for (int i = 0; i < values.length; i++) {
            drawCell(cs, values[i], cx, y, widths[i], false);
            cx += widths[i];
        }
        return y - 16;
    }

    private void drawCell(PDPageContentStream cs, String text, float x, float y, float w, boolean header) throws IOException {
        float h = 14;

        // border
        cs.addRect(x, y - 3, w, h);
        cs.stroke();

        // text
        cs.beginText();
        cs.newLineAtOffset(x + 3, y + 1);
        cs.showText(text == null ? "" : text);
        cs.endText();
    }

    private String safe(String s) {
        return s == null ? "" : s;
    }
}