package com.example.pvbackend.controller;


import com.example.pvbackend.model.NeigungModuleAnlage;
import com.example.pvbackend.service.NeigungModuleAnlageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/neigungmodule")
@CrossOrigin(origins = "https://envaris.cloudaxes.de")
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
    public NeigungModuleAnlage createNeigungModuleAnlage(@RequestBody NeigungModuleAnlage neigungModuleAnlage) {
        return neigungModuleAnlageService.saveNeigungModule(neigungModuleAnlage);
    }

    @PutMapping("/{id}")
    public NeigungModuleAnlage updateNeigungModuleAnlage(@PathVariable Long id, @RequestBody NeigungModuleAnlage updateNeigungModuleAnlage) {
        updateNeigungModuleAnlage.setId(id);
        return neigungModuleAnlageService.saveNeigungModule(updateNeigungModuleAnlage);
    }

    @DeleteMapping("/{id}")
    public void deleteNeigungModuleAnlage(@PathVariable Long id) {
        neigungModuleAnlageService.deleteNeigungModule(id);
    }


}
