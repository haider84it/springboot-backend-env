package com.example.pvbackend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "datenlogger_anlage")
public class DatenloggerAnlage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hersteller;
    private String modell;
    private String ipAdresse;
    private String login;
    private String passwort;


    @ManyToOne
    @JoinColumn(name = "anlage_id")
    @JsonBackReference("datenlogger_anlage")
    private PhotovoltaikAnlage anlage;


}
