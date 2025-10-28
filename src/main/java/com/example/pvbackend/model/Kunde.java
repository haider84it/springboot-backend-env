package com.example.pvbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "kunden")
@Getter
@Setter
public class Kunde {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String firma;
    private String anrede;
    private String vorname;
    private String nachname;
    private String strasse;
    private String plzOrt;
    private String telefon;
    private String mobil;

    @Column(unique = true)
    private String email;
}
