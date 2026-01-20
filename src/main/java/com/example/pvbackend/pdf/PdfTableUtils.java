package com.example.pvbackend.pdf;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;

public class PdfTableUtils {

    private PdfTableUtils() {
        // utility class
    }

    public static float drawZusatzTabelle(
            PDPageContentStream cs,
            List<? extends ZusatzBase> list,
            String title,
            float y
    ) throws IOException {

        text(cs, title, 40, y, 11);
        y -= 20;

        if (list == null) return y;

        for (ZusatzBase z : list) {

            if (isEmpty(z)) continue;

            if (!isBlank(z.getZupunkt())) {
                text(cs, "• Zu Punkt: " + z.getZupunkt(), 40, y, 9);
                y -= 12;
            }

            if (!isBlank(z.getBemerkung())) {
                text(cs, "  Bemerkung: " + z.getBemerkung(), 40, y, 9);
                y -= 12;
            }

            if (!isBlank(z.getStandort())) {
                text(cs, "  Standort: " + z.getStandort(), 40, y, 9);
                y -= 12;
            }

            boolean hasBottom =
                    Boolean.TRUE.equals(z.getPlan()) ||
                            !isBlank(z.getBildnr()) ||
                            Boolean.TRUE.equals(z.getBeh()) ||
                            Boolean.TRUE.equals(z.getNbeh());

            if (hasBottom) {
                text(cs,
                        "  Plan:" + checkbox(Boolean.TRUE.equals(z.getPlan())) +
                                "  Bild-Nr: " + safe(z.getBildnr()) +
                                "  Beh:" + checkbox(Boolean.TRUE.equals(z.getBeh())) +
                                "  n.Beh:" + checkbox(Boolean.TRUE.equals(z.getNbeh())),
                        40, y, 9);
                y -= 18;
            }

            y -= 6;
        }

        return y;
    }

    // ---------------- helpers ----------------

    private static void text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
        cs.setFont(PDType1Font.HELVETICA, size);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(txt == null ? "" : txt);
        cs.endText();
    }

    private static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }

    private static String safe(String s) {
        return s == null ? "" : s;
    }

    private static String checkbox(boolean checked) {
        return checked ? " [X]" : " [ ]";
    }
    private static boolean isEmpty(ZusatzBase z) {
        return z == null ||
                isBlank(z.getZupunkt()) &&
                        isBlank(z.getBemerkung()) &&
                        isBlank(z.getStandort()) &&
                        !Boolean.TRUE.equals(z.getPlan()) &&
                        isBlank(z.getBildnr()) &&
                        !Boolean.TRUE.equals(z.getBeh()) &&
                        !Boolean.TRUE.equals(z.getNbeh());
    }
}