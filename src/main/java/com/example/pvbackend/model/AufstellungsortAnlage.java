package com.example.pvbackend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "aufstellungsort_anlage")
public class AufstellungsortAnlage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private boolean wohngebaeude;
    private boolean garage;
    private boolean landwirtschaftsgebaeude;
    private boolean gewerblicheHalle;
    private boolean freiland;
    private boolean andere;


    @OneToOne
    @JoinColumn(name = "anlage_id")
    @JsonBackReference("aufstellungsort_anlage")
    private PhotovoltaikAnlage anlage;

}
