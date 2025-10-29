package com.example.pvbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "kunden_anlage_zuordnung")
public class KundenAnlageZuordnung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "kunde_id")
    private Kunde kunde;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "anlage_id")
    private PhotovoltaikAnlage anlage;

    private LocalDate vertragsbeginn;
    private Double leistungKwp;
    private String seriennummerWechselrichter;
}
