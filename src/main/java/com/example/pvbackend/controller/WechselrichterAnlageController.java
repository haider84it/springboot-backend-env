package com.example.pvbackend.controller;

import com.example.pvbackend.model.WechselrichterAnlage;
import com.example.pvbackend.service.WechselrichterAnlageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wechselrichter")
@CrossOrigin(origins = "*")
public class WechselrichterAnlageController {

    private final WechselrichterAnlageService wechselrichterAnlageService;

    public WechselrichterAnlageController(WechselrichterAnlageService wechselrichterAnlageService) {
        this.wechselrichterAnlageService = wechselrichterAnlageService;
    }


    @GetMapping
    public List<WechselrichterAnlage> getAllWechselrichter() {
        return wechselrichterAnlageService.getAllWechselricher();
    }


    @GetMapping("/{id}")
    public Optional<WechselrichterAnlage> getWechselrichterById(@PathVariable Long id) {
        return wechselrichterAnlageService.getWechselrichterById(id);
    }


    @GetMapping("/anlage/{anlageId}")
    public List<WechselrichterAnlage> getWechselrichterByAnlage(@PathVariable Long anlageId) {
        return wechselrichterAnlageService.getWechselrichterByAnlage(anlageId);
    }


    @PostMapping
    public WechselrichterAnlage createWechselrichter(@RequestBody WechselrichterAnlage wechselrichterAnlage) {
        return wechselrichterAnlageService.saveWechselrichter(wechselrichterAnlage);
    }


    @PutMapping("/{id}")
    public WechselrichterAnlage updateWechselrichter(@PathVariable Long id, @RequestBody WechselrichterAnlage updateWechselrichter) {
        updateWechselrichter.setId(id);
        return wechselrichterAnlageService.saveWechselrichter(updateWechselrichter);
    }


    @DeleteMapping("/{id}")
    public void deleteWechselrichter(@PathVariable Long id) {
        wechselrichterAnlageService.deleteWechselrichter(id);
    }


}
