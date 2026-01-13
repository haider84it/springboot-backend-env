package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite8;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;
import static com.example.pvbackend.util.PdfRenderUtils.*;


@Component
public class Seite8PdfRenderer {

    public void render(PDDocument doc, WartungsprotokollSeite8 s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            // Header
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 8 – Außenanlage & Diebstahl");
            cs.endText();

            float y = 740;

            // SECTION 11 – Prüfung Außenanlage
            text(cs, "SECTION 11 – Prüfung Außenanlage (11.1 – 11.9)", 40, y, 11);
            y -= 20;

            int nr = 1;
            for (WartungsprotokollSeite8.AussenRow row : s.getPruefungAussen()) {
                text(cs,
                        "11." + nr + "  " +
                                threeChecks(row.getJa(), row.getNein(), row.getNz()),
                        40, y, 9);
                y -= 15;
                nr++;
            }

            y -= 10;

            // Zusatz-Tabelle Außenanlage
            y = drawZusatzTabelle(cs, s.getZusatzAussen(), "Zusatz-Tabelle Außenanlage", y);
            y -= 10;

            // SECTION 12 – Prüfung Diebstahlschutz
            text(cs, "SECTION 12 – Prüfung Diebstahlschutz (12.1 – 12.5)", 40, y, 11);
            y -= 20;

            nr = 1;
            for (WartungsprotokollSeite8.DiebstahlRow row : s.getPruefungDiebstahl()) {
                text(cs,
                        "12." + nr + "  " +
                                threeChecks(row.getJa(), row.getNein(), row.getNz()),
                        40, y, 9);
                y -= 15;
                nr++;
            }

            y -= 10;

            // Zusatz-Tabelle Diebstahlschutz
            drawZusatzTabelle(cs, s.getZusatzDiebstahl(), "Zusatz-Tabelle Diebstahlschutz", y);
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