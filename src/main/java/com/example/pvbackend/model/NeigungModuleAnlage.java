package com.example.pvbackend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "neigung_module_anlage")
public class NeigungModuleAnlage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 5, scale = 2)
    private BigDecimal neigung;


    @OneToOne
    @JoinColumn(name = "anlage_id")
    @JsonBackReference("neigung_module_anlage")
    private PhotovoltaikAnlage anlage;
}
