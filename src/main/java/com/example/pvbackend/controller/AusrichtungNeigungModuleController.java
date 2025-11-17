package com.example.pvbackend.controller;


import com.example.pvbackend.model.AusrichtungNeigungModule;
import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.service.AusrichtungNeigungModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/ausrichtungneigung")
@CrossOrigin(origins = "https://envaris.cloudaxes.de")
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
    public AusrichtungNeigungModule createAusrichtungModule(@RequestBody Map<String, Object> body) {

        List<String> richtungen = (List<String>) body.get("richtungen");
        Map anlageMap = (Map) body.get("anlage");
        Long anlageId = Long.valueOf(anlageMap.get("id").toString());

        AusrichtungNeigungModule m = new AusrichtungNeigungModule();
        m.setAnlage(new PhotovoltaikAnlage(anlageId));

        m.setSued(richtungen.contains("Süd"));
        m.setOst(richtungen.contains("Ost"));
        m.setWest(richtungen.contains("West"));
        m.setOstWest(richtungen.contains("Ost/West"));
        m.setNord(richtungen.contains("Nord"));
        m.setSuedOst(richtungen.contains("Süd/Ost"));
        m.setSuedWest(richtungen.contains("Süd/West"));
        m.setNordOst(richtungen.contains("Nord/Ost"));
        m.setNordWest(richtungen.contains("Nord/West"));
        m.setAndere(richtungen.contains("andere"));

        return ausrichtungNeigungModuleService.saveAusrichtungNeigungModule(m);
    }

    @PutMapping("/{id}")
    public AusrichtungNeigungModule updateAusrichtungModule(@PathVariable Long id, @RequestBody AusrichtungNeigungModule updateAusrichtungNeigungModule) {
        updateAusrichtungNeigungModule.setId(id);
        return ausrichtungNeigungModuleService.saveAusrichtungNeigungModule(updateAusrichtungNeigungModule);
    }


    @DeleteMapping("/{id}")
    public void deleteAusrichtungModule(@PathVariable Long id) {
        ausrichtungNeigungModuleService.deleteAusrichtungNeigungModule(id);
    }
}
