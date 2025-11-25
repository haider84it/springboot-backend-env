package com.example.pvbackend.controller;

import com.example.pvbackend.model.Wartungsprotokoll;
import com.example.pvbackend.service.WartungsprotokollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wartungsprotokoll")
@CrossOrigin(origins = "https://envaris.cloudaxes.de")
public class WartungsprotokollController {

    private final WartungsprotokollService service;

    public WartungsprotokollController(WartungsprotokollService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Wartungsprotokoll> create(@RequestBody Wartungsprotokoll protokoll) {
        return ResponseEntity.ok(service.create(protokoll));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Wartungsprotokoll>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Wartungsprotokoll> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Wartungsprotokoll> update(
            @PathVariable Long id,
            @RequestBody Wartungsprotokoll updated
    ) {
        return ResponseEntity.ok(service.update(id, updated));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}