package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite11;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.pvbackend.util.PdfRenderUtils.*;



@Component
public class Seite11PdfRenderer {

    public void render(PDDocument doc, WartungsprotokollSeite11 s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            // Header
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 11 – MSP Sichtprüfung + Sonstiges");
            cs.endText();

            float y = 740;

            // SECTION 15 – Sichtprüfung Mittelspannungsanlagen
            text(cs, "SECTION 15 – Sichtprüfung Mittelspannungsanlagen (15.1 – 15.4)", 40, y, 11);
            y -= 20;

            for (int i = 0; i < MS_SICHT_LABELS.length; i++) {
                var row = s.getPruefungMSsicht().get(i);
                y = drawCheckTriple(cs,
                        "15." + (i + 1) + " " + MS_SICHT_LABELS[i],
                        row.getJa(), row.getNein(), row.getNz(),
                        y
                );

                y -= 9; // more space under the text


                cs.moveTo(40, y );
                cs.lineTo(555, y );
                cs.stroke();


                y -= 12; // space after the line


            }



            y -= 10;

            // Zusatz-Tabelle Sichtprüfung MS
            y = drawZusatzTabelle(cs, s.getZusatzMSsicht(), "Zusatz-Tabelle Sichtprüfung MS", y);
            y -= 10;

            // SECTION 16 – Sonstiges
            text(cs, "SECTION 16 – Sonstiges (16.1 – 16.4)", 40, y, 11);
            y -= 20;


            for (int i = 0; i < SONSTIGES_LABELS.length; i++) {
                var row = s.getSonstiges().get(i);
                y = drawCheckTriple(cs,
                        "16." + (i + 1) + " " + SONSTIGES_LABELS[i],
                        row.getJa(), row.getNein(), row.getNz(),
                        y
                );

                y -= 9; // more space under the text

                cs.moveTo(40, y);
                cs.lineTo(555, y);
                cs.stroke();

                y -= 12; // space after the line
            }



            y -= 10;

            // Zusatz-Tabelle Sonstiges
            drawZusatzTabelle(cs, s.getZusatzSonstiges(), "Zusatz-Tabelle Sonstiges", y);
        }
    }

    private void text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
        cs.setFont(PDType1Font.HELVETICA, size);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(txt == null ? "" : txt);
        cs.endText();
    }

    private static final String[] MS_SICHT_LABELS = {
            "Sichtprüfung der Trafostation durchgeführt, ohne Beanstandungen",
            "Sichtprüfung der Übergabestation durchgeführt, ohne Beanstandungen",
            "Die Anzugsmomente aller Schrauben sind richtig (Kabelanschlüsse)",
            "Reinigung Station / Lüftungswege u.ä. (ohne Abschaltung) ausgeführt"
    };

    private static final String[] SONSTIGES_LABELS = {
            "Warnschilder für PV-Anlage und Komp. vorhanden und in Ordnung",
            "Brandschutzkonzept ist vorhanden und aktuell (Pläne, usw.)",
            "Brandschutzmaßnahmen i. O. (Feuerlöscher, Schlüssel, usw.)",
            "Blitzschutzkonzept vorhanden, Maßnahmen in Ordnung"
    };


}
