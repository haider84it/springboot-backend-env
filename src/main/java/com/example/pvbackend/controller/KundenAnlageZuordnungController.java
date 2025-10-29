package com.example.pvbackend.controller;

import com.example.pvbackend.model.KundenAnlageZuordnung;
import com.example.pvbackend.service.KundenAnlageZuordnungService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zuordnungen")
@RequiredArgsConstructor
public class KundenAnlageZuordnungController {

    private final KundenAnlageZuordnungService service;

    @PostMapping
    public KundenAnlageZuordnung create(@RequestBody KundenAnlageZuordnung zuordnung) {
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