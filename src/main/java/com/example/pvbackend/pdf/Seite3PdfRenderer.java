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


    private static final String[] VERSCHATTUNG_LABELS = {
            "1.1 Die Anlage ist frei von dauerhafter Umgebungsverschattung (zB Aufbauten) \n",
            "1.2 Die Anlage ist frei von temporärer Umgebungsverschattung (zB Bewuchs) \n",
            "1.3 Die Anlage ist frei von eigener Verschattung \n"
    };


    private static final String[] MODULZUSTAND_LABELS = {
            "2.1 Die Oberfläche der Module ist frei von Beschädigungen \n",
            "2.2 Die Rahmen der Module sind frei von Beschädigungen  \n",
            "2.3 Die Glasoberfläche der Module ist frei von Verschmutzung (Foto!) \n",
            "2.4 Die Zellen in den Modulen sind frei von Auffälligkeiten \n",
            "2.5 Die Einbettungsfolie ist frei von Auffälligkeiten \n",
            "2.6 Die Rückseitenfolie der Module ist frei von Beschädigungen \n",
            "2.7 Die Modulanschlussdosen sind frei von Auffälligkeiten \n",
            "2.8 Die Typenschilder und SN-Aufkleber sind frei von Auffälligkeiten \n",
            "2.9 Die Module wurden gereinigt \n"
    };




    public void render(PDDocument doc, WartungsprotokollSeite3 s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {



            //System.out.println("ModuleZustand size = " + s.getModuleZustand().size());

            cs.setFont(PDType1Font.HELVETICA_BOLD, 11);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 3 – Module & Anlage");
            cs.endText();

            float y = 740;

            // before using the lists
            while (s.getVerschattung().size() < VERSCHATTUNG_LABELS.length) {
                s.getVerschattung().add(new WartungsprotokollSeite3.CheckRow3());
            }

            while (s.getAnlageZustand().size() < MODULZUSTAND_LABELS.length) {
                s.getAnlageZustand().add(new WartungsprotokollSeite3.CheckRow5State3());
            }

            // 1. Kontrolle der Anlage auf Verschattung
            text(cs, "1 Kontrolle der Anlage auf Verschattung", 40, y, 11);
            y -= 20;

            for (int i = 0; i < VERSCHATTUNG_LABELS.length; i++) {
                var row = s.getVerschattung().get(i);
                y = yesNoNzRow(
                        cs,
                        40,
                        y,
                        VERSCHATTUNG_LABELS[i],
                        Boolean.TRUE.equals(row.getJa()),
                        Boolean.TRUE.equals(row.getNein()),
                        Boolean.TRUE.equals(row.getNz())
                );
                y -= 19;

            }

// spacing between sections
            y -= 10;

// 2. Sichtkontrolle der Module
            text(cs, "2 Sichtkontrolle der Module auf Beschädigung und Verschmutzung", 40, y, 11);
            y -= 20;

            for (int i = 0; i < MODULZUSTAND_LABELS.length; i++) {
                var row = s.getAnlageZustand().get(i);
                y = yesNoNzRow(
                        cs,
                        40,
                        y,
                        MODULZUSTAND_LABELS[i],
                        Boolean.TRUE.equals(row.getJa()),
                        Boolean.TRUE.equals(row.getNein()),
                        Boolean.TRUE.equals(row.getNz())
                );
                y -= 19;

            }
        }
    }


    private float text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
        cs.setFont(PDType1Font.HELVETICA, size);

        float lineHeight = size + 2; // spacing between lines

        String[] lines = (txt == null ? "" : txt).split("\\n");

        for (String line : lines) {
            cs.beginText();
            cs.newLineAtOffset(x, y);
            cs.showText(line);
            cs.endText();
            y -= lineHeight;
        }

        return y; // return new y after drawing all lines
    }


    private void drawCheckbox(PDPageContentStream cs, float x, float y, boolean checked) throws IOException {
        float size = 8;
        float yOffset = 2;

        cs.addRect(x, y - size + yOffset, size, size);
        cs.stroke();

        if (checked) {
            cs.moveTo(x + 1, y - 1 + yOffset);
            cs.lineTo(x + size - 1, y - size + 1 + yOffset);
            cs.moveTo(x + 1, y - size + 1 + yOffset);
            cs.lineTo(x + size - 1, y - 1 + yOffset);
            cs.stroke();
        }
    }

    private float yesNoNzRow(
            PDPageContentStream cs,
            float x,
            float y,
            String label,
            boolean ja,
            boolean nein,
            boolean nz
    ) throws IOException {

        float startY = y;

        // draw multiline label
        float newY = text(cs, label, x, y, 9);

        float col = x + 340;

        drawCheckbox(cs, col, startY, ja);
        text(cs, "Ja", col + 12, startY, 9);

        drawCheckbox(cs, col + 50, startY, nein);
        text(cs, "Nein", col + 62, startY, 9);

        drawCheckbox(cs, col + 100, startY, nz);
        text(cs, "n.z.", col + 112, startY, 9);

        return newY;
    }


}