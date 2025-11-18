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
                stream.showText("Netzwerk-Router:");
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

                if (aufstellungsort.isWohngebaeude()) {
                    stream.showText("  Wohngebäude: true");
                    stream.newLineAtOffset(0, -15);
                }

                if (aufstellungsort.isGarage()) {
                    stream.showText("  Garage: true");
                    stream.newLineAtOffset(0, -15);
                }

                if (aufstellungsort.isLandwirtschaftsgebaeude()) {
                    stream.showText("  Landwirtschaftsgebäude: true");
                    stream.newLineAtOffset(0, -15);
                }

                if (aufstellungsort.isGewerblicheHalle()) {
                    stream.showText("  Gewerbliche Halle: true");
                    stream.newLineAtOffset(0, -15);
                }

                if (aufstellungsort.isFreiland()) {
                    stream.showText("  Freiland: true");
                    stream.newLineAtOffset(0, -15);
                }

                if (aufstellungsort.isAndere()) {
                    stream.showText("  Andere: true");
                    stream.newLineAtOffset(0, -15);
                }
            }

            //9
            if (anlage.getAngabenZumDachAnlage() != null) {
                var angabenZumDachAnlage = anlage.getAngabenZumDachAnlage();
                stream.showText("Angaben zum Dach:");
                stream.newLineAtOffset(0, -15);
                if (angabenZumDachAnlage.isSatteldach()) {
                    stream.showText("  Satteldach");
                    stream.newLineAtOffset(0, -15);
                }
                if (angabenZumDachAnlage.isFlachdach()) {
                    stream.showText("  Flachdach");
                    stream.newLineAtOffset(0, -15);
                }
                if (angabenZumDachAnlage.isPultdach()) {
                    stream.showText("  Pultdach");
                    stream.newLineAtOffset(0, -15);
                }
                if (angabenZumDachAnlage.isSheddach()) {
                    stream.showText("  Sheddach");
                    stream.newLineAtOffset(0, -15);
                }
            }

            //10
            if (anlage.getDacheindeckungAnlage() != null) {
                var dacheindeckungAnlage = anlage.getDacheindeckungAnlage();
                stream.showText("Dacheindeckung:");
                stream.newLineAtOffset(0, -15);
                if (dacheindeckungAnlage.isZiegel()) {
                    stream.showText("  Ziegel");
                    stream.newLineAtOffset(0, -15);
                }
                if (dacheindeckungAnlage.isFaserzement()) {
                    stream.showText("  Faserzement");
                    stream.newLineAtOffset(0, -15);
                }
                if (dacheindeckungAnlage.isMetall()) {
                    stream.showText("  Metall");
                    stream.newLineAtOffset(0, -15);
                }
                if (dacheindeckungAnlage.isBitumen()) {
                    stream.showText("  Bitumen");
                    stream.newLineAtOffset(0, -15);
                }
                if (dacheindeckungAnlage.isFolie()) {
                    stream.showText("  Folie");
                    stream.newLineAtOffset(0, -15);
                }
            }


            //11
            if (anlage.getSchienensystemAnlage() != null) {
                var schienensystemAnlage = anlage.getSchienensystemAnlage();
                stream.showText("Schienensystem:");
                stream.newLineAtOffset(0, -15);
                if (schienensystemAnlage.isEinlagige()) {
                    stream.showText("  einlagig");
                    stream.newLineAtOffset(0, -15);
                }
                if (schienensystemAnlage.isZweilagig()) {
                    stream.showText("  zweilagig");
                    stream.newLineAtOffset(0, -15);
                }
                if (schienensystemAnlage.isAufgeständert()) {
                    stream.showText("  aufgeständert");
                    stream.newLineAtOffset(0, -15);
                }
            }


            //12
            if (anlage.getModulbefestigungAnlage() != null) {
                var modulbefestigungAnlage = anlage.getModulbefestigungAnlage();
                stream.showText("Modulbefestigung:");
                stream.newLineAtOffset(0, -15);
                if (modulbefestigungAnlage.isKlemmen()) {
                    stream.showText("  Klemmen");
                    stream.newLineAtOffset(0, -15);
                }
                if (modulbefestigungAnlage.isEinschubsystem()) {
                    stream.showText("  Einschubsystem");
                    stream.newLineAtOffset(0, -15);
                }
                if (modulbefestigungAnlage.isSchraubeAnUK()) {
                    stream.showText("  Schraube an UK");
                    stream.newLineAtOffset(0, -15);
                }
            }

            //13
            if (anlage.getBefestigungAnlage() != null) {
                var befestigungAnlage = anlage.getBefestigungAnlage();
                stream.showText("Befestigung:");
                stream.newLineAtOffset(0, -15);
                if (befestigungAnlage.isDachhacken()) {
                    stream.showText("  Dachhaken");
                    stream.newLineAtOffset(0, -15);
                }
                if (befestigungAnlage.isStockschrauben()) {
                    stream.showText("  Stockschrauben");
                    stream.newLineAtOffset(0, -15);
                }
                if (befestigungAnlage.isBallastierung()) {
                    stream.showText("  Ballastierung");
                    stream.newLineAtOffset(0, -15);
                }
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
                if (beschwerungAnlage.isWannen()) {
                    stream2.showText("  Wannen");
                    stream2.newLineAtOffset(0, -15);
                }
                if (beschwerungAnlage.isSteine()) {
                    stream2.showText("  Steine");
                    stream2.newLineAtOffset(0, -15);
                }
                if (beschwerungAnlage.isKies()) {
                    stream2.showText("  Kies");
                    stream2.newLineAtOffset(0, -15);
                }
            }


            //15
            if (anlage.getAusrichtungNeigungModule() != null) {
                var ausrichtungNeigungModule = anlage.getAusrichtungNeigungModule();
                stream2.showText("Ausrichtung der Module:");
                stream2.newLineAtOffset(0, -15);
                if (ausrichtungNeigungModule.isSued()) {
                    stream2.showText("  Süd");
                    stream2.newLineAtOffset(0, -15);
                }
                if (ausrichtungNeigungModule.isOst()) {
                    stream2.showText("  Ost");
                    stream2.newLineAtOffset(0, -15);
                }
                if (ausrichtungNeigungModule.isWest()) {
                    stream2.showText("  West");
                    stream2.newLineAtOffset(0, -15);
                }
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

