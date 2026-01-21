package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite3;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static com.example.pvbackend.util.PdfRenderUtils.safe;
import static io.micrometer.common.util.StringUtils.isBlank;
import static org.springframework.util.ObjectUtils.isEmpty;


@Component
public class Seite3PdfRenderer {


    private static final String[] VERSCHATTUNG_LABELS = {
            "1.1 Die Anlage ist frei von dauerhafter Umgebungsverschattung (zB Aufbauten) \n",
            "1.2 Die Anlage ist frei von temporärer Umgebungsverschattung (zB Bewuchs) \n",
            "1.3 Die Anlage ist frei von eigener Verschattung \n"
    };


    private static final String[] MODULZUSTAND_LABELS = {
            "2.1 Die Oberfläche der Module ist frei von Beschädigungen \n",
            "2.2 Die Rahmen der Module sind frei von Beschädigungen  \n",
            "2.3 Die Glasoberfläche der Module ist frei von Verschmutzung (Foto!) \n",
            "2.4 Die Zellen in den Modulen sind frei von Auffälligkeiten \n",
            "2.5 Die Einbettungsfolie ist frei von Auffälligkeiten \n",
            "2.6 Die Rückseitenfolie der Module ist frei von Beschädigungen \n",
            "2.7 Die Modulanschlussdosen sind frei von Auffälligkeiten \n",
            "2.8 Die Typenschilder und SN-Aufkleber sind frei von Auffälligkeiten \n",
            "2.9 Die Module wurden gereinigt \n"
    };




    public void render(PDDocument doc, WartungsprotokollSeite3 s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);



        PDPageContentStream cs = new PDPageContentStream(doc, page);


            //System.out.println("ModuleZustand size = " + s.getModuleZustand().size());

            cs.setFont(PDType1Font.HELVETICA_BOLD, 11);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 3 – Module & Anlage");
            cs.endText();

            float y = 740;

            // before using the lists
            while (s.getVerschattung().size() < VERSCHATTUNG_LABELS.length) {
                s.getVerschattung().add(new WartungsprotokollSeite3.CheckRow3());
            }

            while (s.getAnlageZustand().size() < MODULZUSTAND_LABELS.length) {
                s.getAnlageZustand().add(new WartungsprotokollSeite3.CheckRow5State3());
            }

            // 1. Kontrolle der Anlage auf Verschattung
            text(cs, "1 Kontrolle der Anlage auf Verschattung", 40, y, 11);
            y -= 20;

            for (int i = 0; i < VERSCHATTUNG_LABELS.length; i++) {
                var row = s.getVerschattung().get(i);
                y = yesNoNzRow(
                        cs,
                        40,
                        y,
                        VERSCHATTUNG_LABELS[i],
                        Boolean.TRUE.equals(row.getJa()),
                        Boolean.TRUE.equals(row.getNein()),
                        Boolean.TRUE.equals(row.getNz())
                );


                cs.moveTo(40, y - 6);
                cs.lineTo(555, y - 6);
                cs.stroke();

                y -= 3;

            }



// spacing between sections
            y -= 10;

            float[] yRef = { y };


            // ZUSATZ-TABELLE #1
            cs =  drawZusatzTabelle(doc, cs, s.getZusatz1(), "Zusatz-Tabelle #1", yRef);
            y = yRef[0];
            y -= 10;



// 2. Sichtkontrolle der Module
            text(cs, "2 Sichtkontrolle der Module auf Beschädigung und Verschmutzung", 40, y, 11);
            y -= 20;

            for (int i = 0; i < MODULZUSTAND_LABELS.length; i++) {
                var row = s.getAnlageZustand().get(i);
                y = yesNoNzRow(
                        cs,
                        40,
                        y,
                        MODULZUSTAND_LABELS[i],
                        Boolean.TRUE.equals(row.getJa()),
                        Boolean.TRUE.equals(row.getNein()),
                        Boolean.TRUE.equals(row.getNz())
                );

                cs.moveTo(40, y - 6);
                cs.lineTo(555, y - 6);
                cs.stroke();

                y -= 3;

            }

        y = drawVerschmutzungUndReinigung(cs, s, y);


            y -= 10;

            float[] yRef2 = { y };

            cs = drawZusatzTabelle(doc, cs, s.getZusatz2(), "Zusatz-Tabelle #2", yRef2);

            y = yRef2[0];

            cs.close();

    }

    public static PDPageContentStream drawZusatzTabelle(
            PDDocument doc,
            PDPageContentStream cs,
            List<? extends ZusatzBase> list,
            String title,
            float[] y
    ) throws IOException {

        // --- TABLE SETTINGS ---
        final float startX = 40f;
        final float tableWidth = 515f;     // fits inside A4 margins
        final float headerHeight = 18f;
        final float rowHeight = 18f;
        final int maxRows = 3;

        // Column widths (sum must be tableWidth)
        final float wZuPunkt = 75f;
        final float wBemerkung = 190f;
        final float wStandort = 70f;
        final float wPlan = 25f;
        final float wBildNr = 45f;
        final float wBeh = 35f;
        final float wNBeh = 35f;
        // sum = 75 + 210 + 70 + 25 + 45 + 25 + 25 = 475
        // we still need 40 more to reach 515, so distribute to remarks:
        final float extra = tableWidth - (wZuPunkt + wBemerkung + wStandort + wPlan + wBildNr + wBeh + wNBeh);
        final float wBemerkungFinal = wBemerkung + extra;

        // --- TITLE ---
        text(cs, title, startX, y[0], 11);
        y[0] -= 16;

        // Page break if needed before drawing the full table
        float tableTotalHeight = headerHeight + (maxRows * rowHeight);
        if (y[0] - tableTotalHeight < 80) {
            cs.close();
            PDPage newPage = new PDPage(PDRectangle.A4);
            doc.addPage(newPage);
            cs = new PDPageContentStream(doc, newPage);
            y[0] = 740;
            text(cs, title, startX, y[0], 11);
            y[0] -= 16;
        }

        // --- DRAW TABLE HEADER + GRID ---
        float topY = y[0];
        float tableLeft = startX;
        float tableRight = startX + tableWidth;

        // Outer border
        cs.addRect(tableLeft, topY - (headerHeight + maxRows * rowHeight), tableWidth, headerHeight + maxRows * rowHeight);
        cs.stroke();

        // Horizontal line after header
        cs.moveTo(tableLeft, topY - headerHeight);
        cs.lineTo(tableRight, topY - headerHeight);
        cs.stroke();

        // Horizontal row lines (3 rows)
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

        // last column ends at tableRight

        // --- HEADER TEXT ---
        float headerTextY = topY - 13; // vertical alignment
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
            ZusatzBase z = (list != null && i < list.size()) ? list.get(i) : null;

            float rowTopY = topY - headerHeight - (i * rowHeight);
            float textY = rowTopY - 13; // align text inside row

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

            // Checkbox cells (center-ish)
            float cbSize = 7f;

            float planX = tableLeft + wZuPunkt + wBemerkungFinal + wStandort + (wPlan / 2f) - (cbSize / 2f);
            float behX = tableLeft + wZuPunkt + wBemerkungFinal + wStandort + wPlan + wBildNr + (wBeh / 2f) - (cbSize / 2f);
            float nbehX = tableLeft + wZuPunkt + wBemerkungFinal + wStandort + wPlan + wBildNr + wBeh + (wNBeh / 2f) - (cbSize / 2f);

            float cbY = rowTopY - 6; // tweak vertical alignment

            // Draw checkboxes
            cs.addRect(planX, cbY - cbSize, cbSize, cbSize);
            cs.stroke();
            if (plan) {
                cs.moveTo(planX + 1, cbY - 1);
                cs.lineTo(planX + cbSize - 1, cbY - cbSize + 1);
                cs.moveTo(planX + 1, cbY - cbSize + 1);
                cs.lineTo(planX + cbSize - 1, cbY - 1);
                cs.stroke();
            }

            cs.addRect(behX, cbY - cbSize, cbSize, cbSize);
            cs.stroke();
            if (beh) {
                cs.moveTo(behX + 1, cbY - 1);
                cs.lineTo(behX + cbSize - 1, cbY - cbSize + 1);
                cs.moveTo(behX + 1, cbY - cbSize + 1);
                cs.lineTo(behX + cbSize - 1, cbY - 1);
                cs.stroke();
            }

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
        y[0] = topY - (headerHeight + maxRows * rowHeight) - 10;

        return cs;
    }



    private float drawVerschmutzungUndReinigung(PDPageContentStream cs, WartungsprotokollSeite3 s, float y) throws IOException {

        y -= 10;

        // VERSCHMUTZUNGSGRAD
        text(cs, "VERSCHMUTZUNGSGRAD", 40, y, 10);
        y -= 15;

        text(cs,
                checkbox(Boolean.TRUE.equals(s.getVerschmutzungLeicht())) + " leicht   " +
                        checkbox(Boolean.TRUE.equals(s.getVerschmutzungMittel())) + " mittel   " +
                        checkbox(Boolean.TRUE.equals(s.getVerschmutzungStark())) + " stark   " +
                        checkbox(Boolean.TRUE.equals(s.getVerschmutzungPartiell())) + " partiell   " +
                        checkbox(Boolean.TRUE.equals(s.getVerschmutzungFlaechig())) + " flächig   " +
                        checkbox(Boolean.TRUE.equals(s.getVerschmutzungRand())) + " Rand",
                40, y, 9
        );
        y -= 20;

        // REINIGUNG EMPFOHLEN
        text(cs, "REINIGUNG EMPFOHLEN", 40, y, 10);
        y -= 15;

        text(cs,
                checkbox(Boolean.TRUE.equals(s.getReinigungEmpfohlenJa())) + " Ja   " +
                        checkbox(Boolean.TRUE.equals(s.getReinigungEmpfohlenNein())) + " Nein",
                40, y, 9
        );
        y -= 20;

        return y;
    }


    private static float text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
        cs.setFont(PDType1Font.HELVETICA, size);

        float lineHeight = size + 2; // spacing between lines

        String[] lines = (txt == null ? "" : txt).split("\\n");

        for (String line : lines) {
            cs.beginText();
            cs.newLineAtOffset(x, y);
            cs.showText(line);
            cs.endText();
            y -= lineHeight;
        }

        return y; // return new y after drawing all lines
    }


    private void drawCheckbox(PDPageContentStream cs, float x, float y, boolean checked) throws IOException {
        float size = 8;
        float yOffset = 2;

        cs.addRect(x, y - size + yOffset, size, size);
        cs.stroke();

        if (checked) {
            cs.moveTo(x + 1, y - 1 + yOffset);
            cs.lineTo(x + size - 1, y - size + 1 + yOffset);
            cs.moveTo(x + 1, y - size + 1 + yOffset);
            cs.lineTo(x + size - 1, y - 1 + yOffset);
            cs.stroke();
        }
    }

    private float yesNoNzRow(
            PDPageContentStream cs,
            float x,
            float y,
            String label,
            boolean ja,
            boolean nein,
            boolean nz
    ) throws IOException {

        float startY = y;

        // draw multiline label
        float newY = text(cs, label, x, y, 9);

        float col = x + 340;

        float checkboxY = startY + 5; // move up (try 2–5)

        drawCheckbox(cs, col, checkboxY, ja);
        text(cs, "Ja", col + 12, startY, 9);

        drawCheckbox(cs, col + 50, checkboxY, nein);
        text(cs, "Nein", col + 62, startY, 9);

        drawCheckbox(cs, col + 100, checkboxY, nz);
        text(cs, "n.z.", col + 112, startY, 9);

        return newY;
    }

    private static String checkbox(boolean b) {
        return b ? "[X]" : "[ ]";
    }




}