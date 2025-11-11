package com.example.pvbackend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "modulbefestigung_anlage")
public class ModulbefestigungAnlage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private boolean klemmen;
    private boolean einschubsystem;
    private boolean schraubeAnUK;
    private boolean andere;


    @OneToOne
    @JoinColumn(name = "anlage_id")
    @JsonBackReference("modulbefestigung_anlage")
    private PhotovoltaikAnlage anlage;



}
