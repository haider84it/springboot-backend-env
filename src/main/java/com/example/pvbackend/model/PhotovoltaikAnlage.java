package com.example.pvbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wartung_id")
    private WartungNeueAnlage wartung;


}
