package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite9;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.pvbackend.util.PdfRenderUtils.*;

@Component
public class Seite9PdfRenderer {

    public void render(PDDocument doc, WartungsprotokollSeite9 s) throws IOException {

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

            // Header
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(40, 780);
            cs.showText("Seite 9 – Zentralwechselrichter");
            cs.endText();

            float y = 740;

            // SECTION 13 – Zentralwechselrichter
            text(cs, "SECTION 13 – Zentralwechselrichter (13.1 – 13.23)", 40, y, 11);
            y -= 20;

            for (int i = 0; i < ZENTRAL_WR_LABELS.length; i++) {
                WartungsprotokollSeite9.ZentralWRRow row = s.getPruefungWRZentral().get(i);

                text(cs,
                        "13." + (i + 1) + " " + ZENTRAL_WR_LABELS[i] + "  " +
                                threeChecks(row.getJa(), row.getNein(), row.getNz()),
                        40, y, 9);

                y -= 15;
            }

            y -= 10;

            // Zusatz-Tabelle Zentralwechselrichter
            drawZusatzTabelle(cs, s.getZusatzWRZentral(), "Zusatz-Tabelle Zentralwechselrichter", y);
        }
    }

    private void text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
        cs.setFont(PDType1Font.HELVETICA, size);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(txt == null ? "" : txt);
        cs.endText();
    }


    private static final String[] ZENTRAL_WR_LABELS = {
            "Die WR-Gebäude sind frei und zugänglich",
            "Die WR-Gebäude befinden sich in gutem Zustand",
            "Der Innenraum der WR ist in Ordnung (Verschmutzung, Feuchtigkeit usw.)",
            "Die Türen der WR in Ordnung (Dichtungen, Scharniere, Verriegelungen)",
            "Heizung vorhanden und betriebsbereit",
            "Hygrostat in Ordnung",
            "Lüfter in Ordnung",
            "Reinigung Lüfter durchgeführt",
            "Stationsbeleuchtung und Steckdosen in Ordnung",
            "Die Kabelverschraubungen sind fest und dicht",
            "Sammelschienen und Isolatoren in Ordnung (bei Bedarf gereinigt)",
            "Klemmverbindung der Leistungseverkabelung in Ordnung",
            "Steck-/Klemmverbindung der Strangverkabelung in Ordnung",
            "Erdverbindungen in Ordnung",
            "Sicherungen in Ordnung",
            "Isolierungen in Ordnung",
            "Berührungsschutz vorhanden und in Ordnung",
            "Überspannungsableiter in Ordnung",
            "Die Schalteinheiten sind funktionsfähig und in Ordnung",
            "Alle WR sind funktionsfähig und speisen ein, ohne Fehlermeldungen",
            "Versorgungsspannung und USV in Ordnung",
            "Weitere Wartungsmaßnahmen laut Angaben WR-Hersteller durchgeführt"
    };

}
