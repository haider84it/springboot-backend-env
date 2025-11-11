package com.example.pvbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "angabenzumdach_anlage")
public class AngabenZumDachAnlage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private boolean satteldach;
    private boolean flachdach;
    private boolean pultdach;
    private boolean sheddach;
    private boolean andere;


    @OneToOne
    @JoinColumn(name = "anlage_id")
    @JsonBackReference("angabenzumdach_anlage")
    private PhotovoltaikAnlage anlage;



}
