package com.example.pvbackend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "zaehler_anlage")
public class ZaehlerAnlage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String zaehlernummer;
    private BigDecimal wandlerFaktor;

    @OneToOne
    @JoinColumn(name = "anlage_id")
    @JsonBackReference("zaehler_anlage")
    private PhotovoltaikAnlage anlage;




}
