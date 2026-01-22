package com.example.pvbackend.pdf;

import com.example.pvbackend.model.Wartungspaket;
import com.example.pvbackend.model.WartungsprotokollSeite1;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

import static com.example.pvbackend.util.PdfRenderUtils.safe;

@Component
public class Seite1PdfRenderer {

    private static final float MARGIN_LEFT = 40;
    private static final float MARGIN_RIGHT = 550;
    private static final float START_Y = 750;
    private static final float ROW_HEIGHT = 14;

    private static final int FONT_TITLE = 14;
    private static final int FONT_HEADER = 9;
    private static final int FONT_BODY = 8;



    public void render(PDDocument doc, WartungsprotokollSeite1 s) throws IOException {

        PDPage page = new PDPage();
        doc.addPage(page);



        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {


            float y = 745;

            try (InputStream is = getClass().getClassLoader().getResourceAsStream("picture/logo.jpg")) {

                if (is != null) {
                    PDImageXObject logo = JPEGFactory.createFromStream(doc, is);

                    // size of the logo in the PDF
                    float logoW = 90;
                    float logoH = 90;

// position (top right)
                    float xLogo = 590 - logoW;   // right margin
                    float yLogo = 700;           // near top

                    cs.drawImage(logo, xLogo, yLogo, logoW, logoH);
                }

            }


            float infoX = 500;        // align with logo left
            float infoY = 700 - 12;   // below logo

            cs.setFont(PDType1Font.HELVETICA_BOLD, 8);
            text(cs, "ENVARIS GmbH", infoX, infoY, 8);

            cs.setFont(PDType1Font.HELVETICA, 8);
            text(cs, "Friedrich-Olbricht-Damm 62", infoX, infoY - 10, 8);
            text(cs, "13627 Berlin", infoX, infoY - 20, 8);



            y -= 120; // move down by 10 points

            // ---------- Header ----------
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(140, 670);
            cs.showText("Kontrollliste Wartungsarbeiten vor Ort - PV-Anlagen");
            cs.endText();

            cs.setFont(PDType1Font.HELVETICA, 9);

            // ---------- Top fields ----------
            // left column
            text(cs, "Anlagenbezeichnung / ID", 40, y, 9);
            text(cs, safe(s.getAnlagenbezeichnung()), 40, y - 13, 9);
            drawLine(cs, 40, y - 15, 260, y - 15);

            text(cs, "Auftrag erteilt von", 40, y - 32, 9);
            text(cs, safe(s.getAuftraggeber()), 40, y - 45, 9);
            drawLine(cs, 40, y - 47, 260, y - 47);

            // right column
            text(cs, "Vorgang", 320, y, 9);
            text(cs, safe(s.getVorgang()), 320, y - 13, 9);
            drawLine(cs, 320, y - 15, 550, y - 15);

            text(cs, "Wartungspaket", 320, y - 32, 9);

            boolean isStandard = s.getWartungspaket() == Wartungspaket.STANDARD;
            boolean isDguvV3 = s.getWartungspaket() == Wartungspaket.DGUV_V3;

            // Standard
            drawCheckbox(cs, 340, y - 47, isStandard);
            text(cs, "Standard", 352, y - 47, FONT_HEADER);

            // DGUV V3
            drawCheckbox(cs, 430, y - 47, isDguvV3);
            text(cs, "DGUV V3", 442, y - 47, FONT_HEADER);

            y -= 80;

            // ---------- Optionale Bereiche ----------
            text(cs, "Optionale Bereiche (Nur bei Wartung auszuführen wenn angekreuzt) & Zusatzaufträge",
                    40, y, 9);
            y -= 20;

            // DC-Messungen block
            checkboxRow(cs, 60, y, Boolean.TRUE.equals(s.getDcMessungen()),
                    "DC-Messungen (erforderlich wenn kein Überwachungssystem vorhanden, oder bei Unregelmäßigkeiten)", 5);


            y -= 14;


            checkboxRow(
                    cs,
                    80,
                    y,
                    Boolean.TRUE.equals(s.getDcNurBeiUnregelmaessigkeiten()),
                    "nur bei erkennbaren Unregelmäßigkeiten / Auffälligkeiten bei der Wartung vor Ort", 5
            );


            y -= 14;

            text(cs, "vollständig oder im folgenden Bereich:", 80, y, 8);
            text(cs, safe(s.getDcVollstaendigOderBereich()), 260, y, 8);
            drawLine(cs, 260, y - 2, 540, y - 2);


            y -= 14;



            drawCheckbox(cs, 80, y, Boolean.TRUE.equals(s.getVollstaendigGemaessDin()));
            text(
                    cs,
                    "vollständige Messungen gem. DIN EN 62446",
                    92, y,
                    FONT_BODY
            );



            y -= 22;

            // AC-Messungen block
            checkboxRow(cs, 60, y, Boolean.TRUE.equals(s.getAcMessungen()), "AC-Messungen", 5);

            y -= 14;




            checkboxRow(
                    cs,
                    80,
                    y,
                    Boolean.TRUE.equals(s.getAcNurBeiUnregelmaessigkeiten()),
                    "nur bei erkennbaren Unregelmäßigkeiten / Auffälligkeiten bei der Wartung vor Ort"
            , 5);


            y -= 14;



            text(cs, "vollständig oder im folgenden Bereich:", 80, y, 8);
            text(cs, safe(s.getAcVollstaendigOderBereich()), 260, y, 8);
            drawLine(cs, 260, y - 2, 540, y - 2);
            y -= 22;


            // Weitere Optionen (einzelne Checkboxes)
            checkboxRow(cs, 60, y, Boolean.TRUE.equals(s.getZentralwechselrichter()),
                    "Wartung Zentralwechselrichter", 5);



            y -= 12;

            checkboxRow(
                    cs,
                    60,
                    y,
                    Boolean.TRUE.equals(s.getMittelspannungsanlagenErweitert()),
                    "Wartung Mittelspannungsanlagen erweitert", 5
            );



            y -= 12;



            checkboxRow(
                    cs,
                    60,
                    y,
                    Boolean.TRUE.equals(s.getErdungsmessungenStationen()),
                    "Erdungsmessungen Stationen", 5
            );



            y -= 12;


            checkboxRow(
                    cs,
                    60,
                    y,
                    Boolean.TRUE.equals(s.getSichtpruefungMittelspannungsanlagen()),
                    "Sichtprüfung Mittelspannungsanlagen (Trafo- und Übergabestationen)", 5
            );




            y -= 12;


            // Reinigung + Unterpunkte
            checkboxRow(
                    cs,
                    60,
                    y,
                    Boolean.TRUE.equals(s.getReinigung()),
                    "Reinigung (sofern verschmutzt bzw. notwendig)", 5
            );





            y -= 12;



            checkboxRow(cs, 80, y, Boolean.TRUE.equals(s.getReinigungWr()), "WR", 5);
            checkboxRow(cs, 120, y, Boolean.TRUE.equals(s.getReinigungGak()), "GAK", 5);
            checkboxRow(cs, 170, y, Boolean.TRUE.equals(s.getReinigungModule()), "Module", 5);



            y -= 14;



            // Thermografie + Unterpunkte
            checkboxRow(
                    cs,
                    60,
                    y,
                    Boolean.TRUE.equals(s.getThermografie()),
                    "Thermografieuntersuchung der folgenden Komponenten", 5
            );


            y -= 12;



            checkboxRow(cs, 80, y, Boolean.TRUE.equals(s.getThermografieVerteiler()), "Verteiler", 5);
            checkboxRow(cs, 150, y, Boolean.TRUE.equals(s.getThermografieModule()), "Module", 5);
            checkboxRow(cs, 210, y, Boolean.TRUE.equals(s.getThermografieMspAnlagen()), "MSP-Anlagen", 5);



            y -= 14;



            checkboxRow(
                    cs,
                    60,
                    y,
                    Boolean.TRUE.equals(s.getKennlinienmessungen()),
                    "Kennlinienmessungen", 5
            );



            y -= 30;

            // ---------- Bottom text blocks ----------
            // left info block
            text(cs, "Ziel und Vorgehensweise der Wartungsarbeiten", 40, y, 9);
            y -= 14;
            text(cs,
                    "Die Wartungsarbeiten erfolgen unter Beachtung der anerkannten Regeln",
                    40, y, 8);
            y -= 10;
            text(cs,
                    "der Technik und zutreffenden Bestimmungen und Vorschriften",
                    40, y, 8);

            y -= 8;
            text(cs,
                    "für Arbeitssicherheit. - Seite 9 & 10 - nur vorhanden wenn beauftragt",
                    40, y, 8);

            // put "Information zur Kontrollliste" BELOW the left block
            y -= 30;                // add some vertical space after the left block
            float x = 40;           // same left margin
            text(cs, "Information zur Kontrollliste", x, y, 9);
            y -= 14;
            text(cs, "Kurze Bemerkungen sind in die unter den Bereichen stehenden Kästen einzutragen.", x, y, 8);
            y -= 10;
            text(cs, "Zusätzliche Mängelbeschreibungen sind auf einem zusätzlichen Blatt zu beschreiben.", x, y, 8);
            y -= 14;
            text(cs, "Optionale Leistungen – Nur auszuführen wenn angekreuzt.", x, y, 8);
            y -= 14;
            text(cs, "Seiten 10 und 11 nur ausführen, wenn die Leistungen beauftragt / angekreuzt wurden.", x, y, 8);
            y -= 10;
            text(cs, "Vorgefundene Auffälligkeiten immer fotografieren und Standort festhalten!", x, y, 8);


        }
    }

    private void text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
        cs.setFont(PDType1Font.HELVETICA, size);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(txt == null ? "" : txt);
        cs.endText();
    }

    private void drawLine(PDPageContentStream cs, float x1, float y1, float x2, float y2) throws IOException {
        cs.moveTo(x1, y1);
        cs.lineTo(x2, y2);
        cs.stroke();
    }

    private void drawCheckbox(PDPageContentStream cs, float x, float y, boolean checked) throws IOException {
        float size = 8;

        float yOffset = 2; // ← adjust this (try 1–2)

        // box
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

    private void checkboxRow(PDPageContentStream cs, float x, float y, boolean checked, String label, float checkboxYOffset)
            throws IOException {
        drawCheckbox(cs, x, y + checkboxYOffset, checked);   // checkbox moved
        text(cs, label, x + 12, y, FONT_BODY);               // text stays
    }

}