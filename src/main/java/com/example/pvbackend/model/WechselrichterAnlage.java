package com.example.pvbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wechselrichter_anlage")
public class WechselrichterAnlage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hersteller;
    private String type;
    private String leistung;
    private String art;
    private Integer anzahl;

    @ManyToOne
    @JoinColumn(name = "anlage_id")
    @JsonBackReference("wechselrichter_anlage")
    private PhotovoltaikAnlage anlage;


}

