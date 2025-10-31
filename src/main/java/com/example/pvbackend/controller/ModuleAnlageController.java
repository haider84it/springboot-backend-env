package com.example.pvbackend.controller;

import com.example.pvbackend.model.ModuleAnlage;
import com.example.pvbackend.service.ModuleAnlageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/module")
@CrossOrigin(origins = "*")
public class ModuleAnlageController {

    private final ModuleAnlageService moduleAnlageService;

    public ModuleAnlageController(ModuleAnlageService moduleAnlageService) {
        this.moduleAnlageService = moduleAnlageService;
    }

    @GetMapping
    public List<ModuleAnlage> getAllModules() {
        return moduleAnlageService.getAllModules();
    }

    @GetMapping("/{id}")
    public Optional<ModuleAnlage> getModuleById(@PathVariable Long id) {
        return moduleAnlageService.getModuleById(id);
    }

    @PostMapping
    public ModuleAnlage createModule(@RequestBody ModuleAnlage moduleAnlage) {
        return moduleAnlageService.saveModule(moduleAnlage);
    }

    @PutMapping("/{id}")
    public ModuleAnlage updateModule(@PathVariable Long id, @RequestBody ModuleAnlage updatedModule) {
        updatedModule.setId(id);
        return moduleAnlageService.saveModule(updatedModule);
    }

    @DeleteMapping("/{id}")
    public void deleteModule(@PathVariable Long id) {
        moduleAnlageService.deleteModule(id);
    }
}
