package com.example.pvbackend.pdf;

import com.example.pvbackend.model.*;
import com.example.pvbackend.service.PhotovoltaikAnlageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final PhotovoltaikAnlageService anlageService;

    public byte[] generateAnlagePdf(Long id) {
        PhotovoltaikAnlage anlage = anlageService.findById(id)
                .orElseThrow(() -> new RuntimeException("Anlage not found"));
        WartungNeueAnlage wartung = anlage.getWartung();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            PDPageContentStream stream = new PDPageContentStream(doc, page);
            stream.beginText();
            stream.setFont(PDType1Font.HELVETICA, 8);
            stream.newLineAtOffset(40, 750);

            stream.showText("Anlagenname: " + anlage.getAnlagenName());
            stream.newLineAtOffset(0, -15);;
            stream.showText("Projekt-Nr: " + anlage.getProjektNummer());
            stream.newLineAtOffset(0, -15);;
            stream.showText("Groesse: " + anlage.getAnlagenGroesse());
            stream.newLineAtOffset(0, -15);;
            stream.showText("Straße: " + anlage.getStrasse());
            stream.newLineAtOffset(0, -15);;
            stream.showText("PLZ: " + anlage.getPlz());
            stream.newLineAtOffset(0, -15);;
            stream.showText("Ort: " + anlage.getOrt());
            stream.newLineAtOffset(0, -15);;
            stream.showText("Latitude: " + anlage.getLatitude());
            stream.newLineAtOffset(0, -15);;
            stream.showText("Longitude: " + anlage.getLongitude());
            stream.newLineAtOffset(0, -15);;


            //1

            if (wartung != null) {
                stream.showText("Wartung:");
                stream.newLineAtOffset(0, -15);;
                stream.showText("  Erste Wartung: " + wartung.getJahrErsteWartung());
                stream.newLineAtOffset(0, -15);;
                stream.showText("  Art der Wartung: " + wartung.getArtDerWartung());
                stream.newLineAtOffset(0, -15);;
                stream.showText("  Wartungsturnus: " + wartung.getWartungsturnus());
                stream.newLineAtOffset(0, -15);;
            }

            //2
            stream.showText("Kontaktdaten:");
            stream.newLineAtOffset(0, -15);

            for (KundenAnlageZuordnung z : anlage.getKundenAnlagzuordnungen()) {
                Kunde k = z.getKunde();
                stream.showText("  Name: " + k.getVorname() + " " + k.getNachname());
                stream.newLineAtOffset(0, -15);
                stream.showText("  Telefon: " + k.getTelefon());
                stream.newLineAtOffset(0, -15);
                stream.showText("  Email: " + k.getEmail());
                stream.newLineAtOffset(0, -15);
            }


            //3
            if (anlage.getModule() != null && !anlage.getModule().isEmpty()) {
                stream.showText("Module:");
                stream.newLineAtOffset(0, -15);;
                for (ModuleAnlage m : anlage.getModule()) {
                    stream.showText("  Hersteller: " + m.getHersteller() + ", Typ: " + m.getTyp()
                            + ", Leistung: " + m.getLeistungWp() + ", Anzahl: " + m.getAnzahl());
                    stream.newLineAtOffset(0, -15);;
                }
            }


            //4
            stream.showText("Wechselrichter:");
            stream.newLineAtOffset(0, -15);

            for (WechselrichterAnlage wechselrichterAnlage : anlage.getWechselrichter()) {
                stream.showText("  Hersteller: " + wechselrichterAnlage.getHersteller() +
                        ", Typ: " + wechselrichterAnlage.getType() +
                        ", Leistung: " + wechselrichterAnlage.getLeistung() +
                        ", Anzahl: " + wechselrichterAnlage.getAnzahl());
                stream.newLineAtOffset(0, -15);
            }


            //5
            stream.showText("Datenlogger:");
            stream.newLineAtOffset(0, -15);

            for (DatenloggerAnlage datenloggerAnlage : anlage.getDatenlogger()) {
                stream.showText(
                        "  Hersteller: " + datenloggerAnlage.getHersteller() +
                                ", Modell: " + datenloggerAnlage.getModell() +
                                ", IP: " + datenloggerAnlage.getIpAdresse()
                );
                stream.newLineAtOffset(0, -15);
            }


            //6
            MobilefunkRouterAnlage mobilefunkRouter = anlage.getMobilefunkRouter();
            if (mobilefunkRouter != null) {
                stream.showText("Mobilfunk-Router:");
                stream.newLineAtOffset(0, -15);
                stream.showText("  Hersteller: " + mobilefunkRouter.getHersteller());
                stream.newLineAtOffset(0, -15);
                stream.showText("  IP: " + mobilefunkRouter.getIpAdresse());
                stream.newLineAtOffset(0, -15);
            }

            //7
            NetzwerkRouterAnlage netzwerkRouterAnlage = anlage.getNetzwerkRouterAnlage();
            if (netzwerkRouterAnlage != null) {
                stream.showText("Mobilfunk-Router:");
                stream.newLineAtOffset(0, -15);
                stream.showText("  Hersteller: " + netzwerkRouterAnlage.getHersteller());
                stream.newLineAtOffset(0, -15);
                stream.showText("  IP: " + netzwerkRouterAnlage.getIpAdresse());
                stream.newLineAtOffset(0, -15);
            }

            //8
            AufstellungsortAnlage aufstellungsort = anlage.getAufstellungsortAnlage();
            if (aufstellungsort != null) {
                stream.showText("Aufstellungsort:");
                stream.newLineAtOffset(0, -15);
                stream.showText("  Garage: " + aufstellungsort.isGarage());
                stream.newLineAtOffset(0, -15);
            }

            //9
            if (anlage.getAngabenZumDachAnlage() != null) {
                var angabenZumDachAnlage = anlage.getAngabenZumDachAnlage();
                stream.showText("Angaben zum Dach:");
                stream.newLineAtOffset(0, -15);
                stream.showText("  Satteldach: " + angabenZumDachAnlage.isSatteldach());
                stream.newLineAtOffset(0, -15);
                stream.showText("  Flachdach: " + angabenZumDachAnlage.isFlachdach());
                stream.newLineAtOffset(0, -15);
                stream.showText("  Pultdach: " + angabenZumDachAnlage.isPultdach());
                stream.newLineAtOffset(0, -15);
                stream.showText("  Sheddach: " + angabenZumDachAnlage.isSheddach());
                stream.newLineAtOffset(0, -15);
            }

            //10
            if (anlage.getDacheindeckungAnlage() != null) {
                var dacheindeckungAnlage = anlage.getDacheindeckungAnlage();
                stream.showText("Dacheindeckung:");
                stream.newLineAtOffset(0, -15);
                stream.showText("  Ziegel: " + dacheindeckungAnlage.isZiegel());
                stream.newLineAtOffset(0, -15);
                stream.showText("  Faserzement: " + dacheindeckungAnlage.isFaserzement());
                stream.newLineAtOffset(0, -15);
                stream.showText("  Metall: " + dacheindeckungAnlage.isMetall());
                stream.newLineAtOffset(0, -15);
                stream.showText("  Bitumen: " + dacheindeckungAnlage.isBitumen());
                stream.newLineAtOffset(0, -15);
                stream.showText("  Folie: " + dacheindeckungAnlage.isFolie());
                stream.newLineAtOffset(0, -15);
            }


            //11
            if (anlage.getSchienensystemAnlage() != null) {
                var schienensystemAnlage = anlage.getSchienensystemAnlage();
                stream.showText("Schienensystem:");
                stream.newLineAtOffset(0, -15);
                stream.showText("  einlagig: " + schienensystemAnlage.isEinlagige());
                stream.newLineAtOffset(0, -15);
                stream.showText("  zweilagig: " + schienensystemAnlage.isZweilagig());
                stream.newLineAtOffset(0, -15);
                stream.showText("  aufgeständert: " + schienensystemAnlage.isAufgeständert());
                stream.newLineAtOffset(0, -15);
            }


            //12
            if (anlage.getModulbefestigungAnlage() != null) {
                var modulbefestigungAnlage = anlage.getModulbefestigungAnlage();
                stream.showText("Modulbefestigung:");
                stream.newLineAtOffset(0, -15);
                stream.showText("  Klemmen: " + modulbefestigungAnlage.isKlemmen());
                stream.newLineAtOffset(0, -15);
                stream.showText("  Einschubsystem: " + modulbefestigungAnlage.isEinschubsystem());
                stream.newLineAtOffset(0, -15);
                stream.showText("  Schraube an UK: " + modulbefestigungAnlage.isSchraubeAnUK());
                stream.newLineAtOffset(0, -15);
            }

            //13
            if (anlage.getBefestigungAnlage() != null) {
                var befestigungAnlage = anlage.getBefestigungAnlage();
                stream.showText("Befestigung:");
                stream.newLineAtOffset(0, -15);
                stream.showText("  Dachhaken: " + befestigungAnlage.isDachhacken());
                stream.newLineAtOffset(0, -15);
                stream.showText("  Stockschrauben: " + befestigungAnlage.isStockschrauben());
                stream.newLineAtOffset(0, -15);
                stream.showText("  Ballastierung: " + befestigungAnlage.isBallastierung());
                stream.newLineAtOffset(0, -15);
            }



            // + add more fields if the page1 has more space
            stream.endText();
            stream.close();

            PDPage page2 = new PDPage();
            doc.addPage(page2);

            PDPageContentStream stream2 = new PDPageContentStream(doc, page2);
            stream2.beginText();
            stream2.setFont(PDType1Font.HELVETICA, 8);
            stream2.newLineAtOffset(40, 750);

            //14
            if (anlage.getBeschwerungAnlage() != null) {
                var beschwerungAnlage = anlage.getBeschwerungAnlage();
                stream2.showText("Beschwerung:");
                stream2.newLineAtOffset(0, -15);
                stream2.showText("  Wannen: " + beschwerungAnlage.isWannen());
                stream2.newLineAtOffset(0, -15);
                stream2.showText("  Steine: " + beschwerungAnlage.isSteine());
                stream2.newLineAtOffset(0, -15);
                stream2.showText("  Kies: " + beschwerungAnlage.isKies());
                stream2.newLineAtOffset(0, -15);
            }


            //15
            if (anlage.getAusrichtungNeigungModule() != null) {
                var ausrichtungNeigungModule = anlage.getAusrichtungNeigungModule();
                stream2.showText("Ausrichtung der Module:");
                stream2.newLineAtOffset(0, -15);
                stream2.showText("  Süd: " + ausrichtungNeigungModule.isSued());
                stream2.newLineAtOffset(0, -15);
                stream2.showText("  Ost: " + ausrichtungNeigungModule.isOst());
                stream2.newLineAtOffset(0, -15);
                stream2.showText("  West: " + ausrichtungNeigungModule.isWest());
                stream2.newLineAtOffset(0, -15);
                // add more if needed **************************************************
            }


            //16
            if (anlage.getNeigungModuleAnlage() != null) {
                var neigungModuleAnlage = anlage.getNeigungModuleAnlage();
                stream2.showText("Neigung der Module: " + neigungModuleAnlage.getNeigung() + "°");
                stream2.newLineAtOffset(0, -15);
            }


            //17
            if (anlage.getZaehlerAnlage() != null) {
                var zaehlerAnlage = anlage.getZaehlerAnlage();
                stream2.showText("Zähler:");
                stream2.newLineAtOffset(0, -15);
                stream2.showText("  Zählernummer: " + zaehlerAnlage.getZaehlernummer());
                stream2.newLineAtOffset(0, -15);
                stream2.showText("  Wandlerfaktor: " + zaehlerAnlage.getWandlerFaktor());
                stream2.newLineAtOffset(0, -15);
            }

            // + add more fields
            stream2.endText();
            stream2.close();

            doc.save(out);
        } catch (Exception e) {
            throw new RuntimeException("PDF error", e);
        }

        return out.toByteArray();
    }
}

