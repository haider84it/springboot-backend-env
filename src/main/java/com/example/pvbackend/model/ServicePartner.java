package com.example.pvbackend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "service_partner")
@Getter
@Setter
public class ServicePartner {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String partnerName;

    private String telefon;

    private String mobilNr;

    @Column(unique = true)
    private String email;


    private String firmenName;

    private String strasseHausnummer;

    private String plz;

    private String ort;


    private String energieErzeuger;



}
