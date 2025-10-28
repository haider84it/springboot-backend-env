package com.example.pvbackend.controller;

import com.example.pvbackend.model.Kunde;
import com.example.pvbackend.service.KundeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kunden")
@CrossOrigin(origins = "https://envaris.cloudaxes.de") // Allow React frontend
public class KundeController {

    private final KundeService service;

    public KundeController(KundeService service) {
        this.service = service;
    }

    // ✅ Create a new Kunde
    @PostMapping
    public Kunde create(@RequestBody Kunde kunde) {
        return service.save(kunde);
    }

    // ✅ Get all Kunden
    @GetMapping
    public List<Kunde> getAll() {
        return service.findAll();
    }

    // ✅ Get a single Kunde by ID
    @GetMapping("/{id}")
    public Kunde getById(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new RuntimeException("Kunde not found with id " + id));
    }

    // ✅ Update Kunde
    @PutMapping("/{id}")
    public Kunde update(@PathVariable Long id, @RequestBody Kunde updated) {
        return service.findById(id)
                .map(existing -> {
                    updated.setId(id);
                    return service.save(updated);
                })
                .orElseThrow(() -> new RuntimeException("Kunde not found with id " + id));
    }

    // ✅ Delete Kunde
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }


    // ✅ Get all Kunden belonging to a specific Anlage
    @GetMapping("/anlage/{anlageId}")
    public List<Kunde> getKundenByAnlage(@PathVariable Long anlageId) {
        return service.findByAnlageId(anlageId);
    }


}