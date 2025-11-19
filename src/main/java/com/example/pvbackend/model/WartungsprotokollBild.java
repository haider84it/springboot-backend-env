package com.example.pvbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WartungsprotokollBild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "BYTEA")
    private byte[] daten;

    @ManyToOne
    @JoinColumn(name = "wartungsprotokoll_id")
    private Wartungsprotokoll protokoll;


}
