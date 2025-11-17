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
@CrossOrigin(origins = "https://envaris.cloudaxes.de")
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
    public DacheindeckungAnlage createDacheindeckung(@RequestBody Map<String, Object> body) {

        List<String> deckungsarten = (List<String>) body.get("deckungsarten");
        Map anlageMap = (Map) body.get("anlage");
        Long anlageId = Long.valueOf(anlageMap.get("id").toString());

        DacheindeckungAnlage deckung = new DacheindeckungAnlage();
        deckung.setAnlage(new PhotovoltaikAnlage(anlageId));

        deckung.setZiegel(deckungsarten.contains("Ziegel"));
        deckung.setFaserzement(deckungsarten.contains("Faserzement"));
        deckung.setMetall(deckungsarten.contains("Metall"));
        deckung.setBitumen(deckungsarten.contains("Bitumen"));
        deckung.setFolie(deckungsarten.contains("Folie"));

        return dacheindeckungService.saveDacheindeckung(deckung);
    }


    @PutMapping("/{id}")
    public DacheindeckungAnlage updateDacheindeckung(@PathVariable Long id, @RequestBody DacheindeckungAnlage updateDacheindeckung) {
        updateDacheindeckung.setId(id);
        return dacheindeckungService.saveDacheindeckung(updateDacheindeckung);
    }


    @DeleteMapping("/{id}")
    public void deleteDacheindeckung(@PathVariable Long id) {
        dacheindeckungService.deleteDacheindeckung(id);
    }


}
