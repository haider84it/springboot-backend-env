package com.example.pvbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stromspeicher_anlage")
public class StromspeicherAnlage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hersteller;
    private String typ;
    private String leistung;
    private Integer anzahl;

    @ManyToOne
    @JoinColumn(name = "anlage_id")
    @JsonBackReference("stromspeicher_anlage")
    private PhotovoltaikAnlage anlage;

}
