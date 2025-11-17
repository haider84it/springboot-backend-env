package com.example.pvbackend.controller;


import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.model.SchienensystemAnlage;
import com.example.pvbackend.service.SchienensystemAnlageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/schienensystem")
@CrossOrigin(origins = "https://envaris.cloudaxes.de")
@RequiredArgsConstructor
public class SchienensystemController {

    private final SchienensystemAnlageService schienensystemAnlageService;


    @GetMapping
    public List<SchienensystemAnlage> getAllScienensysteme() {
        return schienensystemAnlageService.getAllSchienensysteme();
    }

    @GetMapping("/{id}")
    public Optional<SchienensystemAnlage> getSchienensystem(@PathVariable Long id) {
        return schienensystemAnlageService.getSchienensystemById(id);
    }

    @GetMapping("/anlage/{anlageId}")
    public SchienensystemAnlage getSchienensystemByAnlage(@PathVariable Long anlageId) {
        return schienensystemAnlageService.getSchienensystemByAnlage(anlageId);
    }

    @PostMapping
    public SchienensystemAnlage createSchienensystem(@RequestBody Map<String, Object> body) {

        List<String> systemarten = (List<String>) body.get("systemarten");
        Map anlageMap = (Map) body.get("anlage");
        Long anlageId = Long.valueOf(anlageMap.get("id").toString());

        SchienensystemAnlage sys = new SchienensystemAnlage();
        sys.setAnlage(new PhotovoltaikAnlage(anlageId));

        sys.setEinlagige(systemarten.contains("einlagig"));
        sys.setZweilagig(systemarten.contains("zweilagig"));
        sys.setAufgeständert(systemarten.contains("aufgeständert"));
        sys.setAndere(systemarten.contains("andere"));

        return schienensystemAnlageService.saveSchienensystem(sys);
    }

    @PutMapping("/{id}")
    public SchienensystemAnlage updateSchienensystem(@PathVariable Long id, @RequestBody SchienensystemAnlage updateSchienensystem) {
        updateSchienensystem.setId(id);
        return schienensystemAnlageService.saveSchienensystem(updateSchienensystem);
    }

    @DeleteMapping("/{id}")
    public void deleteSchienensystem(@PathVariable Long id) {
        schienensystemAnlageService.deleteSchienensystem(id);
    }

}
