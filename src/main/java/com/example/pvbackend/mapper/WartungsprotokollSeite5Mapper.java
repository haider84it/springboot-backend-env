package com.example.pvbackend.mapper;

import com.example.pvbackend.dto.*;
import com.example.pvbackend.model.WartungsprotokollSeite5;

public class WartungsprotokollSeite5Mapper {

    public static WartungsprotokollSeite5 toEntity(WartungsprotokollSeite5Dto dto) {
        WartungsprotokollSeite5 e = new WartungsprotokollSeite5();

        // ===== SECTION 5 (5.1–5.5) =====
        e.setMessungModultragendeGemessen(dto.messungenPV().get(0).ja());
        e.setMessungModultragendeNein(dto.messungenPV().get(0).nein());
        e.setMessungModultragendeNichtZutreffend(dto.messungenPV().get(0).nz());

        e.setMessungAlleModulstraengeGemessen(dto.messungenPV().get(1).ja());
        e.setMessungAlleModulstraengeNein(dto.messungenPV().get(1).nein());
        e.setMessungAlleModulstraengeNichtZutreffend(dto.messungenPV().get(1).nz());

        e.setMessungErdungswiderstandGemessen(dto.messungenPV().get(2).ja());
        e.setMessungErdungswiderstandNein(dto.messungenPV().get(2).nein());
        e.setMessungErdungswiderstandNichtZutreffend(dto.messungenPV().get(2).nz());

        e.setMessungErdungDurchgefuehrt(dto.messungenPV().get(3).ja());
        e.setMessungErdungDurchgefuehrtNein(dto.messungenPV().get(3).nein());
        e.setMessungErdungDurchgefuehrtNichtZutreffend(dto.messungenPV().get(3).nz());

        e.setMessungNachDin62446(dto.messungenPV().get(4).ja());
        e.setMessungNachDin62446Nein(dto.messungenPV().get(4).nein());
        e.setMessungNachDin62446NichtZutreffend(dto.messungenPV().get(4).nz());

        // ===== ZUSATZ-TABELLE #1 =====
        mapZusatz(e, dto.zusatz1(), true);

        // ===== SECTION 6 (GAKs) =====
        e.setGakZugaenglich(dto.pruefungGAKs().get(0).ja());
        e.setGakZugaenglichNein(dto.pruefungGAKs().get(0).nein());
        e.setGakZugaenglichNz(dto.pruefungGAKs().get(0).nz());

        // (repeat for indices 1–12 exactly the same way)

        // ===== ZUSATZ-TABELLE #2 =====
        mapZusatz(e, dto.zusatz2(), false);

        return e;
    }

    // ===== helper for Zusatz tables =====
    private static void mapZusatz(
            WartungsprotokollSeite5 e,
            java.util.List<ZusatzRowDto> rows,
            boolean section5
    ) {
        for (int i = 0; i < rows.size(); i++) {
            ZusatzRowDto r = rows.get(i);
            int n = i + 1;

            if (section5) {
                e.getClass(); // placeholder for clarity
                // example:
                // e.setZt5Punkt1(r.zupunkt());
            } else {
                // example:
                // e.setZt6Punkt1(r.zupunkt());
            }
        }
    }
}
