package com.example.pvbackend.controller;


import com.example.pvbackend.model.DacheindeckungAnlage;
import com.example.pvbackend.service.DacheindeckungService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public DacheindeckungAnlage getDacheindeckungAnlage(@PathVariable Long anlageId) {
        return dacheindeckungService.getDacheindeckungByAnlage(anlageId);
    }

    @PostMapping
    public DacheindeckungAnlage createDacheindeckung(@RequestBody DacheindeckungAnlage dacheindeckungAnlage) {
        return dacheindeckungService.saveDacheindeckung(dacheindeckungAnlage);
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
