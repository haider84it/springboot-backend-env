package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite7;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.pvbackend.util.PdfRenderUtils.*;

@Component
public class Seite7PdfRenderer {

    public void render(PDDocument doc, WartungsprotokollSeite7 s) throws IOException {

        PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            // Header
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 7 – Zähler & Überwachung");
            cs.endText();

            float y = 740;

            // SECTION 9 – Prüfung Zähler
            text(cs, "SECTION 9 – Prüfung Zähler (9.1 – 9.4)", 40, y, 11);
            y -= 20;

            int nr = 1;
            for (WartungsprotokollSeite7.ZaehlerRow row : s.getPruefungZaehler()) {
                text(cs,
                        "9." + nr + "  " +
                                threeChecks(row.getJa(), row.getNein(), row.getNz()),
                        40, y, 9);
                y -= 15;
                nr++;
            }

            y -= 10;

            // Zusatz-Tabelle Zähler
            y = drawZusatzTabelle(cs, s.getZusatzZaehler(), "Zusatz-Tabelle Zähler", y);
            y -= 10;

            // SECTION 10 – Überwachungssystem
            text(cs, "SECTION 10 – Prüfung Überwachungssystem (10.1 – 10.10)", 40, y, 11);
            y -= 20;

            nr = 1;
            for (WartungsprotokollSeite7.UeberwachungRow row : s.getPruefungUeberwachung()) {
                text(cs,
                        "10." + nr + "  " +
                                threeChecks(row.getJa(), row.getNein(), row.getNz()),
                        40, y, 9);
                y -= 15;
                nr++;
            }

            y -= 10;

            // Zusatz-Tabelle Überwachung
            drawZusatzTabelle(cs, s.getZusatzUeberwachung(), "Zusatz-Tabelle Überwachung", y);
        }
    }

    private void text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
        cs.setFont(PDType1Font.HELVETICA, size);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(txt == null ? "" : txt);
        cs.endText();
    }
}