package com.example.pvbackend.controller;


import com.example.pvbackend.model.ModulbefestigungAnlage;
import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.service.ModulbefestigungService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/modulbefestigung")
@CrossOrigin(origins = {
        "https://envaris.cloudaxes.de",
        "https://wartung.envaris.de"
})
@RequiredArgsConstructor
public class ModulbefestigungController {

    private final ModulbefestigungService modulbefestigungService;


    @GetMapping
    public List<ModulbefestigungAnlage> getAllModulbefestigunen() {
        return modulbefestigungService.getAllModulbefestigungen();
    }

    @GetMapping("/{id}")
    public Optional<ModulbefestigungAnlage> getModulbefestigung(@PathVariable Long id) {
        return modulbefestigungService.getModulbefestigungById(id);
    }

    @GetMapping("/anlage/{anlageId}")
    public ModulbefestigungAnlage getModulbefestigungByAnlage(@PathVariable Long anlageId) {
        return modulbefestigungService.getModulbefestigungByAnlage(anlageId);
    }

    @PostMapping
    public ModulbefestigungAnlage createModulbefestigung(
            @RequestBody ModulbefestigungAnlage bef) {

        if (bef.getAnlage() != null) {
            bef.getAnlage().setModulbefestigungAnlage(bef);
        }

        return modulbefestigungService.saveModulbefestigung(bef);
    }

    @PutMapping("/{id}")
    public ModulbefestigungAnlage updateModulbefestigung(
            @PathVariable Long id,
            @RequestBody ModulbefestigungAnlage bef) {

        bef.setId(id);

        if (bef.getAnlage() != null) {
            bef.getAnlage().setModulbefestigungAnlage(bef);
        }

        return modulbefestigungService.saveModulbefestigung(bef);
    }

    @DeleteMapping("/{id}")
    public void deleteModulbefestigungAnlage(@PathVariable Long id) {
        modulbefestigungService.deleteModulbefestigung(id);
    }


}
