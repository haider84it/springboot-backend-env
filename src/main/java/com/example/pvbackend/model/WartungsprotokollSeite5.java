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
    private Boolean gakZugaenglichSbm;
    private Boolean gakZugaenglichBeiblatt;

    private Boolean gakStrangsicherungen;
    private Boolean gakStrangsicherungenNein;
    private Boolean gakStrangsicherungenNz;
    private Boolean gakStrangsicherungenSbm;
    private Boolean gakStrangsicherungenBeiblatt;

    private Boolean gakUssEinrichtungen;
    private Boolean gakUssEinrichtungenNein;
    private Boolean gakUssEinrichtungenNz;
    private Boolean gakUssEinrichtungenSbm;
    private Boolean gakUssEinrichtungenBeiblatt;

    private Boolean gakSchalter;
    private Boolean gakSchalterNein;
    private Boolean gakSchalterNz;
    private Boolean gakSchalterSbm;
    private Boolean gakSchalterBeiblatt;

    private Boolean gakFreiVonSchaeden;
    private Boolean gakFreiVonSchaedenNein;
    private Boolean gakFreiVonSchaedenNz;
    private Boolean gakFreiVonSchaedenSbm;
    private Boolean gakFreiVonSchaedenBeiblatt;

    private Boolean gakFreiVonFeuchtigkeit;
    private Boolean gakFreiVonFeuchtigkeitNein;
    private Boolean gakFreiVonFeuchtigkeitNz;
    private Boolean gakFreiVonFeuchtigkeitSbm;
    private Boolean gakFreiVonFeuchtigkeitBeiblatt;

    private Boolean gakInnererZustand;
    private Boolean gakInnererZustandNein;
    private Boolean gakInnererZustandNz;
    private Boolean gakInnererZustandSbm;
    private Boolean gakInnererZustandBeiblatt;

    private Boolean gakErdungOk;
    private Boolean gakErdungOkNein;
    private Boolean gakErdungOkNz;
    private Boolean gakErdungOkSbm;
    private Boolean gakErdungOkBeiblatt;

    private Boolean gakBeschriftungOk;
    private Boolean gakBeschriftungOkNein;
    private Boolean gakBeschriftungOkNz;
    private Boolean gakBeschriftungOkSbm;
    private Boolean gakBeschriftungOkBeiblatt;

    private Boolean gakKabelVerschraubungen;
    private Boolean gakKabelVerschraubungenNein;
    private Boolean gakKabelVerschraubungenNz;
    private Boolean gakKabelVerschraubungenSbm;
    private Boolean gakKabelVerschraubungenBeiblatt;

    private Boolean gakAnzugsmomente;
    private Boolean gakAnzugsmomenteNein;
    private Boolean gakAnzugsmomenteNz;
    private Boolean gakAnzugsmomenteSbm;
    private Boolean gakAnzugsmomenteBeiblatt;

    private Boolean gakGereinigt;
    private Boolean gakGereinigtNein;
    private Boolean gakGereinigtNz;
    private Boolean gakGereinigtSbm;
    private Boolean gakGereinigtBeiblatt;

    private Boolean gakKeineAuffaelligkeiten;
    private Boolean gakKeineAuffaelligkeitenNein;
    private Boolean gakKeineAuffaelligkeitenNz;
    private Boolean gakKeineAuffaelligkeitenSbm;
    private Boolean gakKeineAuffaelligkeitenBeiblatt;


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
        return anyBooleanNotNull(
                messungModultragendeGemessen,
                messungModultragendeNein,
                messungModultragendeNichtZutreffend,
                messungAlleModulstraengeGemessen,
                messungAlleModulstraengeNein,
                messungAlleModulstraengeNichtZutreffend,
                messungErdungswiderstandGemessen,
                messungErdungswiderstandNein,
                messungErdungswiderstandNichtZutreffend,
                messungErdungDurchgefuehrt,
                messungErdungDurchgefuehrtNein,
                messungErdungDurchgefuehrtNichtZutreffend,
                messungNachDin62446,
                messungNachDin62446Nein,
                messungNachDin62446NichtZutreffend,

                gakZugaenglich, gakZugaenglichNein, gakZugaenglichNz, gakZugaenglichSbm, gakZugaenglichBeiblatt,
                gakStrangsicherungen, gakStrangsicherungenNein, gakStrangsicherungenNz, gakStrangsicherungenSbm, gakStrangsicherungenBeiblatt,
                gakUssEinrichtungen, gakUssEinrichtungenNein, gakUssEinrichtungenNz, gakUssEinrichtungenSbm, gakUssEinrichtungenBeiblatt,
                gakSchalter, gakSchalterNein, gakSchalterNz, gakSchalterSbm, gakSchalterBeiblatt,
                gakFreiVonSchaeden, gakFreiVonSchaedenNein, gakFreiVonSchaedenNz, gakFreiVonSchaedenSbm, gakFreiVonSchaedenBeiblatt,
                gakFreiVonFeuchtigkeit, gakFreiVonFeuchtigkeitNein, gakFreiVonFeuchtigkeitNz, gakFreiVonFeuchtigkeitSbm, gakFreiVonFeuchtigkeitBeiblatt,
                gakInnererZustand, gakInnererZustandNein, gakInnererZustandNz, gakInnererZustandSbm, gakInnererZustandBeiblatt,
                gakErdungOk, gakErdungOkNein, gakErdungOkNz, gakErdungOkSbm, gakErdungOkBeiblatt,
                gakBeschriftungOk, gakBeschriftungOkNein, gakBeschriftungOkNz, gakBeschriftungOkSbm, gakBeschriftungOkBeiblatt,
                gakKabelVerschraubungen, gakKabelVerschraubungenNein, gakKabelVerschraubungenNz, gakKabelVerschraubungenSbm, gakKabelVerschraubungenBeiblatt,
                gakAnzugsmomente, gakAnzugsmomenteNein, gakAnzugsmomenteNz, gakAnzugsmomenteSbm, gakAnzugsmomenteBeiblatt,
                gakGereinigt, gakGereinigtNein, gakGereinigtNz, gakGereinigtSbm, gakGereinigtBeiblatt,
                gakKeineAuffaelligkeiten, gakKeineAuffaelligkeitenNein, gakKeineAuffaelligkeitenNz, gakKeineAuffaelligkeitenSbm, gakKeineAuffaelligkeitenBeiblatt
        )
                || anyStringNotEmpty(
                zt5Punkt1, zt5Bemerkung1, zt5Standort1, zt5BildNr1,
                zt5Punkt2, zt5Bemerkung2, zt5Standort2, zt5BildNr2,
                zt5Punkt3, zt5Bemerkung3, zt5Standort3, zt5BildNr3,
                zt5Punkt4, zt5Bemerkung4, zt5Standort4, zt5BildNr4,
                zt5Punkt5, zt5Bemerkung5, zt5Standort5, zt5BildNr5,
                zt6Punkt1, zt6Bemerkung1, zt6Standort1, zt6BildNr1,
                zt6Punkt2, zt6Bemerkung2, zt6Standort2, zt6BildNr2,
                zt6Punkt3, zt6Bemerkung3, zt6Standort3, zt6BildNr3,
                zt6Punkt4, zt6Bemerkung4, zt6Standort4, zt6BildNr4,
                zt6Punkt5, zt6Bemerkung5, zt6Standort5, zt6BildNr5
        );
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