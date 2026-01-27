package com.example.pvbackend.controller;


import com.example.pvbackend.model.AusrichtungNeigungModule;
import com.example.pvbackend.service.AusrichtungNeigungModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ausrichtungneigung")
@CrossOrigin(origins = {
        "https://envaris.cloudaxes.de",
        "https://wartung.envaris.de"
})
@RequiredArgsConstructor
public class AusrichtungNeigungModuleController {

    private final AusrichtungNeigungModuleService ausrichtungNeigungModuleService;

    @GetMapping
    public List<AusrichtungNeigungModule> getAllAusrichtungNeigungModule() {
        return ausrichtungNeigungModuleService.getAllAusrichtungNeigungModule();
    }

    @GetMapping("/{id}")
    public Optional<AusrichtungNeigungModule> getAusrichtungNeigungModule(@PathVariable Long id) {
        return ausrichtungNeigungModuleService.getAusrichtungNeigungById(id);
    }

    @GetMapping("/anlage/{anlageId}")
    public AusrichtungNeigungModule getAusrichtungModuleByAnlage(@PathVariable Long anlageId) {
        return ausrichtungNeigungModuleService.getAusrichtungNeigungByAnlage(anlageId);
    }

    @PostMapping
    public AusrichtungNeigungModule createAusrichtungModule(
            @RequestBody AusrichtungNeigungModule m) {

        if (m.getAnlage() != null) {
            m.getAnlage().setAusrichtungNeigungModule(m);
        }

        return ausrichtungNeigungModuleService.saveAusrichtungNeigungModule(m);
    }

    @PutMapping("/{id}")
    public AusrichtungNeigungModule updateAusrichtungModule(
            @PathVariable Long id,
            @RequestBody AusrichtungNeigungModule m) {

        m.setId(id);

        if (m.getAnlage() != null) {
            m.getAnlage().setAusrichtungNeigungModule(m);
        }

        return ausrichtungNeigungModuleService.saveAusrichtungNeigungModule(m);
    }

    @DeleteMapping("/{id}")
    public void deleteAusrichtungModule(@PathVariable Long id) {
        ausrichtungNeigungModuleService.deleteAusrichtungNeigungModule(id);
    }
}
