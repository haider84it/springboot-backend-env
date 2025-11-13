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
    @JsonManagedReference("wartung_anlage")
    private WartungNeueAnlage wartung;

    @OneToMany(mappedBy = "anlage")
    @JsonManagedReference("kunde_anlage")
    private List<Kunde> kunden;

    @OneToMany(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("zuordnung_anlage")
    private List<KundenAnlageZuordnung> kundenAnlagzuordnungen;


    @OneToMany(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("module_anlage")
    private List<ModuleAnlage> module;


    @OneToMany(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("wechselrichter_anlage")
    private List<WechselrichterAnlage> wechselrichter;


    @OneToMany(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("stromspeicher_anlage")
    private List<StromspeicherAnlage> stromspeicher;


    @OneToMany(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("datenlogger_anlage")
    private List<DatenloggerAnlage> datenlogger;

    @OneToOne(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("mobilfunk_router_anlage")
    private MobilefunkRouterAnlage mobilefunkRouter;

    @OneToOne(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("netzwerk_router_anlage")
    private NetzwerkRouterAnlage netzwerkRouterAnlage;


    @OneToOne(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("aufstellungsort_anlage")
    private AufstellungsortAnlage aufstellungsortAnlage;

    @OneToOne(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("angabenzumdach_anlage")
    private AngabenZumDachAnlage angabenZumDachAnlage;

    @OneToOne(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("dacheindeckung_anlage")
    private DacheindeckungAnlage dacheindeckungAnlage;

    @OneToOne(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("schienensystem_anlage")
    private SchienensystemAnlage schienensystemAnlage;

    @OneToOne(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("modulbefestigung_anlage")
    private ModulbefestigungAnlage modulbefestigungAnlage;

    @OneToOne(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("befestigung_anlage")
    private BefestigungAnlage befestigungAnlage;

    @OneToOne(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("beschwerung_anlage")
    private BeschwerungAnlage beschwerungAnlage;

    @OneToOne(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("ausrichtung_neigung_module")
    private AusrichtungNeigungModule ausrichtungNeigungModule;

    @OneToOne(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("neigung_module_anlage")
    private NeigungModuleAnlage neigungModuleAnlage;


    @OneToOne(mappedBy = "anlage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("zaehler_anlage")
    private ZaehlerAnlage zaehlerAnlage;


}
