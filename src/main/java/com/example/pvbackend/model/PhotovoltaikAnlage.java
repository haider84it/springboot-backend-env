package com.example.pvbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PhotovoltaikAnlage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projektNummer;
    private String anlagenName;
    private Double anlagenGroesse;
    private String strasse;
    private String plz;
    private String ort;
    private Double latitude;
    private Double longitude;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProjektNummer() { return projektNummer; }
    public void setProjektNummer(String projektNummer) { this.projektNummer = projektNummer; }

    public String getAnlagenName() { return anlagenName; }
    public void setAnlagenName(String anlagenName) { this.anlagenName = anlagenName; }

    public Double getAnlagenGroesse() { return anlagenGroesse; }
    public void setAnlagenGroesse(Double anlagenGroesse) { this.anlagenGroesse = anlagenGroesse; }

    public String getStrasse() { return strasse; }
    public void setStrasse(String strasse) { this.strasse = strasse; }

    public String getPlz() { return plz; }
    public void setPlz(String plz) { this.plz = plz; }

    public String getOrt() { return ort; }
    public void setOrt(String ort) { this.ort = ort; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
}
