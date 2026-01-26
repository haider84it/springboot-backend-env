package com.example.pvbackend.controller;



import com.example.pvbackend.model.StromspeicherAnlage;
import com.example.pvbackend.service.StromspeicherAnlageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/stromspeicher")
@CrossOrigin(origins = {
        "https://envaris.cloudaxes.de",
        "https://wartung.envaris.de"
})
public class StromspeicherAnlageController {

    private final StromspeicherAnlageService stromspeicherAnlageService;

    public StromspeicherAnlageController(StromspeicherAnlageService stromspeicherAnlageService) {
        this.stromspeicherAnlageService = stromspeicherAnlageService;
    }


    @GetMapping
    public List<StromspeicherAnlage> getAllStromspeicher() {
        return stromspeicherAnlageService.getAllStromspeicher();
    }


    @GetMapping("/{id}")
    public Optional<StromspeicherAnlage> getStromspeicherById(@PathVariable Long id) {
        return stromspeicherAnlageService.getStromspeicherById(id);
    }

    @GetMapping("/anlage/{anlageId}")
    public List<StromspeicherAnlage> getStromspeicherByAnlage(@PathVariable Long anlageId) {
        return stromspeicherAnlageService.getStromspeicherByAnlage(anlageId);
    }


    @PostMapping
    public StromspeicherAnlage createStromspeicher(@RequestBody StromspeicherAnlage stromspeicherAnlage) {
        return stromspeicherAnlageService.saveStromspeicher(stromspeicherAnlage);
    }


    @PutMapping("/{id}")
    public StromspeicherAnlage updateStromspeicher(@PathVariable Long id, @RequestBody StromspeicherAnlage updateStromspeicher) {
        updateStromspeicher.setId(id);
        return stromspeicherAnlageService.saveStromspeicher(updateStromspeicher);
    }

    //6
    @DeleteMapping("/{id}")
    public void deleteStromspeicher(@PathVariable Long id) {
        stromspeicherAnlageService.deleteStromspeicher(id);
    }


}
