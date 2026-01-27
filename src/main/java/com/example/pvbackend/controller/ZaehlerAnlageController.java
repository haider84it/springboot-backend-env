package com.example.pvbackend.controller;



import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.model.ZaehlerAnlage;
import com.example.pvbackend.service.ZaehlerAnlageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/zaehleranlage")
@CrossOrigin(origins = {
        "https://envaris.cloudaxes.de",
        "https://wartung.envaris.de"
})
@RequiredArgsConstructor
public class ZaehlerAnlageController {

    private final ZaehlerAnlageService zaehlerAnlageService;

    @GetMapping
    public List<ZaehlerAnlage> getAllZaehlerAnlage () {
        return zaehlerAnlageService.getAllZaehlerAnlage();
    }

    @GetMapping("/{id}")
    public Optional<ZaehlerAnlage> getZaehlerAnlageById(@PathVariable Long id) {
        return zaehlerAnlageService.getZaehlerAnlageById(id);
    }

    @GetMapping("/anlage/{anlageId}")
    public ZaehlerAnlage getZaehlerAnlageByAnlageId(@PathVariable Long anlageId) {
        return zaehlerAnlageService.getZaehlerByAnlage(anlageId);
    }


    @PostMapping
    public ZaehlerAnlage createZaehlerAnlage(
            @RequestBody ZaehlerAnlage z) {

        if (z.getAnlage() != null) {
            z.getAnlage().setZaehlerAnlage(z);
        }

        return zaehlerAnlageService.saveZaehlerAnlage(z);
    }

    @PutMapping("/{id}")
    public ZaehlerAnlage updateZaehlerAnlage(
            @PathVariable Long id,
            @RequestBody ZaehlerAnlage z) {

        z.setId(id);

        if (z.getAnlage() != null) {
            z.getAnlage().setZaehlerAnlage(z);
        }

        return zaehlerAnlageService.saveZaehlerAnlage(z);
    }


    @DeleteMapping("/{id}")
    public void deleteZaehlerAnlage(@PathVariable Long id) {
        zaehlerAnlageService.deleteZaehlerAnlage(id);
    }

}
