package com.example.pvbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "service_partner_info")
@Getter
@Setter
public class ServicePartnerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "service_partner_id")
    private ServicePartner partner;

    // --- Basic Yes/No fields ---
    private Boolean eigeneAnlagen;
    private Boolean betreuenPvAnlagen;
    private Boolean monitoringErfahrung;

    private Boolean einsatzradius100km;
    private Integer einsatzradiusNeinKm;
    private Integer einsatzradiusBisKm;

    private Boolean grosserRadiusMoeglich;
    private Integer grosserRadiusKm;

    private Boolean rufbereitschaft;
    private Boolean notebookVorhanden;
    private Boolean tabletVorhanden;

    private Boolean kenntnisNetzwerktechnik;
    private Boolean ipAdressenPingen;
    private Boolean kenntnisTelefone;
    private Boolean kenntnisMittelspannung;

    // --- Lehrgänge mit Hersteller ---
    private Boolean lehrgangWechselrichter;
    private String herstellerWechselrichter;

    private Boolean lehrgangZentralwechselrichter;
    private String herstellerZentral;

    private Boolean lehrgangSpeicher;
    private String herstellerSpeicher;

    private Boolean lehrgangDatenlogger;
    private String herstellerDatenlogger;

    // --- Spezialkenntnisse ---
    private Boolean fernwirktechnikErfahrung;
    private Boolean konzessionVorhanden;
    private String konzessionsDetails;

    private Boolean zaehlerRegisterLesen;

    // --- Normen (checkbox groups) ---
    private Boolean vde100_100;
    private Boolean vde0105_100;
    private Boolean vde126_23;
    private Boolean vde100_410;
    private Boolean vde100_600;
    private Boolean vde100_712;
    private Boolean vde105_305_3;
    private Boolean pvCheck;

    // --- Qualifikation ---
    private Boolean solateurLSG;
    private Boolean solateurFrei;
    private Boolean solateurHK;
    private Boolean solateurIHK;
    private Boolean solateurTUEV;
}
