package com.example.pvbackend.util;

import com.example.pvbackend.model.WartungsprotokollSeite4;
import com.example.pvbackend.model.WartungsprotokollSeite5;
import com.example.pvbackend.model.WartungsprotokollSeite6;
import com.example.pvbackend.model.WartungsprotokollSeite7;
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

        text(cs, title, 40, y, 11);
        y -= 20;

        for (Object o : list) {

            String zupunkt;
            String bemerkung;
            String standort;
            Boolean plan;
            String bildnr;
            Boolean beh;
            Boolean nbeh;

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
            }  else if (o instanceof WartungsprotokollSeite7.ZusatzZaehlerRow z) {
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


            else {
                continue;
            }

            if (!isBlank(zupunkt)) {
                text(cs, "• Zu Punkt: " + zupunkt, 40, y, 9);
                y -= 12;
            }

            if (!isBlank(bemerkung)) {
                text(cs, "  Bemerkung: " + bemerkung, 40, y, 9);
                y -= 12;
            }

            if (!isBlank(standort)) {
                text(cs, "  Standort: " + standort, 40, y, 9);
                y -= 12;
            }

            boolean hasBottom =
                    Boolean.TRUE.equals(plan) ||
                            !isBlank(bildnr) ||
                            Boolean.TRUE.equals(beh) ||
                            Boolean.TRUE.equals(nbeh);

            if (hasBottom) {
                text(cs,
                        "  Plan:" + checkbox(Boolean.TRUE.equals(plan)) +
                                "  Bild-Nr: " + safe(bildnr) +
                                "  Beh:" + checkbox(Boolean.TRUE.equals(beh)) +
                                "  n.Beh:" + checkbox(Boolean.TRUE.equals(nbeh)),
                        40, y, 9);
                y -= 18;
            }

            y -= 6;
        }

        return y;
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