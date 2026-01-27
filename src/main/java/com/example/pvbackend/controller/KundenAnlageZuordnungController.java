package com.example.pvbackend.controller;

import com.example.pvbackend.model.Kunde;
import com.example.pvbackend.model.KundenAnlageZuordnung;
import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.repository.KundeRepository;
import com.example.pvbackend.repository.PhotovoltaikAnlageRepository;
import com.example.pvbackend.service.KundenAnlageZuordnungService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zuordnungen")
@CrossOrigin(origins = {
        "https://envaris.cloudaxes.de",
        "https://wartung.envaris.de",
        "http://localhost:3000"
})
@RequiredArgsConstructor
public class KundenAnlageZuordnungController {

    private final KundenAnlageZuordnungService service;
    private final KundeRepository kundeRepository;
    private final PhotovoltaikAnlageRepository anlageRepository;

    @PostMapping
    public KundenAnlageZuordnung create(@RequestBody KundenAnlageZuordnung zuordnung) {

        Kunde kunde = kundeRepository.findById(zuordnung.getKunde().getId())
                .orElseThrow();
        PhotovoltaikAnlage anlage = anlageRepository.findById(zuordnung.getAnlage().getId())
                .orElseThrow();

        zuordnung.setKunde(kunde);
        zuordnung.setAnlage(anlage);

        return service.save(zuordnung);
    }

    @PutMapping("/{id}")
    public KundenAnlageZuordnung update(
            @PathVariable Long id,
            @RequestBody KundenAnlageZuordnung zuordnung) {

        Kunde kunde = kundeRepository.findById(zuordnung.getKunde().getId())
                .orElseThrow();
        PhotovoltaikAnlage anlage = anlageRepository.findById(zuordnung.getAnlage().getId())
                .orElseThrow();

        zuordnung.setId(id);
        zuordnung.setKunde(kunde);
        zuordnung.setAnlage(anlage);

        return service.save(zuordnung);
    }

    @GetMapping("/anlage/{anlageId}")
    public List<KundenAnlageZuordnung> getByAnlage(@PathVariable Long anlageId) {
        return service.findByAnlageId(anlageId);
    }

    @GetMapping
    public List<KundenAnlageZuordnung> getAll() {
        return service.findAll();
    }
}