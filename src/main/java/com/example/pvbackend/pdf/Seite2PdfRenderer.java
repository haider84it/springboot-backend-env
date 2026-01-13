package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite2;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import static com.example.pvbackend.util.PdfRenderUtils.safe;

import java.io.IOException;

@Component
public class Seite2PdfRenderer {

    public void render(PDDocument doc, WartungsprotokollSeite2 s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 2 – Zugang & Arbeitszeiten");
            cs.endText();

            float y = 740;

            text(cs, "Zugangsschlüssel vorhanden: " +
                    checkbox(Boolean.TRUE.equals(s.getZugangsschluesselVorhanden())), 40, y, 10);
            y -= 15;
            text(cs, "Anmerkung: " + safe(s.getZugangsschluesselAnmerkung()), 40, y, 10);
            y -= 15;

            text(cs, "Thermokamera vorhanden: " +
                    checkbox(Boolean.TRUE.equals(s.getThermoKameraVorhanden())), 40, y, 10);
            y -= 15;
            text(cs, "Anmerkung: " + safe(s.getThermoKameraAnmerkung()), 40, y, 10);
            y -= 15;

            text(cs, "Betreiber kontaktiert: " +
                    checkbox(Boolean.TRUE.equals(s.getBetreiberKontaktiert())), 40, y, 10);
            y -= 15;
            text(cs, "Anmerkung: " + safe(s.getBetreiberAnmerkung()), 40, y, 10);
            y -= 15;

            text(cs, "Eigentümer kontaktiert: " +
                    checkbox(Boolean.TRUE.equals(s.getEigentuemerKontaktiert())), 40, y, 10);
            y -= 15;
            text(cs, "Anmerkung: " + safe(s.getEigentuemerAnmerkung()), 40, y, 10);
            y -= 15;

            text(cs, "Elektrofachkraft Name: " + safe(s.getElektrofachkraftName()), 40, y, 10);
            y -= 25;

            text(cs, "Arbeitszeiten:", 40, y, 11);
            y -= 20;

            for (var r : s.getArbeitszeiten()) {
                text(cs,
                        safe(r.getDatum()) + "  " +
                                safe(r.getName()) + "  " +
                                "Beginn: " + safe(r.getBeginn()) + "  " +
                                "Ende: " + safe(r.getEnde()) + "  " +
                                "Std: " + safe(r.getStunden()) + "  " +
                                "Wetter: " + safe(r.getWetter()) + "  " +
                                "Temp: " + safe(r.getTemperatur()),
                        40, y, 9
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

    // Convert boolean → checkbox symbol
    private String checkbox(boolean b) {
        return b ? "[X]" : "[ ]";
    }
}