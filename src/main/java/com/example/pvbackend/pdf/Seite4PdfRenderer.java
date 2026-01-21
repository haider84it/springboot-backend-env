package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite4;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static com.example.pvbackend.util.PdfRenderUtils.safe;


@Component
public class Seite4PdfRenderer {

    public void render(PDDocument doc, WartungsprotokollSeite4 s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            // Header
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 4 – Montage & Sichtkontrolle");
            cs.endText();

            float y = 740;

            // SECTION 3
            text(cs, "SECTION 3 – Prüfung Montage (3.1 – 3.10)", 40, y, 11);
            y -= 20;

            int nr = 1;
            for (WartungsprotokollSeite4.RowCheckSimple row : s.getPruefungMontage()) {

                text(cs, "3." + nr + " " + MONTAGE_LABELS[nr - 1], 40, y, 9);

                float abitUpY = y + 5; // move up (try 2–5)

                drawCheckbox(cs, 420, abitUpY, Boolean.TRUE.equals(row.getJa()));
                text(cs, "Ja", 432, y, 9);

                drawCheckbox(cs, 470, abitUpY, Boolean.TRUE.equals(row.getNein()));
                text(cs, "Nein", 482, y, 9);

                drawCheckbox(cs, 520, abitUpY, Boolean.TRUE.equals(row.getNz()));
                text(cs, "n.z.", 532, y, 9);

                cs.moveTo(40, y - 6);
                cs.lineTo(555, y - 6);
                cs.stroke();


                y -= 15;
                y -= 12;   // extra space

                nr++;
            }

            y -= 10;

            // ZUSATZ-TABELLE #1
            y = drawZusatzTabelle(cs, s.getZusatz1(), "Zusatz-Tabelle #1", y);
            y -= 10;

            // SECTION 4
            text(cs, "SECTION 4 – Sichtkontrolle (4.1 – 4.4)", 40, y, 11);
            y -= 20;

            nr = 1;
            for (WartungsprotokollSeite4.RowCheckExtended row : s.getSichtkontrolle()) {
                text(cs, "4." + nr + " " + SICHTKONTROLLE_LABELS[nr - 1], 40, y, 9);

                float abitUpY = y + 5; // move up (try 2–5)
                drawCheckbox(cs, 420, abitUpY, Boolean.TRUE.equals(row.getJa()));
                text(cs, "Ja", 432, y, 9);

                drawCheckbox(cs, 470, abitUpY, Boolean.TRUE.equals(row.getNein()));
                text(cs, "Nein", 482, y, 9);

                drawCheckbox(cs, 520, abitUpY, Boolean.TRUE.equals(row.getNz()));
                text(cs, "n.z.", 532, y, 9);



                y -= 15;
                y -= 12;   // extra space
                nr++;
            }

            y -= 10;

            // ZUSATZ-TABELLE #2
            drawZusatzTabelle(cs, s.getZusatz2(), "Zusatz-Tabelle #2", y);
        }
    }


    private static String checkbox(boolean b) {
        return b ? "[X]" : "[ ]";
    }

    private static void text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
        cs.setFont(PDType1Font.HELVETICA, size);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(txt == null ? "" : txt);
        cs.endText();
    }

    public static float drawZusatzTabelle(
            PDPageContentStream cs,
            List<? extends Object> list,
            String title,
            float y
    ) throws IOException {

        // --- TABLE SETTINGS ---
        final float startX = 40f;
        final float tableWidth = 515f;
        final float headerHeight = 18f;
        final float rowHeight = 18f;
        final int maxRows = 3;

        // Column widths
        final float wZuPunkt = 75f;
        final float wBemerkung = 190f;
        final float wStandort = 70f;
        final float wPlan = 25f;
        final float wBildNr = 45f;
        final float wBeh = 35f;
        final float wNBeh = 35f;

        final float extra = tableWidth - (wZuPunkt + wBemerkung + wStandort + wPlan + wBildNr + wBeh + wNBeh);
        final float wBemerkungFinal = wBemerkung + extra;

        // --- TITLE ---
        text(cs, title, startX, y, 11);
        y -= 16;

        float topY = y;
        float tableLeft = startX;
        float tableRight = startX + tableWidth;

        // Outer border
        cs.addRect(tableLeft, topY - (headerHeight + maxRows * rowHeight), tableWidth, headerHeight + maxRows * rowHeight);
        cs.stroke();

        // Header separator line
        cs.moveTo(tableLeft, topY - headerHeight);
        cs.lineTo(tableRight, topY - headerHeight);
        cs.stroke();

        // Row lines
        for (int r = 1; r <= maxRows; r++) {
            float lineY = topY - headerHeight - (r * rowHeight);
            cs.moveTo(tableLeft, lineY);
            cs.lineTo(tableRight, lineY);
            cs.stroke();
        }

        // Vertical lines
        float x = tableLeft;

        x += wZuPunkt;
        cs.moveTo(x, topY);
        cs.lineTo(x, topY - (headerHeight + maxRows * rowHeight));
        cs.stroke();

        x += wBemerkungFinal;
        cs.moveTo(x, topY);
        cs.lineTo(x, topY - (headerHeight + maxRows * rowHeight));
        cs.stroke();

        x += wStandort;
        cs.moveTo(x, topY);
        cs.lineTo(x, topY - (headerHeight + maxRows * rowHeight));
        cs.stroke();

        x += wPlan;
        cs.moveTo(x, topY);
        cs.lineTo(x, topY - (headerHeight + maxRows * rowHeight));
        cs.stroke();

        x += wBildNr;
        cs.moveTo(x, topY);
        cs.lineTo(x, topY - (headerHeight + maxRows * rowHeight));
        cs.stroke();

        x += wBeh;
        cs.moveTo(x, topY);
        cs.lineTo(x, topY - (headerHeight + maxRows * rowHeight));
        cs.stroke();

        // --- HEADER TEXT ---
        float headerTextY = topY - 13;
        float pad = 3f;

        text(cs, "Zu Punkt", tableLeft + pad, headerTextY, 8);
        text(cs, "Bemerkungen / Mängel", tableLeft + wZuPunkt + pad, headerTextY, 8);
        text(cs, "Standort Mangel", tableLeft + wZuPunkt + wBemerkungFinal + pad, headerTextY, 8);
        text(cs, "Plan", tableLeft + wZuPunkt + wBemerkungFinal + wStandort + pad, headerTextY, 8);
        text(cs, "Bild-Nr.", tableLeft + wZuPunkt + wBemerkungFinal + wStandort + wPlan + pad, headerTextY, 8);
        text(cs, "Beh.", tableLeft + wZuPunkt + wBemerkungFinal + wStandort + wPlan + wBildNr + pad, headerTextY, 8);
        text(cs, "n. Beh.", tableLeft + wZuPunkt + wBemerkungFinal + wStandort + wPlan + wBildNr + wBeh + pad, headerTextY, 8);

        // --- FILL 3 ROWS ---
        for (int i = 0; i < maxRows; i++) {

            WartungsprotokollSeite4.ZusatzRow z = null;
            if (list != null && i < list.size() && list.get(i) instanceof WartungsprotokollSeite4.ZusatzRow zr) {
                z = zr;
            }

            float rowTopY = topY - headerHeight - (i * rowHeight);
            float textY = rowTopY - 13;

            String zuPunkt = (z == null) ? "" : safe(z.getZupunkt());
            String bemerkung = (z == null) ? "" : safe(z.getBemerkung());
            String standort = (z == null) ? "" : safe(z.getStandort());
            String bildNr = (z == null) ? "" : safe(z.getBildnr());

            boolean plan = z != null && Boolean.TRUE.equals(z.getPlan());
            boolean beh = z != null && Boolean.TRUE.equals(z.getBeh());
            boolean nbeh = z != null && Boolean.TRUE.equals(z.getNbeh());

            // Text cells
            text(cs, zuPunkt, tableLeft + pad, textY, 8);
            text(cs, bemerkung, tableLeft + wZuPunkt + pad, textY, 8);
            text(cs, standort, tableLeft + wZuPunkt + wBemerkungFinal + pad, textY, 8);
            text(cs, bildNr, tableLeft + wZuPunkt + wBemerkungFinal + wStandort + wPlan + pad, textY, 8);

            // Checkbox cells
            float cbSize = 7f;

            float planX = tableLeft + wZuPunkt + wBemerkungFinal + wStandort + (wPlan / 2f) - (cbSize / 2f);
            float behX = tableLeft + wZuPunkt + wBemerkungFinal + wStandort + wPlan + wBildNr + (wBeh / 2f) - (cbSize / 2f);
            float nbehX = tableLeft + wZuPunkt + wBemerkungFinal + wStandort + wPlan + wBildNr + wBeh + (wNBeh / 2f) - (cbSize / 2f);

            float cbY = rowTopY - 6;

            // Plan checkbox
            cs.addRect(planX, cbY - cbSize, cbSize, cbSize);
            cs.stroke();
            if (plan) {
                cs.moveTo(planX + 1, cbY - 1);
                cs.lineTo(planX + cbSize - 1, cbY - cbSize + 1);
                cs.moveTo(planX + 1, cbY - cbSize + 1);
                cs.lineTo(planX + cbSize - 1, cbY - 1);
                cs.stroke();
            }

            // Beh checkbox
            cs.addRect(behX, cbY - cbSize, cbSize, cbSize);
            cs.stroke();
            if (beh) {
                cs.moveTo(behX + 1, cbY - 1);
                cs.lineTo(behX + cbSize - 1, cbY - cbSize + 1);
                cs.moveTo(behX + 1, cbY - cbSize + 1);
                cs.lineTo(behX + cbSize - 1, cbY - 1);
                cs.stroke();
            }

            // n.Beh checkbox
            cs.addRect(nbehX, cbY - cbSize, cbSize, cbSize);
            cs.stroke();
            if (nbeh) {
                cs.moveTo(nbehX + 1, cbY - 1);
                cs.lineTo(nbehX + cbSize - 1, cbY - cbSize + 1);
                cs.moveTo(nbehX + 1, cbY - cbSize + 1);
                cs.lineTo(nbehX + cbSize - 1, cbY - 1);
                cs.stroke();
            }
        }

        // Move y below table
        return topY - (headerHeight + maxRows * rowHeight) - 10;
    }


    private static final String[] MONTAGE_LABELS = {
            "Das Montagestell ist frei von Beschädigungen",
            "Das Montagestell ist frei von Roststellen",
            "Das Montagestell ist richtig montiert",
            "Die Anzugsmomente der Gestellverschraubungen sind richtig (33–56 Nm)",
            "Die Modulklemmen sind frei von Beschädigungen",
            "Die Modulklemmen sind richtig montiert",
            "Die Anzugsmomente der Modulklemmen sind richtig (10 Nm)",
            "Der Potentialausgleich ist frei von Beschädigungen",
            "Die Beschriftung der Modulreihen ist in Ordnung",
            "Der Diebstahlschutz der Module ist vorhanden und frei von Schäden"
    };



    private static final String[] SICHTKONTROLLE_LABELS = {
            "Die Strangleitungen sind frei von Beschädigungen",
            "Die Strangleitungen sind frei von Schmorspuren",
            "Die Verbindungsstellen sind frei von Beschädigungen (Steckverbindungen usw.)",
            "Die Kabelverlegung ist ordnungsgemäß"
    };


    private static void drawCheckbox(PDPageContentStream cs, float x, float y, boolean checked) throws IOException {
        cs.addRect(x, y - 8, 8, 8);
        cs.stroke();

        if (checked) {
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 8);
            cs.newLineAtOffset(x + 1.5f, y - 7);
            cs.showText("X");
            cs.endText();
        }
    }



}