package com.example.pvbackend.controller;

import com.example.pvbackend.dto.AnlageCreateDto;
import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.pdf.PdfService;
import com.example.pvbackend.service.PhotovoltaikAnlageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/anlagen")
@CrossOrigin(origins = {
        "https://envaris.cloudaxes.de",
        "https://wartung.envaris.de"
})
@RequiredArgsConstructor// Allow React frontend
public class PhotovoltaikAnlageController {

    private final PhotovoltaikAnlageService service;
    private final PdfService pdfService;

    // ✅ Create a new Anlage
    @PostMapping(consumes = "application/json")
    public PhotovoltaikAnlage create(@RequestBody AnlageCreateDto dto) {

        PhotovoltaikAnlage anlage = new PhotovoltaikAnlage();
        anlage.setProjektNummer(dto.projektNummer());
        anlage.setAnlagenName(dto.anlagenName());
        anlage.setAnlagenGroesse(dto.anlagenGroesse());
        anlage.setStrasse(dto.strasse());
        anlage.setPlz(dto.plz());
        anlage.setOrt(dto.ort());
        anlage.setLatitude(dto.latitude());
        anlage.setLongitude(dto.longitude());

        return service.save(anlage);
    }

    // ✅ Get all Anlagen
    @GetMapping
    public List<PhotovoltaikAnlage> getAll() {
        return service.findAll();
    }

    // ✅ Get a single Anlage by ID
    @GetMapping("/{id}")
    public PhotovoltaikAnlage getById(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new RuntimeException("Anlage not found with id " + id));
    }

    // ✅ Update an existing Anlage
    @PutMapping("/{id}")
    public PhotovoltaikAnlage update(@PathVariable Long id, @RequestBody PhotovoltaikAnlage updated) {
        return service.findById(id)
                .map(existing -> {
                    updated.setId(id);
                    return service.save(updated);
                })
                .orElseThrow(() -> new RuntimeException("Anlage not found with id " + id));
    }

    // ✅ Delete an Anlage
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    // ✅ Check if Projekt-Nr. already exists
    @GetMapping("/check/{projektNummer}")
    public Map<String, Boolean> checkProjektNummer(@PathVariable String projektNummer) {
        boolean exists = service.existsByProjektNummer(projektNummer);
        return Map.of("exists", exists);
    }


}
