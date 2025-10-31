package com.example.pvbackend.controller;

import com.example.pvbackend.model.Hersteller;
import com.example.pvbackend.service.HerstellerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hersteller")
@CrossOrigin(origins = "*")
public class HerstellerController {

    private final HerstellerService herstellerService;

    public HerstellerController(HerstellerService herstellerService) {
        this.herstellerService = herstellerService;
    }

    @GetMapping
    public List<Hersteller> getAllHersteller() {
        return herstellerService.getAllHersteller();
    }

    @GetMapping("/{id}")
    public Hersteller getHerstellerById(@PathVariable Long id) {
        return herstellerService.getHerstellerById(id)
                .orElseThrow(() -> new RuntimeException("Hersteller not found with id " + id));
    }

    @PostMapping
    public Hersteller createHersteller(@RequestBody Hersteller hersteller) {
        return herstellerService.createHersteller(hersteller);
    }

    @PutMapping("/{id}")
    public Hersteller updateHersteller(@PathVariable Long id, @RequestBody Hersteller hersteller) {
        return herstellerService.updateHersteller(id, hersteller);
    }

    @DeleteMapping("/{id}")
    public void deleteHersteller(@PathVariable Long id) {
        herstellerService.deleteHersteller(id);
    }
}