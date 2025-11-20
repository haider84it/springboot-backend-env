package com.example.pvbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "service_partner_rates")
@Getter
@Setter
public class ServicePartnerRates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "service_partner_id")
    private ServicePartner partner;

    // ---------- Meister ----------
    private Integer meisterCount;
    private Double meisterRegelstunde;
    private Double meisterNachtarbeit;
    private Double meisterSonnFeiertag;
    private Double meisterFahrtkosten;

    // ---------- Techniker ----------
    private Integer technikerCount;
    private Double technikerRegelstunde;
    private Double technikerNachtarbeit;
    private Double technikerSonnFeiertag;
    private Double technikerFahrtkosten;

    // ---------- Ingenieure ----------
    private Integer ingenieurCount;
    private Double ingenieurRegelstunde;
    private Double ingenieurNachtarbeit;
    private Double ingenieurSonnFeiertag;
    private Double ingenieurFahrtkosten;

    // ---------- Facharbeiter ----------
    private Integer facharbeiterCount;
    private Double facharbeiterRegelstunde;
    private Double facharbeiterNachtarbeit;
    private Double facharbeiterSonnFeiertag;
    private Double facharbeiterFahrtkosten;

    // ---------- Aushilfen ----------
    private Integer aushilfenCount;
    private Double aushilfenRegelstunde;
    private Double aushilfenNachtarbeit;
    private Double aushilfenSonnFeiertag;
    private Double aushilfenFahrtkosten;

    // ---------- Auszubildende ----------
    private Integer azubiCount;
    private Double azubiRegelstunde;
    private Double azubiNachtarbeit;
    private Double azubiSonnFeiertag;
    private Double azubiFahrtkosten;
}
