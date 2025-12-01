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


    // Seite 2   ✅ NEW
    @Embedded
    private WartungsprotokollSeite2 seite2 = new WartungsprotokollSeite2();



    @OneToMany(mappedBy = "wartungsprotokoll", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Arbeitszeit> arbeitszeiten = new ArrayList<>();




}
