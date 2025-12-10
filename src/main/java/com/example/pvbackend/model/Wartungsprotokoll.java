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
    @AttributeOverrides({
            @AttributeOverride(name = "anlagenbezeichnung", column = @Column(name = "anlagenbezeichnung")),
            @AttributeOverride(name = "auftraggeber", column = @Column(name = "auftraggeber")),
            @AttributeOverride(name = "vorgang", column = @Column(name = "vorgang")),
            @AttributeOverride(name = "wartungspaket", column = @Column(name = "wartungspaket")),
            @AttributeOverride(name = "dcMessungen", column = @Column(name = "dc_messungen")),
            @AttributeOverride(name = "acMessungen", column = @Column(name = "ac_messungen"))
    })
    private WartungsprotokollSeite1 seite1 = new WartungsprotokollSeite1();


    // @OneToMany(mappedBy = "protokoll", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<WartungsprotokollImage> bilder = new ArrayList<>();    //maybe later

    // ---------------------------------------
    // Seite 1
    // ---------------------------------------


    // Seite 2   ✅ NEW
    @Embedded
    private WartungsprotokollSeite2 seite2 = new WartungsprotokollSeite2();



    // Seite 3  ✅ ADD THIS
    @Embedded
    private WartungsprotokollSeite3 seite3 = new WartungsprotokollSeite3();

    // Seite 4  ✅ ADDed THIS
    @Embedded
    private WartungsprotokollSeite4 seite4 = new WartungsprotokollSeite4();


    // Seite 5  ✅ ADDed THIS
    @Embedded
    private WartungsprotokollSeite5 seite5 = new WartungsprotokollSeite5();

    // Seite 6  ✅ ADDed THIS
    @Embedded
    private WartungsprotokollSeite6 seite6 = new WartungsprotokollSeite6();

    // Seite 7  ✅ ADDed THIS
    @Embedded
    private WartungsprotokollSeite7 seite7 = new WartungsprotokollSeite7();

    // Seite 8  ✅ ADDed THIS
    @Embedded
    private WartungsprotokollSeite8 seite8 = new WartungsprotokollSeite8();

    // Seite 9  ✅ ADDed THIS
    @Embedded
    private WartungsprotokollSeite9 seite9 = new WartungsprotokollSeite9();


    // Seite 10  ✅ ADDed THIS
    @Embedded
    private WartungsprotokollSeite10 seite10 = new WartungsprotokollSeite10();

    // Seite 11  ✅ ADDed THIS
    @Embedded
    private WartungsprotokollSeite11 seite11 = new WartungsprotokollSeite11();

    // Seite 11b  ✅ ADDed THIS
    @Embedded
    private WartungsprotokollSeite11b seite11b = new WartungsprotokollSeite11b();

    // Seite 12  ✅ ADDed THIS
    @Embedded
    private WartungsprotokollSeite12 seite12 = new WartungsprotokollSeite12();



    @OneToMany(mappedBy = "wartungsprotokoll", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Arbeitszeit> arbeitszeiten = new ArrayList<>();








}
