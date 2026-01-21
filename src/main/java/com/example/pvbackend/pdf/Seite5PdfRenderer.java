package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite5;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;


import static com.example.pvbackend.util.PdfRenderUtils.drawCheckTriple;

import static com.example.pvbackend.util.PdfRenderUtils.*;

@Component
public class Seite5PdfRenderer {

    public void render(PDDocument doc, WartungsprotokollSeite5 s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            // Header
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 5 – Messungen PV & GAKs");
            cs.endText();

            float y = 740;

            // SECTION 5 – Messungen PV-Anlage
            text(cs, "SECTION 5 – Messungen PV-Anlage", 40, y, 11);
            y -= 20;

            y = drawCheckTriple(cs, "5.1 Modultragende Teile gemessen",
                    s.getMessungenPV().get(0).getJa(),
                    s.getMessungenPV().get(0).getNein(),
                    s.getMessungenPV().get(0).getNz(), y);

            cs.moveTo(40, y);
            cs.lineTo(555, y);
            cs.stroke();

            y = drawCheckTriple(cs, "5.2 Alle Modulstränge gemessen",
                    s.getMessungenPV().get(1).getJa(),
                    s.getMessungenPV().get(1).getNein(),
                    s.getMessungenPV().get(1).getNz(), y);

            cs.moveTo(40, y);
            cs.lineTo(555, y);
            cs.stroke();

            y = drawCheckTriple(cs, "5.3 Erdungswiderstand gemessen",
                    s.getMessungenPV().get(2).getJa(),
                    s.getMessungenPV().get(2).getNein(),
                    s.getMessungenPV().get(2).getNz(), y);

            cs.moveTo(40, y);
            cs.lineTo(555, y);
            cs.stroke();

            y = drawCheckTriple(cs, "5.4 Erdung durchgeführt",
                    s.getMessungenPV().get(3).getJa(),
                    s.getMessungenPV().get(3).getNein(),
                    s.getMessungenPV().get(3).getNz(), y);

            cs.moveTo(40, y);
            cs.lineTo(555, y);
            cs.stroke();


            y = drawCheckTriple(cs, "5.5 Nach DIN 62446 geprüft",
                    s.getMessungenPV().get(4).getJa(),
                    s.getMessungenPV().get(4).getNein(),
                    s.getMessungenPV().get(4).getNz(), y);

            cs.moveTo(40, y);
            cs.lineTo(555, y);
            cs.stroke();


            y -= 10;

            // Zusatz-Tabelle #1
            y = drawZusatzTabelle(cs, s.getZusatz1(), "Zusatz-Tabelle #1", y);
            y -= 10;

            // SECTION 6 – Prüfung GAKs
            text(cs, "SECTION 6 – Prüfung GAKs", 40, y, 11);
            y -= 20;

            y = drawCheckThree(cs, "6.1  Alle GAKs sind frei und zugänglich",
                    s.getPruefungGAKs().get(0).getJa(),
                    s.getPruefungGAKs().get(0).getNein(),
                    s.getPruefungGAKs().get(0).getNz(), y);


            cs.moveTo(40, y);
            cs.lineTo(555, y);
            cs.stroke();


            y = drawCheckThree(cs, "6.2 Die Strangsicherungen sind funktionsfähig",
                    s.getPruefungGAKs().get(1).getJa(),
                    s.getPruefungGAKs().get(1).getNein(),
                    s.getPruefungGAKs().get(1).getNz(), y);


            cs.moveTo(40, y);
            cs.lineTo(555, y);
            cs.stroke();


            y = drawCheckThree(cs, "6.3 Die ÜSS-Einrichtungen sind funktionsfähig",
                    s.getPruefungGAKs().get(2).getJa(),
                    s.getPruefungGAKs().get(2).getNein(),
                    s.getPruefungGAKs().get(2).getNz(), y);


            cs.moveTo(40, y);
            cs.lineTo(555, y);
            cs.stroke();


            y = drawCheckThree(cs, "6.4 Die Schalter sind funktionsfähig",
                    s.getPruefungGAKs().get(3).getJa(),
                    s.getPruefungGAKs().get(3).getNein(),
                    s.getPruefungGAKs().get(3).getNz(), y);

            cs.moveTo(40, y);
            cs.lineTo(555, y);
            cs.stroke();



            y = drawCheckThree(cs, "6.5 Die GAKs sind frei von Schäden",
                    s.getPruefungGAKs().get(4).getJa(),
                    s.getPruefungGAKs().get(4).getNein(),
                    s.getPruefungGAKs().get(4).getNz(), y);

            cs.moveTo(40, y);
            cs.lineTo(555, y);
            cs.stroke();


            y = drawCheckThree(cs, "6.6 Die GAKs sind frei von Feuchtigkeit",
                    s.getPruefungGAKs().get(5).getJa(),
                    s.getPruefungGAKs().get(5).getNein(),
                    s.getPruefungGAKs().get(5).getNz(), y);


            cs.moveTo(40, y);
            cs.lineTo(555, y);
            cs.stroke();


            y = drawCheckThree(cs, "6.7 Der innere Zustand der GAKs ist in Ordnung",
                    s.getPruefungGAKs().get(6).getJa(),
                    s.getPruefungGAKs().get(6).getNein(),
                    s.getPruefungGAKs().get(6).getNz(), y);

            cs.moveTo(40, y);
            cs.lineTo(555, y);
            cs.stroke();


            y = drawCheckThree(cs, "6.8 Die Erdung der Verteiler ist frei von Schäden und richtig montiert",
                    s.getPruefungGAKs().get(7).getJa(),
                    s.getPruefungGAKs().get(7).getNein(),
                    s.getPruefungGAKs().get(7).getNz(), y);


            cs.moveTo(40, y);
            cs.lineTo(555, y);
            cs.stroke();



            y = drawCheckThree(cs, "6.9 Die Beschriftung ist vollständig und i.O.",
                    s.getPruefungGAKs().get(8).getJa(),
                    s.getPruefungGAKs().get(8).getNein(),
                    s.getPruefungGAKs().get(8).getNz(), y);


            cs.moveTo(40, y);
            cs.lineTo(555, y);
            cs.stroke();


            y = drawCheckThree(cs, "6.10 Die Kabelverschraubungen sind fest und dicht",
                    s.getPruefungGAKs().get(9).getJa(),
                    s.getPruefungGAKs().get(9).getNein(),
                    s.getPruefungGAKs().get(9).getNz(), y);


            cs.moveTo(40, y);
            cs.lineTo(555, y);
            cs.stroke();


            y = drawCheckThree(cs, "6.11 Die Anzugsmomente aller Schrauben sind korrekt",
                    s.getPruefungGAKs().get(10).getJa(),
                    s.getPruefungGAKs().get(10).getNein(),
                    s.getPruefungGAKs().get(10).getNz(), y);


            cs.moveTo(40, y);
            cs.lineTo(555, y);
            cs.stroke();


            y = drawCheckThree(cs, "6.12 Die GAKs wurden gereinigt",
                    s.getPruefungGAKs().get(11).getJa(),
                    s.getPruefungGAKs().get(11).getNein(),
                    s.getPruefungGAKs().get(11).getNz(), y);


            cs.moveTo(40, y);
            cs.lineTo(555, y);
            cs.stroke();


            y = drawCheckThree(cs, "6.13 Keine Auffälligkeiten aus Thermografieuntersuchung",
                    s.getPruefungGAKs().get(12).getJa(),
                    s.getPruefungGAKs().get(12).getNein(),
                    s.getPruefungGAKs().get(12).getNz(), y);


            cs.moveTo(40, y);
            cs.lineTo(555, y);
            cs.stroke();


            // … remaining drawCheckFive calls unchanged …

            drawZusatzTabelle(cs, s.getZusatz2(), "Zusatz-Tabelle #2", y);
        }
    }
}