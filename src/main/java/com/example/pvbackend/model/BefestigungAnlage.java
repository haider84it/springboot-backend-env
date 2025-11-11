package com.example.pvbackend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "befestigung_anlage")
public class BefestigungAnlage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean dachhacken;
    private boolean stockschrauben;
    private boolean ballastierung;
    private boolean andere;

    @OneToOne
    @JoinColumn(name = "anlage_id")
    @JsonBackReference("befestigung_anlage")
    private PhotovoltaikAnlage anlage;

    
}
