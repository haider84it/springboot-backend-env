package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite3;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Seite3PdfRenderer {

    public void render(PDDocument doc, WartungsprotokollSeite3 s) throws IOException {

        PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 3 – Module & Anlage");
            cs.endText();

            float y = 740;

            text(cs, "Modulzustand:", 40, y, 11);
            y -= 20;

            int index = 1;
            for (var row : s.getModuleZustand()) {
                text(
                        cs,
                        index + ") Ja:" + checkbox(Boolean.TRUE.equals(row.getJa())) +
                                "  Nein:" + checkbox(Boolean.TRUE.equals(row.getNein())) +
                                "  n.z:" + checkbox(Boolean.TRUE.equals(row.getNz())),
                        40,
                        y,
                        9
                );
                y -= 15;
                index++;
            }
        }
    }

    private void text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
        cs.setFont(PDType1Font.HELVETICA, size);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(txt == null ? "" : txt);
        cs.endText();
    }

    // Convert boolean → checkbox symbol
    private String checkbox(boolean b) {
        return b ? "[X]" : "[ ]";
    }
}