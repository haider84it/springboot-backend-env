package com.example.pvbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "service_partner_technik")
@Getter
@Setter
public class ServicePartnerTechnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "service_partner_id")
    private ServicePartner partner;

    // Fahrzeuge
    private Double fahrtkostenKm;
    private Boolean mitarbeiterInbegriffen;
    private Boolean pkwKombi;
    private Boolean transporter;
    private Boolean lkwPritsche;

    // Messtechnik
    private Boolean kennlinienmessgeraet;
    private String kennlinienWelche;

    private Boolean solartaktor;
    private String solartaktorWelche;

    private Boolean infrarotKamera;
    private String infrarotWelche;

    private Boolean elektroLumineszenz;
    private String elektroLumineszenzWelche;

    private Boolean netzanalysegeraet;
    private String netzanalyseWelche;

    private Boolean stringmessgeraet;
    private String stringmessgeraetWelche;

    private Boolean vdeMessung;
    private String vdeWelche;

    // Sonstige Technik
    private Boolean rollgeruest;
    private Boolean pvReinigung;
    private Boolean geruestteile;
    private Boolean dachdeckerlift;
}