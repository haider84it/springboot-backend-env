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


    // ===== NEW FIELDS (add these) =====
    private Boolean eigeneAnlagen;
    private Boolean betreuenPvAnlagen;
    private Boolean monitoringErfahrung;

    private Boolean einsatzradius100km;
    private Integer einsatzradiusNeinKm;
    private Integer grosserRadiusKm;

    private Boolean lehrgangWechselrichter;
    private String herstellerWechselrichter;

    private Boolean lehrgangSpeicher;
    private String herstellerSpeicher;

    private Boolean lehrgangDatenlogger;
    private String herstellerDatenlogger;

    private Boolean fernwirktechnikErfahrung;
    private Boolean konzession;
    private Boolean zaehler;

    // Normen
    private Boolean vde100_100;
    private Boolean vde0105_100;
    private Boolean vde126_23;
    private Boolean vde100_410;
    private Boolean vde100_600;
    private Boolean vde100_712;
    private Boolean vde105_305_3;
    private Boolean pvCheck;

    // Qualifikation
    private Boolean solateurLSG;
    private Boolean solateurFrei;
    private Boolean solateurHK;
    private Boolean solateurIHK;
    private Boolean solateurTUEV;



}
