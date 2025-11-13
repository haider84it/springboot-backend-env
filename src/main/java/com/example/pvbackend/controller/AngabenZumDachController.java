package com.example.pvbackend.controller;



import com.example.pvbackend.model.AngabenZumDachAnlage;
import com.example.pvbackend.service.AngabenZumDachService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/angabenzumdach")
@CrossOrigin(origins = "https://envaris.cloudaxes.de")
@RequiredArgsConstructor
public class AngabenZumDachController {

    private final AngabenZumDachService angabenZumDachService;

    @GetMapping
    public List<AngabenZumDachAnlage> getAllAngabenZumDach() {
        return angabenZumDachService.getAllAngabenZumDach();
    }

    @GetMapping("/{id}")
    public Optional<AngabenZumDachAnlage> getAngabenZumDachById(@PathVariable Long id) {
        return angabenZumDachService.getAngabenZumDachById(id);
    }

    @GetMapping("/anlage/{anlageId}")
    public AngabenZumDachAnlage getAngabenZumDachByAnlage(@PathVariable Long anlageId) {
        return angabenZumDachService.getAngabenZumDachByAnlage(anlageId);
    }

    @PostMapping
    public AngabenZumDachAnlage createAngabenZumDach(@RequestBody AngabenZumDachAnlage angabenZumDach) {
        return angabenZumDachService.saveAngabenZumDach(angabenZumDach);
    }


    @PutMapping("/{id}")
    public AngabenZumDachAnlage updateAngabenZumDach(@PathVariable Long id, @RequestBody AngabenZumDachAnlage updateAngabenZumDach) {
        updateAngabenZumDach.setId(id);
        return angabenZumDachService.saveAngabenZumDach(updateAngabenZumDach);
    }


    @DeleteMapping("/{id}")
    public void deleteAngabenZumDach(@PathVariable Long id) {
        angabenZumDachService.deleteAngabenZumDach(id);
    }



}
