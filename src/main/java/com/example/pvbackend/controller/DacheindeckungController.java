package com.example.pvbackend.controller;


import com.example.pvbackend.model.DacheindeckungAnlage;
import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.service.DacheindeckungService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/dacheindeckung")
@CrossOrigin(origins = {
        "https://envaris.cloudaxes.de",
        "https://wartung.envaris.de"
})
@RequiredArgsConstructor
public class DacheindeckungController {

    private final DacheindeckungService dacheindeckungService;

    @GetMapping
    public List<DacheindeckungAnlage> getAllDacheindeckungen() {
        return dacheindeckungService.getAllDacheindeckungen();
    }

    @GetMapping("/{id}")
    public Optional<DacheindeckungAnlage> getDacheindeckung(@PathVariable Long id) {
       return dacheindeckungService.getDacheindeckungById(id);
    }

    @GetMapping("/anlage/{anlageId}")
    public DacheindeckungAnlage getDacheindeckungByAnlage(@PathVariable Long anlageId) {
        return dacheindeckungService.getDacheindeckungByAnlage(anlageId);
    }

    @PostMapping
    public DacheindeckungAnlage createDacheindeckung(
            @RequestBody DacheindeckungAnlage deckung) {

        if (deckung.getAnlage() != null) {
            deckung.getAnlage().setDacheindeckungAnlage(deckung);
        }

        return dacheindeckungService.saveDacheindeckung(deckung);
    }

    @PutMapping("/{id}")
    public DacheindeckungAnlage updateDacheindeckung(
            @PathVariable Long id,
            @RequestBody DacheindeckungAnlage deckung) {

        deckung.setId(id);

        if (deckung.getAnlage() != null) {
            deckung.getAnlage().setDacheindeckungAnlage(deckung);
        }

        return dacheindeckungService.saveDacheindeckung(deckung);
    }


    @DeleteMapping("/{id}")
    public void deleteDacheindeckung(@PathVariable Long id) {
        dacheindeckungService.deleteDacheindeckung(id);
    }


}
