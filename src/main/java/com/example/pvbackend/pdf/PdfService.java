package com.example.pvbackend.pdf;

import com.example.pvbackend.model.ModuleAnlage;
import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.model.WartungNeueAnlage;
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
            stream.setFont(PDType1Font.HELVETICA, 12);
            stream.newLineAtOffset(40, 750);

            stream.showText("Anlagenname: " + anlage.getAnlagenName());
            stream.newLine();
            stream.showText("Projekt-Nr: " + anlage.getProjektNummer());
            stream.newLine();
            stream.showText("Groesse: " + anlage.getAnlagenGroesse());
            stream.newLine();
            stream.showText("Straße: " + anlage.getStrasse());
            stream.newLine();
            stream.showText("PLZ: " + anlage.getPlz());
            stream.newLine();
            stream.showText("Ort: " + anlage.getOrt());
            stream.newLine();
            stream.showText("Latitude: " + anlage.getLatitude());
            stream.newLine();
            stream.showText("Longitude: " + anlage.getLongitude());
            stream.newLine();

            if (wartung != null) {
                stream.showText("Wartung:");
                stream.newLine();
                stream.showText("  Erste Wartung: " + wartung.getJahrErsteWartung());
                stream.newLine();
                stream.showText("  Art der Wartung: " + wartung.getArtDerWartung());
                stream.newLine();
                stream.showText("  Wartungsturnus: " + wartung.getWartungsturnus());
                stream.newLine();
            }
            if (anlage.getModule() != null && !anlage.getModule().isEmpty()) {
                stream.showText("Module:");
                stream.newLine();
                for (ModuleAnlage m : anlage.getModule()) {
                    stream.showText("  Hersteller: " + m.getHersteller() + ", Typ: " + m.getTyp()
                            + ", Leistung: " + m.getLeistungWp() + ", Anzahl: " + m.getAnzahl());
                    stream.newLine();
                }
            }
            // + add more fields
            stream.endText();
            stream.close();

            doc.save(out);
        } catch (Exception e) {
            throw new RuntimeException("PDF error", e);
        }

        return out.toByteArray();
    }
}

