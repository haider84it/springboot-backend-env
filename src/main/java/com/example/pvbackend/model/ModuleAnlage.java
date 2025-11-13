package com.example.pvbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "module_anlage")
public class ModuleAnlage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hersteller;
    private String typ;
    private String leistungWp;
    private String art;
    private Integer anzahl;

    @ManyToOne
    @JoinColumn(name = "anlage_id")
    @JsonBackReference("module_anlage")
    private PhotovoltaikAnlage anlage;

}