package com.example.pvbackend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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



    private Integer jahrInbetriebnahme;
    private Boolean hebebuehne;
    private Boolean anmeldungErforderlich;
    private Integer jahrErsteWartung;
    private String artDerWartung;
    private String wartungsturnus;

}
