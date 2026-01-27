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
@CrossOrigin(origins = {
        "https://envaris.cloudaxes.de",
        "https://wartung.envaris.de"
})
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
    public SchienensystemAnlage createSchienensystem(
            @RequestBody SchienensystemAnlage sys) {

        if (sys.getAnlage() != null) {
            sys.getAnlage().setSchienensystemAnlage(sys);
        }

        return schienensystemAnlageService.saveSchienensystem(sys);
    }

    @PutMapping("/{id}")
    public SchienensystemAnlage updateSchienensystem(
            @PathVariable Long id,
            @RequestBody SchienensystemAnlage sys) {

        sys.setId(id);

        if (sys.getAnlage() != null) {
            sys.getAnlage().setSchienensystemAnlage(sys);
        }

        return schienensystemAnlageService.saveSchienensystem(sys);
    }

    @DeleteMapping("/{id}")
    public void deleteSchienensystem(@PathVariable Long id) {
        schienensystemAnlageService.deleteSchienensystem(id);
    }

}
