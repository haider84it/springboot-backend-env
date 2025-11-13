package com.example.pvbackend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "netzwerk_router")
public class NetzwerkRouterAnlage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String hersteller;
    private String ipAdresse;
    private String netzwerk;
    private String subnetmask;

    @OneToOne
    @JoinColumn(name = "anlage_id")
    @JsonBackReference("netzwerk_router_anlage")
    private PhotovoltaikAnlage anlage;




}
