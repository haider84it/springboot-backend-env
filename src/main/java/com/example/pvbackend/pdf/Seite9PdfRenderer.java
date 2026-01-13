package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite9;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.pvbackend.util.PdfRenderUtils.*;

@Component
public class Seite9PdfRenderer {

    public void render(PDDocument doc, WartungsprotokollSeite9 s) throws IOException {

        PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            // Header
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 9 – Zentralwechselrichter");
            cs.endText();

            float y = 740;

            // SECTION 13 – Zentralwechselrichter
            text(cs, "SECTION 13 – Zentralwechselrichter (13.1 – 13.23)", 40, y, 11);
            y -= 20;

            int nr = 1;
            for (WartungsprotokollSeite9.ZentralWRRow row : s.getPruefungWRZentral()) {
                text(cs,
                        "13." + nr + "  " +
                                threeChecks(row.getJa(), row.getNein(), row.getNz()),
                        40, y, 9);
                y -= 15;
                nr++;
            }

            y -= 10;

            // Zusatz-Tabelle Zentralwechselrichter
            drawZusatzTabelle(cs, s.getZusatzWRZentral(), "Zusatz-Tabelle Zentralwechselrichter", y);
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
