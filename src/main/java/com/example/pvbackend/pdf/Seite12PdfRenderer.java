package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite12;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.pvbackend.util.PdfRenderUtils.*;

@Component
public class Seite12PdfRenderer {

    public void render(PDDocument doc, WartungsprotokollSeite12 s) throws IOException {

        PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            // Header
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 12 – Empfehlungen");
            cs.endText();

            float y = 740;

            text(cs, "Empfehlungen", 40, y, 11);
            y -= 25;

            int nr = 1;
            for (WartungsprotokollSeite12.EmpfehlungRow row : s.getEmpfehlungen()) {

                text(cs, nr + ") Zu Nr.: " + safe(row.getZunr()), 40, y, 9);
                y -= 12;

                text(cs, "   Empfehlung: " + safe(row.getEmpfehlung()), 40, y, 9);
                y -= 12;

                text(cs,
                        "   Sicherheit: " + checkbox(Boolean.TRUE.equals(row.getSicherheit())) +
                                "   Ertrag: " + checkbox(Boolean.TRUE.equals(row.getErtrag())) +
                                "   Erhaltung: " + checkbox(Boolean.TRUE.equals(row.getErhaltung())),
                        40, y, 9);
                y -= 12;

                text(cs, "   Kostenschätzung: " + safe(row.getKosten()), 40, y, 9);
                y -= 18;

                nr++;
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
