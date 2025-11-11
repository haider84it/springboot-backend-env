package com.example.pvbackend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dacheindeckung_anlage")
public class DacheindeckungAnlage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private boolean ziegel;
    private boolean faserzement;
    private boolean metall;
    private boolean bitumen;
    private boolean folie;

    @OneToOne
    @JoinColumn(name = "anlage_id")
    @JsonBackReference("dacheindeckung_anlage")
    private PhotovoltaikAnlage anlage;



}
