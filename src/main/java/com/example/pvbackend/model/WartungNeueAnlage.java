package com.example.pvbackend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wartung_neue_anlage")
public class WartungNeueAnlage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "anlage_id")
    private PhotovoltaikAnlage anlage;

    private Integer jahrInbetriebnahme;
    private Boolean hebebuehne;
    private Boolean anmeldungErforderlich;
    private Integer jahrErsteWartung;
    private String artDerWartung;
    private String wartungsturnus;

}
