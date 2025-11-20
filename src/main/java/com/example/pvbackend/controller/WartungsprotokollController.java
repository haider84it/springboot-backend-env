package com.example.pvbackend.controller;

import com.example.pvbackend.model.Wartungsprotokoll;
import com.example.pvbackend.service.WartungsprotokollService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/wartungsprotokoll")
@RequiredArgsConstructor
public class WartungsprotokollController {

    private final WartungsprotokollService service;

    @PostMapping
    public Wartungsprotokoll create(@RequestBody Wartungsprotokoll protokoll) {
        return service.save(protokoll);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getById(@PathVariable Long id) {

        Wartungsprotokoll p = service.findById(id);

        Map<String, Object> response = new HashMap<>();

        response.put("id", p.getId());
        response.put("anlagennummer", p.getAnlagennummer());
        response.put("datum", p.getDatum());
        response.put("uhrzeitVon", p.getUhrzeitVon());
        response.put("uhrzeitBis", p.getUhrzeitBis());
        response.put("temperatur", p.getTemperatur());
        response.put("betriebszustand", p.getBetriebszustand());
        response.put("teilbetriebWert", p.getTeilbetriebWert());
        response.put("einstrahlung", p.getEinstrahlung());
        response.put("verschattung", p.getVerschattung());
        // …all other fields you need…

        // ⭐ Convert images → Base64
        List<Map<String, Object>> bilder = p.getBilder().stream()
                .map(b -> {
                    Map<String, Object> m = new HashMap<>();
                    m.put("id", b.getId());
                    m.put("daten", Base64.getEncoder().encodeToString(b.getDaten()));
                    return m;
                })
                .collect(Collectors.toList());

        response.put("bilder", bilder);

        return response;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // ⭐ IMAGE UPLOAD
    @PostMapping("/{id}/bilder")
    public void uploadImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        System.out.println("UPLOAD RECEIVED → id=" + id + ", size=" + file.getSize());
        service.saveImage(id, file);
    }
}