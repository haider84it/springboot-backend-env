package com.example.pvbackend.dto;

import java.util.List;

public record WartungsprotokollSeite5Dto(

        // ===== SECTION 5 =====
        List<JaNeinNzDto> messungenPV,

        // Zusatz-Tabelle #1
        List<ZusatzRowDto> zusatz1,

        // ===== SECTION 6 =====
        List<JaNeinNzSbmDto> pruefungGAKs,

        // Zusatz-Tabelle #2
        List<ZusatzRowDto> zusatz2

) {}
