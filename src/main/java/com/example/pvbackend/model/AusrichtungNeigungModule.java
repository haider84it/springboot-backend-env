package com.example.pvbackend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ausrichtung_neigung_module")
public class AusrichtungNeigungModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean sued;
    private boolean ost;
    private boolean west;
    private boolean ostWest;
    private boolean nord;
    private boolean suedOst;
    private boolean suedWest;
    private boolean nordOst;
    private boolean nordWest;
    private boolean andere;


    @OneToOne
    @JoinColumn(name = "anlage_id")
    @JsonBackReference("ausrichtung_neigung_module")
    private PhotovoltaikAnlage anlage;


}
