package com.example.pvbackend.controller;


import com.example.pvbackend.model.BeschwerungAnlage;
import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.service.BeschwerungAnlageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/beschwerung")
@CrossOrigin(origins = {
        "https://envaris.cloudaxes.de",
        "https://wartung.envaris.de"
})
@RequiredArgsConstructor
public class BeschwerungAnlageController {

    private final BeschwerungAnlageService beschwerungAnlageService;

    @GetMapping
    public List<BeschwerungAnlage> getAllBeschwerungen() {
        return beschwerungAnlageService.getAllBeschwerungen();
    }

    @GetMapping("/{id}")
    public Optional<BeschwerungAnlage> getBeschwerungAnlage(@PathVariable Long id) {
        return beschwerungAnlageService.getBeschwerungById(id);
    }

    @GetMapping("/anlage/{anlageId}")
    public BeschwerungAnlage getBeschwerungByAnlage(@PathVariable Long anlageId) {
        return beschwerungAnlageService.getBeschwerungByAnlage(anlageId);
    }

    @PostMapping
    public BeschwerungAnlage createBeschwerung(@RequestBody BeschwerungAnlage b) {

        if (b.getAnlage() != null) {
            b.getAnlage().setBeschwerungAnlage(b);
        }

        return beschwerungAnlageService.saveBeschwerung(b);
    }

    @PutMapping("/{id}")
    public BeschwerungAnlage updateBeschwerung(
            @PathVariable Long id,
            @RequestBody BeschwerungAnlage b) {

        b.setId(id);

        if (b.getAnlage() != null) {
            b.getAnlage().setBeschwerungAnlage(b);
        }

        return beschwerungAnlageService.saveBeschwerung(b);
    }

    @DeleteMapping("/{id}")
    public void deleteBeschwerungAnlage(@PathVariable Long id) {
        beschwerungAnlageService.deleteBeschwerungAnlage(id);
    }

}
