package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite10;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.pvbackend.util.PdfRenderUtils.*;

@Component
public class Seite10PdfRenderer {

    public void render(PDDocument doc, WartungsprotokollSeite10 s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            // Header
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 10 – MSP erweitert");
            cs.endText();

            float y = 740;

            // SECTION 14 – Prüfung Mittelspannungsanlagen
            text(cs, "SECTION 14 – Prüfung Mittelspannungsanlagen (14.1 – 14.29)", 40, y, 11);
            y -= 20;

            for (int i = 0; i < MSP_LABELS.length; i++) {
                WartungsprotokollSeite10.MSPRow row = s.getPruefungMSP().get(i);

                y = drawCheckTriple(
                        cs,
                        "14." + (i + 1) + " " + MSP_LABELS[i],
                        row.getJa(), row.getNein(), row.getNz(),
                        y
                );


                cs.moveTo(40, y - 2);
                cs.lineTo(555, y - 2);
                cs.stroke();

                y -= 15;
            }

            y -= 10;

            // Zusatz-Tabelle MSP
            drawZusatzTabelle(cs, s.getZusatzMSP(), "Zusatz-Tabelle MSP", y);
        }
    }

    private void text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
        cs.setFont(PDType1Font.HELVETICA, size);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(txt == null ? "" : txt);
        cs.endText();
    }


    private static final String[] MSP_LABELS = {
            "Die Gebäude der MSP-Anlagen sind frei und zugänglich",
            "Die Gebäude der MSP-Anlagen befinden sich in gutem Zustand",
            "Innenraum der Gebäude in Ordnung (Verschmutzung, Feuchtigkeit, usw.)",
            "Türen in Ordnung (Dichtungen, Scharniere, Verriegelungen)",
            "Heizung vorhanden und betriebsbereit",
            "Hygrostat in Ordnung",
            "Reinigung Luftkanal, Insektenschutzgitter und Filter durchgeführt",
            "Lüfter in Ordnung",
            "Reinigung Lüfter durchgeführt",
            "Stationsbeleuchtung und Steckdosen in Ordnung",
            "Die Kabelverschraubungen sind fest und dicht",
            "Die Anzugsmomente aller Schrauben sind richtig (Kabelanschlüsse)",
            "Sammelschienen und Isolatoren sind in Ordnung (bei Bedarf gereinigt)",
            "Klemmenverbindung der Leistungskabelung in Ordnung",
            "Steck-/Klemmverbindung der Strangkabelung in Ordnung",
            "Erdverbindungen in Ordnung",
            "Sicherungen in Ordnung",
            "Isolierungen in Ordnung",
            "Berührungsschutz vorhanden und in Ordnung",
            "Erdungsscharnitur, Betätigungshebel für Schaltanlage, Spannungsprüfer i.O.",
            "Notfallplan vorhanden und aktuell",
            "Warnschilder und Beschriftungen vorhanden und i.O.",
            "NSHV in Ordnung",
            "Gasdruck der SF6-Schaltanlage in Ordnung",
            "Trafo, Öl, Kühlung, Flüssigkeitsstand in Ordnung",
            "Netz- und Anlagenschutz in Betrieb ohne Fehlermeldungen",
            "Versorgungsspannung in Ordnung",
            "USV funktionsfähig, Batterien in Ordnung (Schutzgerät, usw.)",
            "Parkregelung in Betrieb und ohne Fehlermeldungen"
    };



}
