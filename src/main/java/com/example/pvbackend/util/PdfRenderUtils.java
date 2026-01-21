package com.example.pvbackend.util;

import com.example.pvbackend.model.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;

import static io.micrometer.common.util.StringUtils.isBlank;

public class PdfRenderUtils {

    private PdfRenderUtils() {} // utility class

    // ============================================================
    // PAGE STARTER
    // ============================================================
    public static PDPageContentStream startPage(PDDocument doc, String title) throws IOException {
        PDPage page = new PDPage();
        doc.addPage(page);

        PDPageContentStream cs = new PDPageContentStream(doc, page);
        cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
        cs.beginText();
        cs.newLineAtOffset(40, 780);
        cs.showText(title);
        cs.endText();

        return cs;
    }

    // ============================================================
    // BASIC DRAW HELPERS
    // ============================================================
    public static void text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
        cs.setFont(PDType1Font.HELVETICA, size);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(txt == null ? "" : txt);
        cs.endText();
    }

    public static void drawLine(PDPageContentStream cs, float x1, float y1, float x2, float y2) throws IOException {
        cs.moveTo(x1, y1);
        cs.lineTo(x2, y2);
        cs.stroke();
    }

    public static String checkbox(boolean b) {
        return b ? "[X]" : "[ ]";
    }

    public static String safe(String s) {
        return s == null ? "" : s;
    }

    // ============================================================
    // CHECKBOX RENDER HELPERS
    // ============================================================
    public static String threeChecks(Boolean ja, Boolean nein, Boolean nz) {
        return
                "Ja:" + checkbox(Boolean.TRUE.equals(ja)) +
                        "  Nein:" + checkbox(Boolean.TRUE.equals(nein)) +
                        "  n.z:" + checkbox(Boolean.TRUE.equals(nz));
    }

    public static float drawCheckTriple(PDPageContentStream cs, String label,
                                        Boolean ja, Boolean nein, Boolean nz,
                                        float y) throws IOException {

        if (ja == null && nein == null && nz == null) return y;

        text(cs, label, 40, y, 9);

        float col = 420;

        drawCheckbox(cs, col, y, Boolean.TRUE.equals(ja));
        text(cs, "Ja", col + 12, y, 9);

        drawCheckbox(cs, col + 50, y, Boolean.TRUE.equals(nein));
        text(cs, "Nein", col + 62, y, 9);

        drawCheckbox(cs, col + 100, y, Boolean.TRUE.equals(nz));
        text(cs, "n.z.", col + 112, y, 9);

        return y - 15;
    }

    public static float drawCheckThree(PDPageContentStream cs, String label,
                                       Boolean ja, Boolean nein, Boolean nz,
                                       float y) throws IOException {
        return drawCheckTriple(cs, label, ja, nein, nz, y);
    }

    // ============================================================
    // ZUSATZ-TABELLE RENDERER
    // ============================================================
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

        // Header separator
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

            Object o = (list != null && i < list.size()) ? list.get(i) : null;

            String zupunkt = "";
            String bemerkung = "";
            String standort = "";
            Boolean plan = false;
            String bildnr = "";
            Boolean beh = false;
            Boolean nbeh = false;

            if (o instanceof WartungsprotokollSeite4.ZusatzRow z) {
                zupunkt = z.getZupunkt();
                bemerkung = z.getBemerkung();
                standort = z.getStandort();
                plan = z.getPlan();
                bildnr = z.getBildnr();
                beh = z.getBeh();
                nbeh = z.getNbeh();
            } else if (o instanceof WartungsprotokollSeite5.ZusatzRow z) {
                zupunkt = z.getZupunkt();
                bemerkung = z.getBemerkung();
                standort = z.getStandort();
                plan = z.getPlan();
                bildnr = z.getBildnr();
                beh = z.getBeh();
                nbeh = z.getNbeh();
            } else if (o instanceof WartungsprotokollSeite6.ZusatzRow z) {
                zupunkt = z.getZupunkt();
                bemerkung = z.getBemerkung();
                standort = z.getStandort();
                plan = z.getPlan();
                bildnr = z.getBildnr();
                beh = z.getBeh();
                nbeh = z.getNbeh();
            } else if (o instanceof WartungsprotokollSeite7.ZusatzZaehlerRow z) {
                zupunkt = z.getZupunkt();
                bemerkung = z.getBemerkung();
                standort = z.getStandort();
                plan = z.getPlan();
                bildnr = z.getBildnr();
                beh = z.getBeh();
                nbeh = z.getNbeh();
            } else if (o instanceof WartungsprotokollSeite7.ZusatzUeberwachungRow z) {
                zupunkt = z.getZupunkt();
                bemerkung = z.getBemerkung();
                standort = z.getStandort();
                plan = z.getPlan();
                bildnr = z.getBildnr();
                beh = z.getBeh();
                nbeh = z.getNbeh();
            }

            else if (o instanceof WartungsprotokollSeite8.ZusatzAussenRow z) {
                zupunkt = z.getZupunkt();
                bemerkung = z.getBemerkung();
                standort = z.getStandort();
                plan = z.getPlan();
                bildnr = z.getBildnr();
                beh = z.getBeh();
                nbeh = z.getNbeh();
            }

            else if (o instanceof WartungsprotokollSeite8.ZusatzDiebstahlRow z) {
                zupunkt = z.getZupunkt();
                bemerkung = z.getBemerkung();
                standort = z.getStandort();
                plan = z.getPlan();
                bildnr = z.getBildnr();
                beh = z.getBeh();
                nbeh = z.getNbeh();
            }

            float rowTopY = topY - headerHeight - (i * rowHeight);
            float textY = rowTopY - 13;

            // Text cells
            text(cs, safe(zupunkt), tableLeft + pad, textY, 8);
            text(cs, safe(bemerkung), tableLeft + wZuPunkt + pad, textY, 8);
            text(cs, safe(standort), tableLeft + wZuPunkt + wBemerkungFinal + pad, textY, 8);
            text(cs, safe(bildnr), tableLeft + wZuPunkt + wBemerkungFinal + wStandort + wPlan + pad, textY, 8);

            // Checkbox cells
            float cbSize = 7f;

            float planX = tableLeft + wZuPunkt + wBemerkungFinal + wStandort + (wPlan / 2f) - (cbSize / 2f);
            float behX = tableLeft + wZuPunkt + wBemerkungFinal + wStandort + wPlan + wBildNr + (wBeh / 2f) - (cbSize / 2f);
            float nbehX = tableLeft + wZuPunkt + wBemerkungFinal + wStandort + wPlan + wBildNr + wBeh + (wNBeh / 2f) - (cbSize / 2f);

            float cbY = rowTopY - 6;

            // Plan
            cs.addRect(planX, cbY - cbSize, cbSize, cbSize);
            cs.stroke();
            if (Boolean.TRUE.equals(plan)) {
                cs.moveTo(planX + 1, cbY - 1);
                cs.lineTo(planX + cbSize - 1, cbY - cbSize + 1);
                cs.moveTo(planX + 1, cbY - cbSize + 1);
                cs.lineTo(planX + cbSize - 1, cbY - 1);
                cs.stroke();
            }

            // Beh
            cs.addRect(behX, cbY - cbSize, cbSize, cbSize);
            cs.stroke();
            if (Boolean.TRUE.equals(beh)) {
                cs.moveTo(behX + 1, cbY - 1);
                cs.lineTo(behX + cbSize - 1, cbY - cbSize + 1);
                cs.moveTo(behX + 1, cbY - cbSize + 1);
                cs.lineTo(behX + cbSize - 1, cbY - 1);
                cs.stroke();
            }

            // n.Beh
            cs.addRect(nbehX, cbY - cbSize, cbSize, cbSize);
            cs.stroke();
            if (Boolean.TRUE.equals(nbeh)) {
                cs.moveTo(nbehX + 1, cbY - 1);
                cs.lineTo(nbehX + cbSize - 1, cbY - cbSize + 1);
                cs.moveTo(nbehX + 1, cbY - cbSize + 1);
                cs.lineTo(nbehX + cbSize - 1, cbY - 1);
                cs.stroke();
            }
        }

        return topY - (headerHeight + maxRows * rowHeight) - 10;
    }

    // ============================================================
    // NEW EMPTY ROW BUILDER
    // ============================================================
    public static WartungsprotokollSeite4.ZusatzRow newRow(
            String punkt,
            String bemerkung,
            String standort,
            Boolean plan,
            String bildnr,
            Boolean beh,
            Boolean nbeh
    ) {
        WartungsprotokollSeite4.ZusatzRow r = new WartungsprotokollSeite4.ZusatzRow();
        r.setZupunkt(punkt);
        r.setBemerkung(bemerkung);
        r.setStandort(standort);
        r.setPlan(plan);
        r.setBildnr(bildnr);
        r.setBeh(beh);
        r.setNbeh(nbeh);
        return r;
    }

    public static void drawCheckbox(PDPageContentStream cs, float x, float y, boolean checked) throws IOException {
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

}