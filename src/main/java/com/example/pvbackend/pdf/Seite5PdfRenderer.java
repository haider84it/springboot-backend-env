package com.example.pvbackend.pdf;

import com.example.pvbackend.model.WartungsprotokollSeite4;
import com.example.pvbackend.model.WartungsprotokollSeite5;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static com.example.pvbackend.util.PdfRenderUtils.drawCheckTriple;
import static io.micrometer.common.util.StringUtils.isBlank;
import static org.springframework.util.ObjectUtils.isEmpty;
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
                    s.getMessungModultragendeGemessen(),
                    s.getMessungModultragendeNein(),
                    s.getMessungModultragendeNichtZutreffend(), y);

            y = drawCheckTriple(cs, "5.2 Alle Modulstränge gemessen",
                    s.getMessungAlleModulstraengeGemessen(),
                    s.getMessungAlleModulstraengeNein(),
                    s.getMessungAlleModulstraengeNichtZutreffend(), y);

            y = drawCheckTriple(cs, "5.3 Erdungswiderstand gemessen",
                    s.getMessungErdungswiderstandGemessen(),
                    s.getMessungErdungswiderstandNein(),
                    s.getMessungErdungswiderstandNichtZutreffend(), y);

            y = drawCheckTriple(cs, "5.4 Erdung durchgeführt",
                    s.getMessungErdungDurchgefuehrt(),
                    s.getMessungErdungDurchgefuehrtNein(),
                    s.getMessungErdungDurchgefuehrtNichtZutreffend(), y);

            y = drawCheckTriple(cs, "5.5 Nach DIN 62446 geprüft",
                    s.getMessungNachDin62446(),
                    s.getMessungNachDin62446Nein(),
                    s.getMessungNachDin62446NichtZutreffend(), y);

            y -= 10;

            // Zusatz-Tabelle #1
            y = drawZusatzTabelle(cs, List.of(
                    newRow(s.getZt5Punkt1(), s.getZt5Bemerkung1(), s.getZt5Standort1(), s.getZt5Plan1(), s.getZt5BildNr1(), s.getZt5Beh1(), s.getZt5NichtBeh1()),
                    newRow(s.getZt5Punkt2(), s.getZt5Bemerkung2(), s.getZt5Standort2(), s.getZt5Plan2(), s.getZt5BildNr2(), s.getZt5Beh2(), s.getZt5NichtBeh2()),
                    newRow(s.getZt5Punkt3(), s.getZt5Bemerkung3(), s.getZt5Standort3(), s.getZt5Plan3(), s.getZt5BildNr3(), s.getZt5Beh3(), s.getZt5NichtBeh3()),
                    newRow(s.getZt5Punkt4(), s.getZt5Bemerkung4(), s.getZt5Standort4(), s.getZt5Plan4(), s.getZt5BildNr4(), s.getZt5Beh4(), s.getZt5NichtBeh4()),
                    newRow(s.getZt5Punkt5(), s.getZt5Bemerkung5(), s.getZt5Standort5(), s.getZt5Plan5(), s.getZt5BildNr5(), s.getZt5Beh5(), s.getZt5NichtBeh5())
            ), "Zusatz-Tabelle #1", y);

            y -= 10;

            // SECTION 6 – Prüfung GAKs
            text(cs, "SECTION 6 – Prüfung GAKs", 40, y, 11);
            y -= 20;

            y = drawCheckThree(cs, "6.1 GAK zugänglich",
                    s.getGakZugaenglich(), s.getGakZugaenglichNein(), s.getGakZugaenglichNz(), y );

            // … remaining drawCheckFive calls unchanged …

            drawZusatzTabelle(cs, List.of(
                    newRow(s.getZt6Punkt1(), s.getZt6Bemerkung1(), s.getZt6Standort1(), s.getZt6Plan1(), s.getZt6BildNr1(), s.getZt6Beh1(), s.getZt6NichtBeh1()),
                    newRow(s.getZt6Punkt2(), s.getZt6Bemerkung2(), s.getZt6Standort2(), s.getZt6Plan2(), s.getZt6BildNr2(), s.getZt6Beh2(), s.getZt6NichtBeh2()),
                    newRow(s.getZt6Punkt3(), s.getZt6Bemerkung3(), s.getZt6Standort3(), s.getZt6Plan3(), s.getZt6BildNr3(), s.getZt6Beh3(), s.getZt6NichtBeh3()),
                    newRow(s.getZt6Punkt4(), s.getZt6Bemerkung4(), s.getZt6Standort4(), s.getZt6Plan4(), s.getZt6BildNr4(), s.getZt6Beh4(), s.getZt6NichtBeh4()),
                    newRow(s.getZt6Punkt5(), s.getZt6Bemerkung5(), s.getZt6Standort5(), s.getZt6Plan5(), s.getZt6BildNr5(), s.getZt6Beh5(), s.getZt6NichtBeh5())
            ), "Zusatz-Tabelle #2", y);
        }
    }

    private static void text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
        cs.setFont(PDType1Font.HELVETICA, size);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(txt == null ? "" : txt);
        cs.endText();
    }

    public static float drawZusatzTabelle(
            PDPageContentStream cs,
            List<? extends Object> list,
            String title,
            float y
    ) throws IOException {

        text(cs, title, 40, y, 11);
        y -= 20;

        for (Object o : list) {
            if (!(o instanceof WartungsprotokollSeite4.ZusatzRow z)) continue;

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


    // Convert boolean → checkbox symbol
    private static String checkbox(boolean b) {
        return b ? "[X]" : "[ ]";
    }

    public static String safe(String s) {
        return s == null ? "" : s;
    }



    public static WartungsprotokollSeite4.ZusatzRow newRow(
            String punkt,
            String bemerkung,
            String standort,
            Boolean plan,
            String bildnr,
            Boolean beh,
            Boolean nbeh
    ) {
        WartungsprotokollSeite4.ZusatzRow r = new WartungsprotokollSeite4.ZusatzRow();
        r.setZupunkt(punkt);
        r.setBemerkung(bemerkung);
        r.setStandort(standort);
        r.setPlan(plan);
        r.setBildnr(bildnr);
        r.setBeh(beh);
        r.setNbeh(nbeh);
        return r;
    }

}