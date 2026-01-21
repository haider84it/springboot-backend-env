package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite12;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.pvbackend.util.PdfRenderUtils.*;
@Component
public class Seite12PdfRenderer {

    public void render(PDDocument doc, WartungsprotokollSeite12 s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            // Header
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 12 – Empfehlungen");
            cs.endText();

            float y = 740;

            text(cs, "Empfehlungen", 40, y, 11);
            y -= 20;

            // keep only filled rows (no empty rows)
            List<WartungsprotokollSeite12.EmpfehlungRow> rows = s.getEmpfehlungen()
                    .stream()
                    .filter(r ->
                            !isBlank(r.getZunr()) ||
                                    !isBlank(r.getEmpfehlung()) ||
                                    Boolean.TRUE.equals(r.getSicherheit()) ||
                                    Boolean.TRUE.equals(r.getErtrag()) ||
                                    Boolean.TRUE.equals(r.getErhaltung()) ||
                                    !isBlank(r.getKosten())
                    )
                    .collect(Collectors.toList());

            drawEmpfehlungenTable(cs, rows, y);
        }
    }

    // ============================================================
    // TABLE
    // ============================================================
    private void drawEmpfehlungenTable(PDPageContentStream cs,
                                       List<WartungsprotokollSeite12.EmpfehlungRow> rows,
                                       float y) throws IOException {

        final float startX = 40f;
        final float tableWidth = 515f;

        final float headerHeight = 18f;
        final float rowHeight = 18f;

        // Column widths (sum must be 515)
        final float wZuNr = 55f;
        final float wEmpfehlung = 240f;
        final float wSicherheit = 55f;
        final float wErtrag = 45f;
        final float wErhaltung = 55f;
        final float wKosten = 65f;

        float topY = y;

        int rowCount = rows == null ? 0 : rows.size();
        float tableHeight = headerHeight + (rowCount * rowHeight);

        // Outer border
        cs.addRect(startX, topY - tableHeight, tableWidth, tableHeight);
        cs.stroke();

        // Header line
        cs.moveTo(startX, topY - headerHeight);
        cs.lineTo(startX + tableWidth, topY - headerHeight);
        cs.stroke();

        // Vertical lines
        float x = startX;

        x += wZuNr;
        drawVLine(cs, x, topY, topY - tableHeight);

        x += wEmpfehlung;
        drawVLine(cs, x, topY, topY - tableHeight);

        x += wSicherheit;
        drawVLine(cs, x, topY, topY - tableHeight);

        x += wErtrag;
        drawVLine(cs, x, topY, topY - tableHeight);

        x += wErhaltung;
        drawVLine(cs, x, topY, topY - tableHeight);

        // last col ends at tableWidth

        // Header text
        float pad = 3f;
        float headerTextY = topY - 13;

        text(cs, "zu Nr.", startX + pad, headerTextY, 8);
        text(cs, "Empfehlung", startX + wZuNr + pad, headerTextY, 8);
        text(cs, "Sicherheit", startX + wZuNr + wEmpfehlung + pad, headerTextY, 8);
        text(cs, "Ertrag", startX + wZuNr + wEmpfehlung + wSicherheit + pad, headerTextY, 8);
        text(cs, "Erhaltung", startX + wZuNr + wEmpfehlung + wSicherheit + wErtrag + pad, headerTextY, 8);
        text(cs, "Kosten", startX + wZuNr + wEmpfehlung + wSicherheit + wErtrag + wErhaltung + pad, headerTextY, 8);

        // Rows
        for (int i = 0; i < rowCount; i++) {

            float rowTopY = topY - headerHeight - (i * rowHeight);
            float rowBottomY = rowTopY - rowHeight;

            // row separator
            cs.moveTo(startX, rowBottomY);
            cs.lineTo(startX + tableWidth, rowBottomY);
            cs.stroke();

            WartungsprotokollSeite12.EmpfehlungRow r = rows.get(i);

            float textY = rowTopY - 13;

            // text columns
            text(cs, safe(r.getZunr()), startX + pad, textY, 8);
            text(cs, safe(r.getEmpfehlung()), startX + wZuNr + pad, textY, 8);
            text(cs, safe(r.getKosten()), startX + wZuNr + wEmpfehlung + wSicherheit + wErtrag + wErhaltung + pad, textY, 8);

            // checkbox columns (centered)
            float cbSize = 7f;
            float cbY = rowTopY - 6;

            float sicherheitX = startX + wZuNr + wEmpfehlung + (wSicherheit / 2f) - (cbSize / 2f);
            float ertragX = startX + wZuNr + wEmpfehlung + wSicherheit + (wErtrag / 2f) - (cbSize / 2f);
            float erhaltungX = startX + wZuNr + wEmpfehlung + wSicherheit + wErtrag + (wErhaltung / 2f) - (cbSize / 2f);

            drawCheckboxBox(cs, sicherheitX, cbY, cbSize, Boolean.TRUE.equals(r.getSicherheit()));
            drawCheckboxBox(cs, ertragX, cbY, cbSize, Boolean.TRUE.equals(r.getErtrag()));
            drawCheckboxBox(cs, erhaltungX, cbY, cbSize, Boolean.TRUE.equals(r.getErhaltung()));
        }
    }

    private void drawCheckboxBox(PDPageContentStream cs, float x, float topY, float size, boolean checked) throws IOException {
        cs.addRect(x, topY - size, size, size);
        cs.stroke();

        if (checked) {
            cs.moveTo(x + 1, topY - 1);
            cs.lineTo(x + size - 1, topY - size + 1);
            cs.moveTo(x + 1, topY - size + 1);
            cs.lineTo(x + size - 1, topY - 1);
            cs.stroke();
        }
    }

    private void drawVLine(PDPageContentStream cs, float x, float yTop, float yBottom) throws IOException {
        cs.moveTo(x, yTop);
        cs.lineTo(x, yBottom);
        cs.stroke();
    }

    // ============================================================
    // TEXT + HELPERS
    // ============================================================
    private void text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
        cs.setFont(PDType1Font.HELVETICA, size);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(txt == null ? "" : txt);
        cs.endText();
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
