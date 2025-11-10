package com.example.pvbackend.controller;

import com.example.pvbackend.model.AufstellungsortAnlage;
import com.example.pvbackend.service.AufstellungsortService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/aufstellungsort")
@CrossOrigin(origins = "https://envaris.cloudaxes.de")
@RequiredArgsConstructor
public class AufstellungsortController {

    private final AufstellungsortService aufstellungsortService;


    @GetMapping
    public List<AufstellungsortAnlage> getAufstellungsorte() {
        return aufstellungsortService.getAllAufstellungsorte();
    }

    @GetMapping("/{id}")
    public Optional<AufstellungsortAnlage> getAufstellungsortById(@PathVariable Long id) {
        return aufstellungsortService.getAufstellungsortById(id);
    }


    @GetMapping("/anlage/{analgeId}")
    public AufstellungsortAnlage getAufstellungsortByAnlage(@PathVariable Long anlageId) {
       return aufstellungsortService.getAufstellungsortByAnlage(anlageId);
    }

    @PostMapping
    public AufstellungsortAnlage createAufstellungsort(@RequestBody AufstellungsortAnlage aufstellungsort) {
        return aufstellungsortService.saveAufstellungsort(aufstellungsort);
    }

    @PutMapping("/{id}")
    public AufstellungsortAnlage updateAufstellungsort(@PathVariable Long id, @RequestBody AufstellungsortAnlage updateAufstellungsort) {
        updateAufstellungsort.setId(id);
        return aufstellungsortService.saveAufstellungsort(updateAufstellungsort);
    }



    @DeleteMapping("/{id}")
    public void deleteAufstellungsort(@PathVariable Long id) {
        aufstellungsortService.deleteAufstellungsort(id);
    }


}
