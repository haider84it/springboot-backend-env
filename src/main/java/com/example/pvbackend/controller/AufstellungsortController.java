package com.example.pvbackend.controller;

import com.example.pvbackend.model.AufstellungsortAnlage;
import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.service.AufstellungsortService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/aufstellungsort")
@CrossOrigin(origins = {
        "https://envaris.cloudaxes.de",
        "https://wartung.envaris.de"
})
@RequiredArgsConstructor
public class AufstellungsortController {

    private final AufstellungsortService aufstellungsortService;


    @GetMapping
    public List<AufstellungsortAnlage> getAllAufstellungsorte() {
        return aufstellungsortService.getAllAufstellungsorte();
    }

    @GetMapping("/{id}")
    public Optional<AufstellungsortAnlage> getAufstellungsortById(@PathVariable Long id) {
        return aufstellungsortService.getAufstellungsortById(id);
    }


    @GetMapping("/anlage/{anlageId}")
    public AufstellungsortAnlage getAufstellungsortByAnlage(@PathVariable Long anlageId) {
        return aufstellungsortService.getAufstellungsortByAnlage(anlageId);
    }

    @PostMapping
    public AufstellungsortAnlage createAufstellungsort(
            @RequestBody AufstellungsortAnlage ort) {

        if (ort.getAnlage() != null) {
            ort.getAnlage().setAufstellungsortAnlage(ort);
        }

        return aufstellungsortService.saveAufstellungsort(ort);
    }


    @PutMapping("/{id}")
    public AufstellungsortAnlage updateAufstellungsort(
            @PathVariable Long id,
            @RequestBody AufstellungsortAnlage ort) {

        ort.setId(id);

        if (ort.getAnlage() != null) {
            ort.getAnlage().setAufstellungsortAnlage(ort);
        }

        return aufstellungsortService.saveAufstellungsort(ort);
    }


    @DeleteMapping("/{id}")
    public void deleteAufstellungsort(@PathVariable Long id) {
        aufstellungsortService.deleteAufstellungsort(id);
    }


}
