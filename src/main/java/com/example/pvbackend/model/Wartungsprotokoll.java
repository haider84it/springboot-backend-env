package com.example.pvbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "wartungsprotokoll")
public class Wartungsprotokoll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // ---------------------------
    // Seite 1
    // ---------------------------
    @Embedded
    private WartungsprotokollSeite1 seite1 = new WartungsprotokollSeite1();


    // @OneToMany(mappedBy = "protokoll", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<WartungsprotokollImage> bilder = new ArrayList<>();    //maybe later

    // ---------------------------------------
    // Seite 1
    // ---------------------------------------


    // Seite 2
    private Boolean zugangsschluesselVorhanden;
    private String zugangsschluesselAnmerkung;

    private Boolean thermoKameraVorhanden;
    private String thermoKameraAnmerkung;

    private Boolean vorAbfahrtUnterlagenStand;
    private String vorAbfahrtUnterlagenAnmerkung;

    private Boolean vorAbfahrtBetreiberKontaktiertStand;
    private String vorAbfahrtBetreiberKontaktiertAnmerkung;

    private Boolean vorAbfahrtEigentuemerKontaktiertStand;
    private String vorAbfahrtEigentuemerKontaktiertAnmerkung;

    private String elektrofachkraftName;



    @OneToMany(mappedBy = "wartungsprotokoll", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Arbeitszeit> arbeitszeiten = new ArrayList<>();




}
