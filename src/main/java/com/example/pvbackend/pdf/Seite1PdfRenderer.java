package com.example.pvbackend.pdf;

import com.example.pvbackend.model.Wartungspaket;
import com.example.pvbackend.model.WartungsprotokollSeite1;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

import static com.example.pvbackend.util.PdfRenderUtils.safe;

public class Seite1PdfRenderer {



    public void render(PDDocument doc, WartungsprotokollSeite1 s) throws IOException {

        PDPage page = new PDPage();
        doc.addPage(page);

        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {


            // ---------- Header ----------
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.beginText();
            cs.newLineAtOffset(140, 750);
            cs.showText("Kontrollliste Wartungsarbeiten vor Ort - PV-Anlagen");
            cs.endText();

            cs.setFont(PDType1Font.HELVETICA, 9);
            float y = 745;

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

            text(cs, "Standard " + checkbox(isStandard), 340, y - 47, 9);
            text(cs, "DGUV V3 " + checkbox(isDguvV3), 430, y - 47, 9);

            y -= 80;

            // ---------- Optionale Bereiche ----------
            text(cs, "Optionale Bereiche (Nur bei Wartung auszuführen wenn angekreuzt) & Zusatzaufträge",
                    40, y, 9);
            y -= 20;

            // DC-Messungen block
            text(cs,
                    checkbox(Boolean.TRUE.equals(s.getDcMessungen())) +
                            " DC-Messungen (erforderlich wenn kein Überwachungssystem vorhanden, oder bei Unregelmäßigkeiten)",
                    60, y, 8);
            y -= 14;

            text(cs,
                    checkbox(Boolean.TRUE.equals(s.getDcNurBeiUnregelmaessigkeiten())) +
                            " nur bei erkennbaren Unregelmäßigkeiten / Auffälligkeiten bei der Wartung vor Ort",
                    80, y, 8);
            y -= 14;

            text(cs, "vollständig oder im folgenden Bereich:", 80, y, 8);
            text(cs, safe(s.getDcVollstaendigOderBereich()), 260, y, 8);
            drawLine(cs, 260, y - 2, 540, y - 2);
            y -= 14;

            text(cs,
                    checkbox(Boolean.TRUE.equals(s.getVollstaendigGemaessDin())) +
                            " vollständige Messungen gem. DIN EN 62446",
                    80, y, 8);
            y -= 22;

            // AC-Messungen block
            text(cs,
                    checkbox(Boolean.TRUE.equals(s.getAcMessungen())) + " AC-Messungen",
                    60, y, 8);
            y -= 14;

            text(cs,
                    checkbox(Boolean.TRUE.equals(s.getAcNurBeiUnregelmaessigkeiten())) +
                            " nur bei erkennbaren Unregelmäßigkeiten / Auffälligkeiten bei der Wartung vor Ort",
                    80, y, 8);
            y -= 14;

            text(cs, "vollständig oder im folgenden Bereich:", 80, y, 8);
            text(cs, safe(s.getAcVollstaendigOderBereich()), 260, y, 8);
            drawLine(cs, 260, y - 2, 540, y - 2);
            y -= 22;

            // Weitere Optionen (einzelne Checkboxes)
            text(cs,
                    checkbox(Boolean.TRUE.equals(s.getZentralwechselrichter())) +
                            " Wartung Zentralwechselrichter",
                    60, y, 8);
            y -= 12;

            text(cs,
                    checkbox(Boolean.TRUE.equals(s.getMittelspannungsanlagenErweitert())) +
                            " Wartung Mittelspannungsanlagen erweitert",
                    60, y, 8);
            y -= 12;

            text(cs,
                    checkbox(Boolean.TRUE.equals(s.getErdungsmessungenStationen())) +
                            " Erdungsmessungen Stationen",
                    60, y, 8);
            y -= 12;

            text(cs,
                    checkbox(Boolean.TRUE.equals(s.getSichtpruefungMittelspannungsanlagen())) +
                            " Sichtprüfung Mittelspannungsanlagen (Trafo- und Übergabestationen)",
                    60, y, 8);
            y -= 12;

            // Reinigung + Unterpunkte
            text(cs,
                    checkbox(Boolean.TRUE.equals(s.getReinigung())) +
                            " Reinigung (sofern verschmutzt bzw. notwendig)",
                    60, y, 8);
            y -= 12;

            text(cs,
                    checkbox(Boolean.TRUE.equals(s.getReinigungWr())) + " WR   " +
                            checkbox(Boolean.TRUE.equals(s.getReinigungGak())) + " GAK   " +
                            checkbox(Boolean.TRUE.equals(s.getReinigungModule())) + " Module",
                    80, y, 8);
            y -= 14;

            // Thermografie + Unterpunkte
            text(cs,
                    checkbox(Boolean.TRUE.equals(s.getThermografie())) +
                            " Thermografieuntersuchung der folgenden Komponenten",
                    60, y, 8);
            y -= 12;

            text(cs,
                    checkbox(Boolean.TRUE.equals(s.getThermografieVerteiler())) + " Verteiler   " +
                            checkbox(Boolean.TRUE.equals(s.getThermografieModule())) + " Module   " +
                            checkbox(Boolean.TRUE.equals(s.getThermografieMspAnlagen())) + " MSP-Anlagen",
                    80, y, 8);
            y -= 14;

            text(cs,
                    checkbox(Boolean.TRUE.equals(s.getKennlinienmessungen())) +
                            " Kennlinienmessungen",
                    60, y, 8);
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
    // Convert boolean → checkbox symbol
    private String checkbox(boolean b) {
        return b ? "[X]" : "[ ]";
    }

}