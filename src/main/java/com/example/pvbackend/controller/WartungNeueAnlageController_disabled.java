/*package com.example.pvbackend.controller;

import com.example.pvbackend.model.WartungNeueAnlage;
import com.example.pvbackend.service.WartungNeueAnlageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wartung")
public class WartungNeueAnlageController_disabled {

    private final WartungNeueAnlageService service;

    public WartungNeueAnlageController_disabled(WartungNeueAnlageService service) {
        this.service = service;
    }

    @PostMapping
    public WartungNeueAnlage create(@RequestBody WartungNeueAnlage wartung) {
        return service.save(wartung);
    }

    @GetMapping("/{id}")
    public WartungNeueAnlage getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public WartungNeueAnlage update(@PathVariable Long id, @RequestBody WartungNeueAnlage updated) {
        updated.setId(id);
        return service.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}

*/
