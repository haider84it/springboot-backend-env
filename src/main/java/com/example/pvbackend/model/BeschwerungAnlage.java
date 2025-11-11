package com.example.pvbackend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "beschwerung_anlage")
public class BeschwerungAnlage {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
