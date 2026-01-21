package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite8;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;
import static com.example.pvbackend.util.PdfRenderUtils.*;


@Component
public class Seite8PdfRenderer {

    public void render(PDDocument doc, WartungsprotokollSeite8 s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            // Header
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 8 – Außenanlage & Diebstahl");
            cs.endText();

            float y = 740;

            // SECTION 11 – Prüfung Außenanlage
            text(cs, "SECTION 11 – Prüfung Außenanlage (11.1 – 11.9)", 40, y, 11);
            y -= 20;

            for (int i = 0; i < AUSSEN_LABELS.length; i++) {
                WartungsprotokollSeite8.AussenRow row = s.getPruefungAussen().get(i);

                y = drawCheckTriple(
                        cs,
                        "11." + (i + 1) + " " + AUSSEN_LABELS[i],
                        row.getJa(), row.getNein(), row.getNz(),
                        y
                );

                cs.moveTo(40, y - 2);
                cs.lineTo(555, y - 2);
                cs.stroke();

                y -= 15;
            }

            y -= 10;

            // Zusatz-Tabelle Außenanlage
            y = drawZusatzTabelle(cs, s.getZusatzAussen(), "Zusatz-Tabelle Außenanlage", y);
            y -= 10;

            // SECTION 12 – Prüfung Diebstahlschutz
            text(cs, "SECTION 12 – Prüfung Diebstahlschutz (12.1 – 12.5)", 40, y, 11);
            y -= 20;

            for (int i = 0; i < DIEBSTAHL_LABELS.length; i++) {
                WartungsprotokollSeite8.DiebstahlRow row = s.getPruefungDiebstahl().get(i);

                y = drawCheckTriple(
                        cs,
                        "12." + (i + 1) + " " + DIEBSTAHL_LABELS[i],
                        row.getJa(), row.getNein(), row.getNz(),
                        y
                );

                cs.moveTo(40, y - 2);
                cs.lineTo(555, y - 2);
                cs.stroke();

                y -= 15;
            }

            y -= 10;

            // Zusatz-Tabelle Diebstahlschutz
            drawZusatzTabelle(cs, s.getZusatzDiebstahl(), "Zusatz-Tabelle Diebstahlschutz", y);
        }
    }

    private void text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
        cs.setFont(PDType1Font.HELVETICA, size);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(txt == null ? "" : txt);
        cs.endText();
    }


    private static final String[] AUSSEN_LABELS = {
            "Die Zaunanlage ist frei von Schäden",
            "Tore und Schließmechanismus sind in Ordnung",
            "Das Gelände befindet sich in gutem Zustand",
            "Die Straßen im Gelände befinden sich in gutem Zustand",
            "Die Zufahrten befinden sich in gutem Zustand, sind frei und zugänglich",
            "Der Bewuchs der Anlage ist in Ordnung, auch bei WR, Verteiler, Gebäude",
            "Die Anlagengebäude befinden sich in gutem Zustand",
            "Das Dach / die Eindeckung befindet sich in gutem Zustand",
            "Die Anlagenbeleuchtung befindet sich in gutem Zustand"
    };

    private static final String[] DIEBSTAHL_LABELS = {
            "Die Videokameras sind frei von Schäden",
            "Die Videokameras sind richtig ausgerichtet und werden nicht bedeckt",
            "Die Datenübertragung wurde bestätigt",
            "Die Alarmanlage ist funktionsfähig (Zu- und Abschaltung)",
            "Die Sensoren sind frei von Beschädigungen und funktionsfähig"
    };


}