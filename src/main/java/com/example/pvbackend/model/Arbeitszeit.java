package com.example.pvbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "arbeitszeit")
public class Arbeitszeit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String datum;
    private String name;
    private String beginn;
    private String ende;
    private String stunden;
    private String wetter;
    private String temperatur;

    @ManyToOne
    @JoinColumn(name = "wartungsprotokoll_id")
    private Wartungsprotokoll wartungsprotokoll;
}
