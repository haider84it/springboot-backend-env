package com.example.pvbackend.dto;

public record JaNeinNzSbmDto(
        boolean ja,
        boolean nein,
        boolean nz,
        boolean sbm,
        boolean sbeiblatt
) {}
