package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite3;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static com.example.pvbackend.util.PdfRenderUtils.safe;
import static io.micrometer.common.util.StringUtils.isBlank;
import static org.springframework.util.ObjectUtils.isEmpty;


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
                y -= 27;

            }

// spacing between sections
            y -= 10;

            // ZUSATZ-TABELLE #1
            drawZusatzTabelle(doc, cs, s.getZusatz1(), "Zusatz-Tabelle #2", y);
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
                y -= 27;

            }


            y -= 10;

            drawZusatzTabelle(doc, cs, s.getZusatz2(), "Zusatz-Tabelle #2", y);


        }
    }

    public static float drawZusatzTabelle(PDDocument doc, PDPageContentStream cs, List<? extends Object> list, String title, float y) throws IOException {

        text(cs, title, 40, y, 11);
        y -= 20;



        for (Object o : list) {

            // ✅ PAGE BREAK CHECK (put here)
            if (y < 120) {
                cs.close();

                PDPage newPage = new PDPage(PDRectangle.A4);
                doc.addPage(newPage);

                cs = new PDPageContentStream(doc, newPage);

                y = 740; // reset top
                text(cs, title, 40, y, 11); // optional: redraw table title
                y -= 20;
            }



            if (!(o instanceof WartungsprotokollSeite3.Zusatz1Row z)) continue;

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


    private static float text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
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

        float checkboxY = startY + 5; // move up (try 2–5)

        drawCheckbox(cs, col, checkboxY, ja);
        text(cs, "Ja", col + 12, startY, 9);

        drawCheckbox(cs, col + 50, checkboxY, nein);
        text(cs, "Nein", col + 62, startY, 9);

        drawCheckbox(cs, col + 100, checkboxY, nz);
        text(cs, "n.z.", col + 112, startY, 9);

        return newY;
    }

    private static String checkbox(boolean b) {
        return b ? "[X]" : "[ ]";
    }




}