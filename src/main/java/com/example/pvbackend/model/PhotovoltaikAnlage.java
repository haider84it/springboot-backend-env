package com.example.pvbackend.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Entity
@Getter
@Setter
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



    @OneToOne(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private WartungNeueAnlage wartung;

    @OneToMany(mappedBy = "anlage")
    @JsonManagedReference
    private List<Kunde> kunden;

    @OneToMany(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<KundenAnlageZuordnung> kundenAnlagzuordnungen;



}
