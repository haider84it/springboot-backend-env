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
            "1.1 Die Anlage ist frei von dauerhafter Umgebungsverschattung (zB Aufbauten)",
            "1.2 Die Anlage ist frei von temporärer Umgebungsverschattung (zB Bewuchs)",
            "1.3 Die Anlage ist frei von eigener Verschattung"
    };


    private static final String[] MODULZUSTAND_LABELS = {
            "2.1 Die Oberfläche der Module ist frei von Beschädigungen",
            "2.2 Die Rahmen der Module sind frei von Beschädigungen",
            "2.3 Die Glasoberfläche der Module ist frei von Verschmutzung (Foto!)",
            "2.4 Die Zellen in den Modulen sind frei von Auffälligkeiten",
            "2.5 Die Einbettungsfolie ist frei von Auffälligkeiten",
            "2.6 Die Rückseitenfolie der Module ist frei von Beschädigungen",
            "2.7 Die Modulanschlussdosen sind frei von Auffälligkeiten",
            "2.8 Die Typenschilder und SN-Aufkleber sind frei von Auffälligkeiten",
            "2.9 Die Module wurden gereinigt"
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
                yesNoNzRow(
                        cs,
                        40,
                        y,
                        VERSCHATTUNG_LABELS[i],
                        Boolean.TRUE.equals(row.getJa()),
                        Boolean.TRUE.equals(row.getNein()),
                        Boolean.TRUE.equals(row.getNz())
                );
                y -= 15;
            }

// spacing between sections
            y -= 10;

// 2. Sichtkontrolle der Module
            text(cs, "2 Sichtkontrolle der Module auf Beschädigung und Verschmutzung", 40, y, 11);
            y -= 20;

            for (int i = 0; i < MODULZUSTAND_LABELS.length; i++) {
                var row = s.getAnlageZustand().get(i);
                yesNoNzRow(
                        cs,
                        40,
                        y,
                        MODULZUSTAND_LABELS[i],
                        Boolean.TRUE.equals(row.getJa()),
                        Boolean.TRUE.equals(row.getNein()),
                        Boolean.TRUE.equals(row.getNz())
                );
                y -= 15;
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

    private void yesNoNzRow(
            PDPageContentStream cs,
            float x,
            float y,
            String label,
            boolean ja,
            boolean nein,
            boolean nz
    ) throws IOException {

        text(cs, label, x, y, 9);

        drawCheckbox(cs, x + 280, y, ja);
        text(cs, "Ja", x + 292, y, 9);

        drawCheckbox(cs, x + 330, y, nein);
        text(cs, "Nein", x + 342, y, 9);

        drawCheckbox(cs, x + 380, y, nz);
        text(cs, "n.z.", x + 392, y, 9);
    }


}