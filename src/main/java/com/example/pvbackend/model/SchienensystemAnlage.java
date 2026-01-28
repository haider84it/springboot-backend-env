package com.example.pvbackend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "schienensystem_anlage")
public class SchienensystemAnlage {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false)
    private boolean einlagig;

    @Column(nullable = false)
    private boolean zweilagig;

    @Column(nullable = false)
    private boolean aufgestaendert;

    @Column(nullable = false)
    private boolean andere;



    @OneToOne
    @JoinColumn(name = "anlage_id")
    @JsonBackReference("schienensystem_anlage")
    private PhotovoltaikAnlage anlage;



}
