package com.example.pvbackend.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Entity
@Getter
@Setter
public class PhotovoltaikAnlage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projektNummer;
    private String anlagenName;
    private Double anlagenGroesse;
    private String strasse;
    private String plz;
    private String ort;
    private Double latitude;
    private Double longitude;



    @OneToOne(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("wartung-anlage")
    private WartungNeueAnlage wartung;

    @OneToMany(mappedBy = "anlage")
    @JsonManagedReference("kunde-anlage")
    private List<Kunde> kunden;

    @OneToMany(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("zuordnung-anlage")
    private List<KundenAnlageZuordnung> kundenAnlagzuordnungen;


    @OneToMany(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("module-anlage")
    private List<ModuleAnlage> module;


    @OneToMany(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("wechselrichter-anlage")
    private List<WechselrichterAnlage> wechselrichter;


    @OneToMany(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("stromspeicher-anlage")
    private List<StromspeicherAnlage> stromspeicher;


    @OneToMany(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("datenlogger-anlage")
    private List<DatenloggerAnlage> datenlogger;

    @OneToOne(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("mobilfunk-router-anlage")
    private MobilefunkRouterAnlage mobilefunkRouter;

    @OneToOne(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("netzwerk-router-anlage")
    private NetzwerkRouterAnlage netzwerkRouterAnlage;


    @OneToOne(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("aufstellungsort-anlage")
    private AufstellungsortAnlage aufstellungsortAnlage;


}
