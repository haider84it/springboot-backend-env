package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite6;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.pvbackend.pdf.Seite4PdfRenderer.drawZusatzTabelle;

import static com.example.pvbackend.util.PdfRenderUtils.*;

@Component
public class Seite6PdfRenderer {

    public void render(PDDocument doc, WartungsprotokollSeite6 s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            // Header
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 6 – Wechselrichter (WR) & AC-Verteiler");
            cs.endText();

            float y = 740;

            // SECTION 7 – Prüfung WR
            text(cs, "SECTION 7 – Prüfung Wechselrichter (7.1 – 7.x)", 40, y, 11);
            y -= 20;

            int nr = 1;
            for (WartungsprotokollSeite6.RowWR row : s.getPruefungWR()) {
                text(cs,
                        "7." + nr + "  " + threeChecks(row.getJa(), row.getNein(), row.getNz()),
                        40, y, 9);
                y -= 15;
                nr++;
            }

            y -= 10;

            // Zusatz-Tabelle WR
            y = drawZusatzTabelle(cs, s.getZusatzWR(), "Zusatz-Tabelle WR", y);
            y -= 10;

            // SECTION 8 – Prüfung AC-Verteiler
            text(cs, "SECTION 8 – Prüfung AC-Verteiler (8.1 – 8.x)", 40, y, 11);
            y -= 20;

            nr = 1;
            for (WartungsprotokollSeite6.RowAC row : s.getPruefungAC()) {
                text(cs,
                        "8." + nr
                                + threeChecks(row.getJa(), row.getNein(), row.getNz())
                                + "  s.B/M:" + checkbox(Boolean.TRUE.equals(row.getSbm()))
                                + "  s.Beiblatt:" + checkbox(Boolean.TRUE.equals(row.getSbeiblatt())),
                        40, y, 9);
                y -= 15;
                nr++;
            }

            y -= 10;

            // Zusatz-Tabelle AC
            drawZusatzTabelle(cs, s.getZusatzAC(), "Zusatz-Tabelle AC-Verteiler", y);
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
