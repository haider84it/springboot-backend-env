package com.example.pvbackend.dto;

public record ZusatzRowDto(
        String zupunkt,
        String bemerkung,
        String standort,
        boolean plan,
        String bildnr,
        boolean beh,
        boolean nbeh
) {}
