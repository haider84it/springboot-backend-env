package com.example.pvbackend.pdf;

import com.example.pvbackend.model.*;
import com.example.pvbackend.service.WartungsprotokollService;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static com.example.pvbackend.util.PdfRenderUtils.*;
import static io.micrometer.common.util.StringUtils.isBlank;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final WartungsprotokollService wartungsprotokollService;

    // ---------------------------------------------------------------
    // PUBLIC: Generate PDF for one Protokoll
    // ---------------------------------------------------------------
    public byte[] generate(Long id) {
        Wartungsprotokoll p = wartungsprotokollService.findById(id)
                .orElseThrow(() -> new RuntimeException("Protokoll not found"));

        try (PDDocument doc = new PDDocument()) {

            addSeite1(doc, p.getSeite1());
            addSeite2(doc, p.getSeite2());
            addSeite3(doc, p.getSeite3());
            addSeite4(doc, p.getSeite4());
            addSeite5(doc, p.getSeite5());
            addSeite6(doc, p.getSeite6());
            addSeite7(doc, p.getSeite7());
            addSeite8(doc, p.getSeite8());
            addSeite9(doc, p.getSeite9());
            addSeite10(doc, p.getSeite10());
            addSeite11(doc, p.getSeite11());
            addSeite11b(doc, p.getSeite11b());
            addSeite12(doc, p.getSeite12());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            doc.save(baos);
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("PDF generation error", e);
        }
    }

    // ===============================================================
    // PAGE HELPERS
    // ===============================================================

    private PDPageContentStream startPage(PDDocument doc, String title) throws IOException {
        PDPage page = new PDPage();
        doc.addPage(page);

        PDPageContentStream cs = new PDPageContentStream(doc, page);
        cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
        cs.beginText();
        cs.newLineAtOffset(40, 780);
        cs.showText(title);
        cs.endText();

        return cs;
    }

    private void drawLine(PDPageContentStream cs, float x1, float y1, float x2, float y2) throws IOException {
        cs.moveTo(x1, y1);
        cs.lineTo(x2, y2);
        cs.stroke();
    }

    private void text(PDPageContentStream cs, String txt, float x, float y, int size) throws IOException {
        cs.setFont(PDType1Font.HELVETICA, size);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(txt == null ? "" : txt);
        cs.endText();
    }

    // ===============================================================
    // PAGE 1 EXAMPLE (you can copy this pattern for all 12 pages)
    // ===============================================================
    private void addSeite1(PDDocument doc, WartungsprotokollSeite1 s) throws IOException {
        PDPageContentStream cs = startPage(doc, "Seite 1 – Allgemeine Informationen");

        float y = 740;

        text(cs, "Vorgang: " + s.getVorgang(), 40, y, 10);
        y -= 15;
        text(cs, "Anlagenbezeichnung: " + s.getAnlagenbezeichnung(), 40, y, 10);
        y -= 15;
        text(cs, "Auftraggeber: " + s.getAuftraggeber(), 40, y, 10);
        y -= 15;
        text(cs, "Wartungspaket: " + s.getWartungspaket(), 40, y, 10);
        y -= 25;

        text(cs, "DC-Messungen: " + checkbox(Boolean.TRUE.equals(s.getDcMessungen())), 40, y, 10);
        y -= 15;
        text(cs, "AC-Messungen: " + checkbox(Boolean.TRUE.equals(s.getAcMessungen())), 40, y, 10);
        y -= 25;

        cs.close();
    }

    // Convert boolean → checkbox symbol
    private String checkbox(boolean b) {
        return b ? "[X]" : "[ ]";
    }

    // ===============================================================
    // PAGE 2
    // ===============================================================
    private void addSeite2(PDDocument doc, WartungsprotokollSeite2 s) throws IOException {
        PDPageContentStream cs = startPage(doc, "Seite 2 – Zugang & Arbeitszeiten");

        float y = 740;

        text(cs, "Zugangsschlüssel vorhanden: " +
                checkbox(Boolean.TRUE.equals(s.getZugangsschluesselVorhanden())), 40, y, 10);
        y -= 15;
        text(cs, "Anmerkung: " + s.getZugangsschluesselAnmerkung(), 40, y, 10);
        y -= 15;

        text(cs, "Thermokamera vorhanden: " +
                checkbox(Boolean.TRUE.equals(s.getThermoKameraVorhanden())), 40, y, 10);
        y -= 15;
        text(cs, "Anmerkung: " + s.getThermoKameraAnmerkung(), 40, y, 10);
        y -= 15;

        text(cs, "Betreiber kontaktiert: " +
                checkbox(Boolean.TRUE.equals(s.getBetreiberKontaktiert())), 40, y, 10);
        y -= 15;
        text(cs, "Anmerkung: " + s.getBetreiberAnmerkung(), 40, y, 10);
        y -= 15;

        text(cs, "Eigentümer kontaktiert: " +
                checkbox(Boolean.TRUE.equals(s.getEigentuemerKontaktiert())), 40, y, 10);
        y -= 15;
        text(cs, "Anmerkung: " + s.getEigentuemerAnmerkung(), 40, y, 10);
        y -= 15;

        text(cs, "Elektrofachkraft Name: " + s.getElektrofachkraftName(), 40, y, 10);
        y -= 25;

        cs.close();
    }

    // ===============================================================
    // PAGE 3–12 (ALL REMAINING)
    // ===============================================================

    private void addSeite3(PDDocument doc, WartungsprotokollSeite3 s) throws IOException {
        PDPageContentStream cs = startPage(doc, "Seite 3 – Module & Anlage");
        float y = 740;

        text(cs, "Modulzustand:", 40, y, 11);
        y -= 20;

        int index = 1;
        for (var row : s.getModuleZustand()) {

            text(cs,
                    index + ") Ja:" + checkbox(Boolean.TRUE.equals(row.getJa())) +
                            "  Nein:" + checkbox(Boolean.TRUE.equals(row.getNein())) +
                            "  n.z:" + checkbox(Boolean.TRUE.equals(row.getNz())),
                    40, y, 9);

            y -= 15;
            index++;
        }

        cs.close();
    }

    private void addSeite4(PDDocument doc, WartungsprotokollSeite4 s) throws IOException {
        PDPageContentStream cs = startPage(doc, "Seite 4 – Montage & Sichtkontrolle");
        float y = 740;

        // SECTION 3
        text(cs, "SECTION 3 – Prüfung Montage (3.1 – 3.10)", 40, y, 11);
        y -= 20;

        int nr = 1;
        for (WartungsprotokollSeite4.RowCheckSimple row : s.getPruefungMontage()) {
            text(cs,
                    "3." + nr +
                            "  Ja:" + checkbox(Boolean.TRUE.equals(row.getJa())) +
                            "  Nein:" + checkbox(Boolean.TRUE.equals(row.getNein())) +
                            "  n.z:" + checkbox(Boolean.TRUE.equals(row.getNz())),
                    40, y, 9);
            y -= 15;
            nr++;
        }

        y -= 10;

        // ZUSATZ-TABELLE #1
        y = drawZusatzTabelle(cs, s.getZusatz1(), "Zusatz-Tabelle #1", y);
        y -= 10;

        // SECTION 4
        text(cs, "SECTION 4 – Sichtkontrolle (4.1 – 4.4)", 40, y, 11);
        y -= 20;

        nr = 1;
        for (WartungsprotokollSeite4.RowCheckExtended row : s.getSichtkontrolle()) {
            text(cs,
                    "4." + nr +
                            "  Ja:" + checkbox(Boolean.TRUE.equals(row.getJa())) +
                            "  Nein:" + checkbox(Boolean.TRUE.equals(row.getNein())) +
                            "  n.z:" + checkbox(Boolean.TRUE.equals(row.getNz())) +
                            "  s.B/M:" + checkbox(Boolean.TRUE.equals(row.getSbm())),
                    40, y, 9);
            y -= 15;
            nr++;
        }

        y -= 10;

        // ZUSATZ-TABELLE #2
        y = drawZusatzTabelle(cs, s.getZusatz2(), "Zusatz-Tabelle #2", y);

        cs.close();
    }

    private void addSeite5(PDDocument doc, WartungsprotokollSeite5 s) throws IOException {

        PDPageContentStream cs = startPage(doc, "Seite 5 – Messungen PV & GAKs");
        float y = 740;

        // ============================================================
        // SECTION 5 — Messungen PV-Anlage
        // ============================================================
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


        // ============================================================
        // ZUSATZ-TABELLE #1 (5 rows)
        // ============================================================
        y = drawZusatzTabelle(cs,
                List.of(
                        newRow(s.getZt5Punkt1(), s.getZt5Bemerkung1(), s.getZt5Standort1(), s.getZt5Plan1(), s.getZt5BildNr1(), s.getZt5Beh1(), s.getZt5NichtBeh1()),
                        newRow(s.getZt5Punkt2(), s.getZt5Bemerkung2(), s.getZt5Standort2(), s.getZt5Plan2(), s.getZt5BildNr2(), s.getZt5Beh2(), s.getZt5NichtBeh2()),
                        newRow(s.getZt5Punkt3(), s.getZt5Bemerkung3(), s.getZt5Standort3(), s.getZt5Plan3(), s.getZt5BildNr3(), s.getZt5Beh3(), s.getZt5NichtBeh3()),
                        newRow(s.getZt5Punkt4(), s.getZt5Bemerkung4(), s.getZt5Standort4(), s.getZt5Plan4(), s.getZt5BildNr4(), s.getZt5Beh4(), s.getZt5NichtBeh4()),
                        newRow(s.getZt5Punkt5(), s.getZt5Bemerkung5(), s.getZt5Standort5(), s.getZt5Plan5(), s.getZt5BildNr5(), s.getZt5Beh5(), s.getZt5NichtBeh5())
                ),
                "Zusatz-Tabelle #1",
                y
        );

        y -= 10;


        // ============================================================
        // SECTION 6 — Prüfung GAKs
        // ============================================================
        text(cs, "SECTION 6 – Prüfung GAKs", 40, y, 11);
        y -= 20;

        y = drawCheckFive(cs, "6.1 GAK zugänglich",
                s.getGakZugaenglich(), s.getGakZugaenglichNein(), s.getGakZugaenglichNz(),
                s.getGakZugaenglichSbm(), s.getGakZugaenglichBeiblatt(), y);

        y = drawCheckFive(cs, "6.2 Strangsicherungen vorhanden",
                s.getGakStrangsicherungen(), s.getGakStrangsicherungenNein(), s.getGakStrangsicherungenNz(),
                s.getGakStrangsicherungenSbm(), s.getGakStrangsicherungenBeiblatt(), y);

        y = drawCheckFive(cs, "6.3 ÜSS-Einrichtungen",
                s.getGakUssEinrichtungen(), s.getGakUssEinrichtungenNein(), s.getGakUssEinrichtungenNz(),
                s.getGakUssEinrichtungenSbm(), s.getGakUssEinrichtungenBeiblatt(), y);

        y = drawCheckFive(cs, "6.4 Schalter funktionsfähig",
                s.getGakSchalter(), s.getGakSchalterNein(), s.getGakSchalterNz(),
                s.getGakSchalterSbm(), s.getGakSchalterBeiblatt(), y);

        y = drawCheckFive(cs, "6.5 Frei von Schäden",
                s.getGakFreiVonSchaeden(), s.getGakFreiVonSchaedenNein(), s.getGakFreiVonSchaedenNz(),
                s.getGakFreiVonSchaedenSbm(), s.getGakFreiVonSchaedenBeiblatt(), y);

        y = drawCheckFive(cs, "6.6 Frei von Feuchtigkeit",
                s.getGakFreiVonFeuchtigkeit(), s.getGakFreiVonFeuchtigkeitNein(), s.getGakFreiVonFeuchtigkeitNz(),
                s.getGakFreiVonFeuchtigkeitSbm(), s.getGakFreiVonFeuchtigkeitBeiblatt(), y);

        y = drawCheckFive(cs, "6.7 Innerer Zustand ok",
                s.getGakInnererZustand(), s.getGakInnererZustandNein(), s.getGakInnererZustandNz(),
                s.getGakInnererZustandSbm(), s.getGakInnererZustandBeiblatt(), y);

        y = drawCheckFive(cs, "6.8 Erdung ok",
                s.getGakErdungOk(), s.getGakErdungOkNein(), s.getGakErdungOkNz(),
                s.getGakErdungOkSbm(), s.getGakErdungOkBeiblatt(), y);

        y = drawCheckFive(cs, "6.9 Beschriftung korrekt",
                s.getGakBeschriftungOk(), s.getGakBeschriftungOkNein(), s.getGakBeschriftungOkNz(),
                s.getGakBeschriftungOkSbm(), s.getGakBeschriftungOkBeiblatt(), y);

        y = drawCheckFive(cs, "6.10 Kabelverschraubungen",
                s.getGakKabelVerschraubungen(), s.getGakKabelVerschraubungenNein(), s.getGakKabelVerschraubungenNz(),
                s.getGakKabelVerschraubungenSbm(), s.getGakKabelVerschraubungenBeiblatt(), y);

        y = drawCheckFive(cs, "6.11 Anzugsmomente geprüft",
                s.getGakAnzugsmomente(), s.getGakAnzugsmomenteNein(), s.getGakAnzugsmomenteNz(),
                s.getGakAnzugsmomenteSbm(), s.getGakAnzugsmomenteBeiblatt(), y);

        y = drawCheckFive(cs, "6.12 GAK gereinigt",
                s.getGakGereinigt(), s.getGakGereinigtNein(), s.getGakGereinigtNz(),
                s.getGakGereinigtSbm(), s.getGakGereinigtBeiblatt(), y);

        y = drawCheckFive(cs, "6.13 Keine Auffälligkeiten",
                s.getGakKeineAuffaelligkeiten(), s.getGakKeineAuffaelligkeitenNein(), s.getGakKeineAuffaelligkeitenNz(),
                s.getGakKeineAuffaelligkeitenSbm(), s.getGakKeineAuffaelligkeitenBeiblatt(), y);


        y -= 10;


        // ============================================================
        // ZUSATZ-TABELLE #2
        // ============================================================
        y = drawZusatzTabelle(cs,
                List.of(
                        newRow(s.getZt6Punkt1(), s.getZt6Bemerkung1(), s.getZt6Standort1(), s.getZt6Plan1(), s.getZt6BildNr1(), s.getZt6Beh1(), s.getZt6NichtBeh1()),
                        newRow(s.getZt6Punkt2(), s.getZt6Bemerkung2(), s.getZt6Standort2(), s.getZt6Plan2(), s.getZt6BildNr2(), s.getZt6Beh2(), s.getZt6NichtBeh2()),
                        newRow(s.getZt6Punkt3(), s.getZt6Bemerkung3(), s.getZt6Standort3(), s.getZt6Plan3(), s.getZt6BildNr3(), s.getZt6Beh3(), s.getZt6NichtBeh3()),
                        newRow(s.getZt6Punkt4(), s.getZt6Bemerkung4(), s.getZt6Standort4(), s.getZt6Plan4(), s.getZt6BildNr4(), s.getZt6Beh4(), s.getZt6NichtBeh4()),
                        newRow(s.getZt6Punkt5(), s.getZt6Bemerkung5(), s.getZt6Standort5(), s.getZt6Plan5(), s.getZt6BildNr5(), s.getZt6Beh5(), s.getZt6NichtBeh5())
                ),
                "Zusatz-Tabelle #2",
                y
        );

        cs.close();
    }

    private void addSeite6(PDDocument doc, WartungsprotokollSeite6 s) throws IOException {
        PDPageContentStream cs = startPage(doc, "Seite 6 – Wechselrichter (WR) & AC-Verteiler");
        float y = 740;

        // ============================================================
        // SECTION 7 – Prüfung WR (14 rows)
        // ============================================================
        text(cs, "SECTION 7 – Prüfung Wechselrichter (7.1 – 7.x)", 40, y, 11);
        y -= 20;

        int nr = 1;
        for (WartungsprotokollSeite6.RowWR row : s.getPruefungWR()) {
            text(cs,
                    "7." + nr + "  " + threeChecks(row.getJa(), row.getNein(), row.getNz()),
                    40, y, 9);
            y -= 15;
            nr++;
        }

        y -= 10;

        // ============================================================
        // ZUSATZ-TABELLE WR
        // ============================================================
        y = drawZusatzTabelle(cs, s.getZusatzWR(), "Zusatz-Tabelle WR", y);
        y -= 10;

        // ============================================================
        // SECTION 8 – Prüfung AC-Verteiler
        // ============================================================
        text(cs, "SECTION 8 – Prüfung AC-Verteiler (8.1 – 8.x)", 40, y, 11);
        y -= 20;

        nr = 1;
        for (WartungsprotokollSeite6.RowAC row : s.getPruefungAC()) {

            text(cs,
                    "8." + nr
                            + threeChecks(row.getJa(), row.getNein(), row.getNz())
                            +  "  s.B/M:" + checkbox(Boolean.TRUE.equals(row.getSbm()))
                            + "  s.Beiblatt:" + checkbox(Boolean.TRUE.equals(row.getSbeiblatt())),
                    40, y, 9);
            y -= 15;
            nr++;
        }

        y -= 10;

        // ============================================================
        // ZUSATZ-TABELLE AC
        // ============================================================
        y = drawZusatzTabelle(cs, s.getZusatzAC(), "Zusatz-Tabelle AC-Verteiler", y);

        cs.close();
    }

    private void addSeite7(PDDocument doc, WartungsprotokollSeite7 s) throws IOException {
        PDPageContentStream cs = startPage(doc, "Seite 7 – Zähler & Überwachung");
        float y = 740;

        // ============================================================
        // SECTION 9 — Prüfung Zähler (4 rows)
        // ============================================================
        text(cs, "SECTION 9 – Prüfung Zähler (9.1 – 9.4)", 40, y, 11);
        y -= 20;

        int nr = 1;
        for (WartungsprotokollSeite7.ZaehlerRow row : s.getPruefungZaehler()) {
            text(cs,
                    "9." + nr + "  "
                            + threeChecks(row.getJa(), row.getNein(), row.getNz()),
                    40, y, 9);
            y -= 15;
            nr++;
        }

        y -= 10;

        // ============================================================
        // ZUSATZ-TABELLE ZÄHLER
        // ============================================================
        y = drawZusatzTabelle(cs, s.getZusatzZaehler(), "Zusatz-Tabelle Zähler", y);
        y -= 10;


        // ============================================================
        // SECTION 10 — Überwachungssystem (10 rows)
        // ============================================================
        text(cs, "SECTION 10 – Prüfung Überwachungssystem (10.1 – 10.10)", 40, y, 11);
        y -= 20;

        nr = 1;
        for (WartungsprotokollSeite7.UeberwachungRow row : s.getPruefungUeberwachung()) {
            text(cs,
                    "10." + nr + "  "
                            + threeChecks(row.getJa(), row.getNein(), row.getNz()),
                    40, y, 9);
            y -= 15;
            nr++;
        }

        y -= 10;

        // ============================================================
        // ZUSATZ-TABELLE ÜBERWACHUNG
        // ============================================================
        y = drawZusatzTabelle(cs, s.getZusatzUeberwachung(), "Zusatz-Tabelle Überwachung", y);

        cs.close();
    }



    private void addSeite8(PDDocument doc, WartungsprotokollSeite8 s) throws IOException {
        PDPageContentStream cs = startPage(doc, "Seite 8 – Außenanlage & Diebstahl");
        float y = 740;

        // ============================================================
        // SECTION 11 — Prüfung Außenanlage (11.1 – 11.9)
        // ============================================================
        text(cs, "SECTION 11 – Prüfung Außenanlage (11.1 – 11.9)", 40, y, 11);
        y -= 20;

        int nr = 1;
        for (WartungsprotokollSeite8.AussenRow row : s.getPruefungAussen()) {
            text(cs,
                    "11." + nr + "  " +
                            threeChecks(row.getJa(), row.getNein(), row.getNz()),
                    40, y, 9);
            y -= 15;
            nr++;
        }

        y -= 10;

        // ============================================================
        // ZUSATZ-TABELLE #1 — Außen
        // ============================================================
        y = drawZusatzTabelle(cs, s.getZusatzAussen(), "Zusatz-Tabelle Außenanlage", y);
        y -= 10;


        // ============================================================
        // SECTION 12 — Prüfung Diebstahlschutz (12.1 – 12.5)
        // ============================================================
        text(cs, "SECTION 12 – Prüfung Diebstahlschutz (12.1 – 12.5)", 40, y, 11);
        y -= 20;

        nr = 1;
        for (WartungsprotokollSeite8.DiebstahlRow row : s.getPruefungDiebstahl()) {
            text(cs,
                    "12." + nr + "  " +
                            threeChecks(row.getJa(), row.getNein(), row.getNz()),
                    40, y, 9);
            y -= 15;
            nr++;
        }

        y -= 10;

        // ============================================================
        // ZUSATZ-TABELLE #2 — Diebstahlschutz
        // ============================================================
        y = drawZusatzTabelle(cs, s.getZusatzDiebstahl(), "Zusatz-Tabelle Diebstahlschutz", y);

        cs.close();
    }



    private void addSeite9(PDDocument doc, WartungsprotokollSeite9 s) throws IOException {
        PDPageContentStream cs = startPage(doc, "Seite 9 – Zentralwechselrichter");
        float y = 740;

        // ============================================================
        // SECTION 13 — Zentralwechselrichter (13.1 – 13.23)
        // ============================================================
        text(cs, "SECTION 13 – Zentralwechselrichter (13.1 – 13.23)", 40, y, 11);
        y -= 20;

        int nr = 1;
        for (WartungsprotokollSeite9.ZentralWRRow row : s.getPruefungWRZentral()) {
            text(cs,
                    "13." + nr + "  " +
                            threeChecks(row.getJa(), row.getNein(), row.getNz()),
                    40, y, 9);
            y -= 15;
            nr++;
        }

        y -= 10;

        // ============================================================
        // ZUSATZ-TABELLE — Zentralwechselrichter
        // ============================================================
        y = drawZusatzTabelle(cs, s.getZusatzWRZentral(), "Zusatz-Tabelle Zentralwechselrichter", y);

        cs.close();
    }


    private void addSeite10(PDDocument doc, WartungsprotokollSeite10 s) throws IOException {
        PDPageContentStream cs = startPage(doc, "Seite 10 – MSP erweitert");
        float y = 740;

        // ============================================================
        // SECTION 14 — Prüfung Mittelspannungsanlagen (14.1 – 14.29)
        // ============================================================
        text(cs, "SECTION 14 – Prüfung Mittelspannungsanlagen (14.1 – 14.29)", 40, y, 11);
        y -= 20;

        int nr = 1;
        for (WartungsprotokollSeite10.MSPRow row : s.getPruefungMSP()) {
            text(
                    cs,
                    "14." + nr + "  " +
                            threeChecks(row.getJa(), row.getNein(), row.getNz()),
                    40,
                    y,
                    9
            );
            y -= 15;
            nr++;
        }

        y -= 10;

        // ============================================================
        // ZUSATZ-TABELLE — MSP
        // ============================================================
        y = drawZusatzTabelle(cs, s.getZusatzMSP(), "Zusatz-Tabelle MSP", y);

        cs.close();
    }




    private void addSeite11(PDDocument doc, WartungsprotokollSeite11 s) throws IOException {
        PDPageContentStream cs = startPage(doc, "Seite 11 – MSP Sichtprüfung + Sonstiges");
        float y = 740;

        // ============================================================
        // SECTION 15 – Sichtprüfung Mittelspannungsanlagen
        // ============================================================
        text(cs, "SECTION 15 – Sichtprüfung Mittelspannungsanlagen (15.1 – 15.4)", 40, y, 11);
        y -= 20;

        int nr = 1;
        for (WartungsprotokollSeite11.MSsichtRow row : s.getPruefungMSsicht()) {
            text(
                    cs,
                    "15." + nr + "  " +
                            threeChecks(row.getJa(), row.getNein(), row.getNz()),
                    40,
                    y,
                    9
            );
            y -= 15;
            nr++;
        }

        y -= 10;

        // ============================================================
        // ZUSATZ-TABELLE 15
        // ============================================================
        y = drawZusatzTabelle(cs, s.getZusatzMSsicht(), "Zusatz-Tabelle Sichtprüfung MS", y);
        y -= 10;

        // ============================================================
        // SECTION 16 – Sonstiges
        // ============================================================
        text(cs, "SECTION 16 – Sonstiges (16.1 – 16.4)", 40, y, 11);
        y -= 20;

        nr = 1;
        for (WartungsprotokollSeite11.SonstigesRow row : s.getSonstiges()) {
            text(
                    cs,
                    "16." + nr + "  " +
                            threeChecks(row.getJa(), row.getNein(), row.getNz()),
                    40,
                    y,
                    9
            );
            y -= 15;
            nr++;
        }

        y -= 10;

        // ============================================================
        // ZUSATZ-TABELLE 16
        // ============================================================
        y = drawZusatzTabelle(cs, s.getZusatzSonstiges(), "Zusatz-Tabelle Sonstiges", y);

        cs.close();
    }




    private void addSeite11b(PDDocument doc, WartungsprotokollSeite11b s) throws IOException {
        PDPageContentStream cs = startPage(doc, "Seite 11b – Abschluss & Bewertung");
        float y = 740;

        // ============================================================
        // SECTION: Schulnoten (1–6)
        // ============================================================
        text(cs, "Bewertung – Schulnoten (1 bis 6)", 40, y, 11);
        y -= 20;

        for (int i = 0; i < s.getNoten().size(); i++) {
            Boolean b = s.getNoten().get(i);
            text(
                    cs,
                    "Note " + (i + 1) + ": " + checkbox(Boolean.TRUE.equals(b)),
                    40,
                    y,
                    9
            );
            y -= 15;
        }

        y -= 10;

        // ============================================================
        // SECTION: Abschluss der Arbeiten (6 checkboxes)
        // ============================================================
        text(cs, "Abschluss der Arbeiten", 40, y, 11);
        y -= 20;

        for (int i = 0; i < s.getAbschluss().size(); i++) {
            Boolean b = s.getAbschluss().get(i);
            text(
                    cs,
                    "Punkt " + (i + 1) + ": " + checkbox(Boolean.TRUE.equals(b)),
                    40,
                    y,
                    9
            );
            y -= 15;
        }

        y -= 10;

        // ============================================================
        // SECTION: Anmerkungen (6 textfields)
        // ============================================================
        text(cs, "Anmerkungen", 40, y, 11);
        y -= 20;

        for (int i = 0; i < s.getAnmerkungen().size(); i++) {
            text(
                    cs,
                    "Anmerkung " + (i + 1) + ": " + safe(s.getAnmerkungen().get(i)),
                    40,
                    y,
                    9
            );
            y -= 15;
        }

        y -= 20;

        // ============================================================
        // BOTTOM: Alles erledigt checkbox
        // ============================================================
        text(cs,
                "Alles erledigt: " + checkbox(Boolean.TRUE.equals(s.getAllesErledigt())),
                40, y, 10);
        y -= 25;

        // ============================================================
        // DATE + SIGNATURE
        // ============================================================
        text(cs, "Datum: " + safe(s.getDatum()), 40, y, 10);
        y -= 15;

        text(cs, "Unterschrift: " + safe(s.getUnterschrift()), 40, y, 10);
        y -= 15;

        cs.close();
    }

    private void addSeite12(PDDocument doc, WartungsprotokollSeite12 s) throws IOException {
        PDPageContentStream cs = startPage(doc, "Seite 12 – Empfehlungen");
        float y = 740;

        text(cs, "Empfehlungen", 40, y, 11);
        y -= 25;

        int nr = 1;
        for (WartungsprotokollSeite12.EmpfehlungRow row : s.getEmpfehlungen()) {

            text(cs, nr + ") Zu Nr.: " + safe(row.getZunr()), 40, y, 9);
            y -= 12;

            text(cs, "   Empfehlung: " + safe(row.getEmpfehlung()), 40, y, 9);
            y -= 12;

            text(cs,
                    "   Sicherheit: " + checkbox(Boolean.TRUE.equals(row.getSicherheit())) +
                            "   Ertrag: " + checkbox(Boolean.TRUE.equals(row.getErtrag())) +
                            "   Erhaltung: " + checkbox(Boolean.TRUE.equals(row.getErhaltung())),
                    40, y, 9);
            y -= 12;

            text(cs, "   Kostenschätzung: " + safe(row.getKosten()), 40, y, 9);
            y -= 18;

            nr++;
        }

        cs.close();
    }





}