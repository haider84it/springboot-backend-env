package com.example.pvbackend.controller;


import com.example.pvbackend.model.NeigungModuleAnlage;
import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.service.NeigungModuleAnlageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/neigungmodule")
@CrossOrigin(origins = {
        "https://envaris.cloudaxes.de",
        "https://wartung.envaris.de"
})
@RequiredArgsConstructor
public class NeigungModuleAnlageController {

    private final NeigungModuleAnlageService neigungModuleAnlageService;

    @GetMapping
    public List<NeigungModuleAnlage> getAllNeigungModuleAnlage() {
        return neigungModuleAnlageService.getAllNeigungModule();
    }

    @GetMapping("/{id}")
    public Optional<NeigungModuleAnlage> getNeigungModuleAnlageById(@PathVariable Long id) {
        return neigungModuleAnlageService.getNeigungModuleById(id);
    }

    @GetMapping("/anlage/{anlageId}")
    public NeigungModuleAnlage getNeigungModulByAnlage(@PathVariable Long anlageId) {
        return neigungModuleAnlageService.getNeigungModuleByAnlage(anlageId);
    }

    @PostMapping
    public NeigungModuleAnlage createNeigungModuleAnlage(
            @RequestBody NeigungModuleAnlage n) {

        if (n.getAnlage() != null) {
            n.getAnlage().setNeigungModuleAnlage(n);
        }

        return neigungModuleAnlageService.saveNeigungModule(n);
    }

    @PutMapping("/{id}")
    public NeigungModuleAnlage updateNeigungModuleAnlage(
            @PathVariable Long id,
            @RequestBody NeigungModuleAnlage n) {

        n.setId(id);

        if (n.getAnlage() != null) {
            n.getAnlage().setNeigungModuleAnlage(n);
        }

        return neigungModuleAnlageService.saveNeigungModule(n);
    }

    @DeleteMapping("/{id}")
    public void deleteNeigungModuleAnlage(@PathVariable Long id) {
        neigungModuleAnlageService.deleteNeigungModule(id);
    }


}
