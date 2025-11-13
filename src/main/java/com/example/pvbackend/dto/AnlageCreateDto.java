package com.example.pvbackend.dto;

public record AnlageCreateDto(
        String projektNummer,
        String anlagenName,
        Double anlagenGroesse,
        String strasse,
        String plz,
        String ort,
        Double latitude,
        Double longitude
) {}
