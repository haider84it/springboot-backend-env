package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite5;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static com.example.pvbackend.util.PdfRenderUtils.drawCheckTriple;
import static io.micrometer.common.util.StringUtils.isBlank;
import static org.springframework.util.ObjectUtils.isEmpty;
import static com.example.pvbackend.util.PdfRenderUtils.*;

@Component
public class Seite5PdfRenderer {

    public void render(PDDocument doc, WartungsprotokollSeite5 s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            // Header
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 5 – Messungen PV & GAKs");
            cs.endText();

            float y = 740;

            // SECTION 5 – Messungen PV-Anlage
            text(cs, "SECTION 5 – Messungen PV-Anlage", 40, y, 11);
            y -= 20;

            y = drawCheckTriple(cs, "5.1 Modultragende Teile gemessen",
                    s.getMessungenPV().get(0).getJa(),
                    s.getMessungenPV().get(0).getNein(),
                    s.getMessungenPV().get(0).getNz(), y);

            y = drawCheckTriple(cs, "5.2 Alle Modulstränge gemessen",
                    s.getMessungenPV().get(1).getJa(),
                    s.getMessungenPV().get(1).getNein(),
                    s.getMessungenPV().get(1).getNz(), y);

            y = drawCheckTriple(cs, "5.3 Erdungswiderstand gemessen",
                    s.getMessungenPV().get(2).getJa(),
                    s.getMessungenPV().get(2).getNein(),
                    s.getMessungenPV().get(2).getNz(), y);

            y = drawCheckTriple(
                    cs,
                    "5.4 Erdung durchgeführt",
                    s.getMessungenPV().get(3).getJa(),
                    s.getMessungenPV().get(3).getNein(),
                    s.getMessungenPV().get(3).getNz(),
                    y
            );

            y = drawCheckTriple(
                    cs,
                    "5.5 Nach DIN 62446 geprüft",
                    s.getMessungenPV().get(4).getJa(),
                    s.getMessungenPV().get(4).getNein(),
                    s.getMessungenPV().get(4).getNz(),
                    y
            );

            y -= 10;

            // Zusatz-Tabelle #1
            y = drawZusatzTabelle(
                    cs,
                    s.getZusatz1(),
                    "Zusatz-Tabelle #1",
                    y
            );

            y -= 10;

            // SECTION 6 – Prüfung GAKs
            text(cs, "SECTION 6 – Prüfung GAKs", 40, y, 11);
            y -= 20;

            y = drawCheckThree(
                    cs,
                    "6.1 GAK zugänglich",
                    s.getPruefungGAKs().get(0).getJa(),
                    s.getPruefungGAKs().get(0).getNein(),
                    s.getPruefungGAKs().get(0).getNz(),
                    y
            );

            // … remaining drawCheckFive calls unchanged …

            drawZusatzTabelle(
                    cs,
                    s.getZusatz2(),
                    "Zusatz-Tabelle #2",
                    y
            );
        }
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
            if (!(o instanceof WartungsprotokollSeite5.ZusatzRow z)) continue;

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


    // Convert boolean → checkbox symbol
    private static String checkbox(boolean b) {
        return b ? "[X]" : "[ ]";
    }

    public static String safe(String s) {
        return s == null ? "" : s;
    }



}