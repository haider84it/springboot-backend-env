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

import static io.micrometer.common.util.StringUtils.isBlank;
import static org.springframework.util.ObjectUtils.isEmpty;

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
                text(cs,
                        "3." + nr +
                                "  Ja:" + checkbox(Boolean.TRUE.equals(row.getJa())) +
                                "  Nein:" + checkbox(Boolean.TRUE.equals(row.getNein())) +
                                "  n.z:" + checkbox(Boolean.TRUE.equals(row.getNz())),
                        40, y, 9);
                y -= 15;
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
                text(cs,
                        "4." + nr +
                                "  Ja:" + checkbox(Boolean.TRUE.equals(row.getJa())) +
                                "  Nein:" + checkbox(Boolean.TRUE.equals(row.getNein())) +
                                "  n.z:" + checkbox(Boolean.TRUE.equals(row.getNz())) ,
                        40, y, 9);
                y -= 15;
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

        text(cs, title, 40, y, 11);
        y -= 20;

        for (Object o : list) {
            if (!(o instanceof WartungsprotokollSeite4.ZusatzRow z)) continue;

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

}