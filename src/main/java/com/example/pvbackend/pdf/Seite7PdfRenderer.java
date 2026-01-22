package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite7;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.pvbackend.util.PdfRenderUtils.*;

@Component
public class Seite7PdfRenderer {

    public void render(PDDocument doc, WartungsprotokollSeite7 s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            // Header
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 7 – Zähler & Überwachung");
            cs.endText();

            float y = 740;

            // SECTION 9 – Prüfung Zähler
            text(cs, "SECTION 9 – Prüfung Zähler (9.1 – 9.4)", 40, y, 11);
            y -= 20;


            for (int i = 0; i < ZAEHLER_LABELS.length; i++) {
                WartungsprotokollSeite7.ZaehlerRow row = s.getPruefungZaehler().get(i);

                y = drawCheckTriple(
                        cs,
                        "9." + (i + 1) + " " + ZAEHLER_LABELS[i],
                        row.getJa(),
                        row.getNein(),
                        row.getNz(),
                        y
                );

                cs.moveTo(40, y + 8);
                cs.lineTo(555, y + 8);
                cs.stroke();

                y -= 11;

            }

            y -= 10;

            // Zusatz-Tabelle Zähler
            y = drawZusatzTabelle(cs, s.getZusatzZaehler(), "Zusatz-Tabelle Zähler", y);
            y -= 10;

            // SECTION 10 – Überwachungssystem
            text(cs, "SECTION 10 – Prüfung Überwachungssystem (10.1 – 10.10)", 40, y, 11);
            y -= 20;


            for (int i = 0; i < UEBERWACHUNG_LABELS.length; i++) {
                WartungsprotokollSeite7.UeberwachungRow row = s.getPruefungUeberwachung().get(i);

                y = drawCheckTriple(
                        cs,
                        "10." + (i + 1) + " " + UEBERWACHUNG_LABELS[i],
                        row.getJa(),
                        row.getNein(),
                        row.getNz(),
                        y
                );

                cs.moveTo(40, y + 8);
                cs.lineTo(555, y + 8);
                cs.stroke();

                y -= 11;
            }

            y -= 10;

            // Zusatz-Tabelle Überwachung
            drawZusatzTabelle(cs, s.getZusatzUeberwachung(), "Zusatz-Tabelle Überwachung", y);
        }
    }

    private void text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
        cs.setFont(PDType1Font.HELVETICA, size);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(txt == null ? "" : txt);
        cs.endText();
    }



    private static final String[] ZAEHLER_LABELS = {
            "Der Zähler ist funktionsfähig",
            "Zählerstand 2.8.0 (alternativ 2.8.1 und 2.8.2, oder Lieferung PV)",
            "Umrechnungsfaktor Zähler",
            "Zähler Nr."
    };

    private static final String[] UEBERWACHUNG_LABELS = {
            "Die Datenlogger sind in Betrieb ohne Störungsmeldungen",
            "Der Router ist in Betrieb ohne Störungsmeldungen",
            "Die ÜW-Schnittstelle ist in Betrieb ohne Störungsmeldungen",
            "Das Netzwerk ist in Betrieb",
            "Die Überwachungsverteiler sind frei von Schäden und Feuchtigkeit",
            "Die Beschriftung der ÜW-Verteiler ist in Ordnung",
            "Die Werte aller WR werden übertragen (prüfen mit Überwachungszentrale)",
            "Die Einstrahlungssensoren und deren Kabel sind frei von Schäden",
            "Die Oberfläche der Einstrahlungssensoren wurde gereinigt",
            "Die Temperatursensoren und deren Kabel sind frei von Schäden"
    };



}