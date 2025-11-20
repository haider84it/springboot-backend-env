package com.example.pvbackend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "wartungsprotokoll_image")
@Getter
@Setter
public class WartungsprotokollImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    @ManyToOne
    @JoinColumn(name = "protokoll_id")
    private Wartungsprotokoll protokoll;


}
