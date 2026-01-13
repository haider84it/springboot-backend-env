package com.example.pvbackend.pdf;

import com.example.pvbackend.model.*;
import com.example.pvbackend.service.WartungsprotokollService;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;



@Service
@RequiredArgsConstructor
public class PdfService {

    private final WartungsprotokollService wartungsprotokollService;
    private final Seite1PdfRenderer seite1PdfRenderer;
    private final Seite2PdfRenderer seite2PdfRenderer;
    private final Seite3PdfRenderer seite3PdfRenderer;
    private final Seite4PdfRenderer seite4PdfRenderer;
    private final Seite5PdfRenderer seite5PdfRenderer;
    private final Seite6PdfRenderer seite6PdfRenderer;
    private final Seite7PdfRenderer seite7PdfRenderer;
    private final Seite8PdfRenderer seite8PdfRenderer;
    private final Seite9PdfRenderer seite9PdfRenderer;
    private final Seite10PdfRenderer seite10PdfRenderer;
    private final Seite11PdfRenderer seite11PdfRenderer;
    private final Seite11bPdfRenderer seite11bPdfRenderer;
    private final Seite12PdfRenderer seite12PdfRenderer;

    // PUBLIC: Generate PDF for one Protokoll

    public byte[] generate(Long id) {
        Wartungsprotokoll p = wartungsprotokollService.findById(id)
                .orElseThrow(() -> new RuntimeException("Protokoll not found"));

        try (PDDocument doc = new PDDocument()) {

            if (p.getSeite1() != null && p.getSeite1().hasContent()) seite1PdfRenderer.render(doc, p.getSeite1());
            if (p.getSeite2() != null && p.getSeite2().hasContent()) seite2PdfRenderer.render(doc, p.getSeite2());
            if (p.getSeite3() != null && p.getSeite3().hasContent()) seite3PdfRenderer.render(doc, p.getSeite3());
            if (p.getSeite4() != null && p.getSeite4().hasContent()) seite4PdfRenderer.render(doc, p.getSeite4());
            if (p.getSeite5() != null && p.getSeite5().hasContent()) seite5PdfRenderer.render(doc, p.getSeite5());
            if (p.getSeite6() != null && p.getSeite6().hasContent()) seite6PdfRenderer.render(doc, p.getSeite6());
            if (p.getSeite7() != null && p.getSeite7().hasContent()) seite7PdfRenderer.render(doc, p.getSeite7());
            if (p.getSeite8() != null && p.getSeite8().hasContent()) seite8PdfRenderer.render(doc, p.getSeite8());
            if (p.getSeite9() != null && p.getSeite9().hasContent()) seite9PdfRenderer.render(doc, p.getSeite9());
            if (p.getSeite10() != null && p.getSeite10().hasContent()) seite10PdfRenderer.render(doc, p.getSeite10());
            if (p.getSeite11() != null && p.getSeite11().hasContent()) seite11PdfRenderer.render(doc, p.getSeite11());
            if (p.getSeite11b() != null && p.getSeite11b().hasContent())  seite11bPdfRenderer.render(doc, p.getSeite11b());
            if (p.getSeite12() != null && p.getSeite12().hasContent()) seite12PdfRenderer.render(doc, p.getSeite12());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            doc.save(baos);
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("PDF generation error", e);
        }
    }

    // PAGE HELPERS
    private PDPageContentStream startPage(PDDocument doc, String title) throws IOException {

        PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
        doc.addPage(page);

        PDPageContentStream cs = new PDPageContentStream(doc, page);
        cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
        cs.beginText();
        cs.newLineAtOffset(40, 780);
        cs.showText(title);
        cs.endText();

        return cs;
    }

    private void drawLine(PDPageContentStream cs, float x1, float y1, float x2, float y2) throws IOException {
        cs.moveTo(x1, y1);
        cs.lineTo(x2, y2);
        cs.stroke();
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