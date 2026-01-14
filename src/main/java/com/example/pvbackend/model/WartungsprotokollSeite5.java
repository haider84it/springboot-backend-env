package com.example.pvbackend.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class WartungsprotokollSeite5 {

    // --- SECTION 5: Messungen PV-Anlage ---
    private Boolean messungModultragendeGemessen;
    private Boolean messungModultragendeNein;
    private Boolean messungModultragendeNichtZutreffend;

    private Boolean messungAlleModulstraengeGemessen;
    private Boolean messungAlleModulstraengeNein;
    private Boolean messungAlleModulstraengeNichtZutreffend;

    private Boolean messungErdungswiderstandGemessen;
    private Boolean messungErdungswiderstandNein;
    private Boolean messungErdungswiderstandNichtZutreffend;

    private Boolean messungErdungDurchgefuehrt;
    private Boolean messungErdungDurchgefuehrtNein;
    private Boolean messungErdungDurchgefuehrtNichtZutreffend;

    private Boolean messungNachDin62446;
    private Boolean messungNachDin62446Nein;
    private Boolean messungNachDin62446NichtZutreffend;

    // Zusatz-Tabelle #1 (5 rows)
    private String zt5Punkt1;
    private String zt5Bemerkung1;
    private String zt5Standort1;
    private Boolean zt5Plan1;
    private String zt5BildNr1;
    private Boolean zt5Beh1;
    private Boolean zt5NichtBeh1;

    private String zt5Punkt2;
    private String zt5Bemerkung2;
    private String zt5Standort2;
    private Boolean zt5Plan2;
    private String zt5BildNr2;
    private Boolean zt5Beh2;
    private Boolean zt5NichtBeh2;

    private String zt5Punkt3;
    private String zt5Bemerkung3;
    private String zt5Standort3;
    private Boolean zt5Plan3;
    private String zt5BildNr3;
    private Boolean zt5Beh3;
    private Boolean zt5NichtBeh3;

    private String zt5Punkt4;
    private String zt5Bemerkung4;
    private String zt5Standort4;
    private Boolean zt5Plan4;
    private String zt5BildNr4;
    private Boolean zt5Beh4;
    private Boolean zt5NichtBeh4;

    private String zt5Punkt5;
    private String zt5Bemerkung5;
    private String zt5Standort5;
    private Boolean zt5Plan5;
    private String zt5BildNr5;
    private Boolean zt5Beh5;
    private Boolean zt5NichtBeh5;


    // --- SECTION 6: Prüfung GAKs ---
    private Boolean gakZugaenglich;
    private Boolean gakZugaenglichNein;
    private Boolean gakZugaenglichNz;


    private Boolean gakStrangsicherungen;
    private Boolean gakStrangsicherungenNein;
    private Boolean gakStrangsicherungenNz;


    private Boolean gakUssEinrichtungen;
    private Boolean gakUssEinrichtungenNein;
    private Boolean gakUssEinrichtungenNz;


    private Boolean gakSchalter;
    private Boolean gakSchalterNein;
    private Boolean gakSchalterNz;


    private Boolean gakFreiVonSchaeden;
    private Boolean gakFreiVonSchaedenNein;
    private Boolean gakFreiVonSchaedenNz;


    private Boolean gakFreiVonFeuchtigkeit;
    private Boolean gakFreiVonFeuchtigkeitNein;
    private Boolean gakFreiVonFeuchtigkeitNz;


    private Boolean gakInnererZustand;
    private Boolean gakInnererZustandNein;
    private Boolean gakInnererZustandNz;


    private Boolean gakErdungOk;
    private Boolean gakErdungOkNein;
    private Boolean gakErdungOkNz;


    private Boolean gakBeschriftungOk;
    private Boolean gakBeschriftungOkNein;
    private Boolean gakBeschriftungOkNz;


    private Boolean gakKabelVerschraubungen;
    private Boolean gakKabelVerschraubungenNein;
    private Boolean gakKabelVerschraubungenNz;


    private Boolean gakAnzugsmomente;
    private Boolean gakAnzugsmomenteNein;
    private Boolean gakAnzugsmomenteNz;


    private Boolean gakGereinigt;
    private Boolean gakGereinigtNein;
    private Boolean gakGereinigtNz;


    private Boolean gakKeineAuffaelligkeiten;
    private Boolean gakKeineAuffaelligkeitenNein;
    private Boolean gakKeineAuffaelligkeitenNz;



    // Zusatz-Tabelle #2 (5 rows)
    private String zt6Punkt1;
    private String zt6Bemerkung1;
    private String zt6Standort1;
    private Boolean zt6Plan1;
    private String zt6BildNr1;
    private Boolean zt6Beh1;
    private Boolean zt6NichtBeh1;

    private String zt6Punkt2;
    private String zt6Bemerkung2;
    private String zt6Standort2;
    private Boolean zt6Plan2;
    private String zt6BildNr2;
    private Boolean zt6Beh2;
    private Boolean zt6NichtBeh2;

    private String zt6Punkt3;
    private String zt6Bemerkung3;
    private String zt6Standort3;
    private Boolean zt6Plan3;
    private String zt6BildNr3;
    private Boolean zt6Beh3;
    private Boolean zt6NichtBeh3;

    private String zt6Punkt4;
    private String zt6Bemerkung4;
    private String zt6Standort4;
    private Boolean zt6Plan4;
    private String zt6BildNr4;
    private Boolean zt6Beh4;
    private Boolean zt6NichtBeh4;

    private String zt6Punkt5;
    private String zt6Bemerkung5;
    private String zt6Standort5;
    private Boolean zt6Plan5;
    private String zt6BildNr5;
    private Boolean zt6Beh5;
    private Boolean zt6NichtBeh5;


    public boolean hasContent() {
        return true;
    }

    private boolean anyBooleanNotNull(Boolean... values) {
        for (Boolean b : values) {
            if (b != null) return true;
        }
        return false;
    }

    private boolean anyStringNotEmpty(String... values) {
        for (String s : values) {
            if (s != null && !s.trim().isEmpty()) return true;
        }
        return false;
    }



}