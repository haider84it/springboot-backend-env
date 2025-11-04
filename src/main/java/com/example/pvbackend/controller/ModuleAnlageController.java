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
    //service for dependency injection
    private final ModuleAnlageService moduleAnlageService;
    //constructor
    public ModuleAnlageController(ModuleAnlageService moduleAnlageService) {
        this.moduleAnlageService = moduleAnlageService;
    }
     //1
    @GetMapping
    public List<ModuleAnlage> getAllModules() {
        return moduleAnlageService.getAllModules();
    }
    //2
    @GetMapping("/{id}")
    public Optional<ModuleAnlage> getModuleById(@PathVariable Long id) {
        return moduleAnlageService.getModuleById(id);
    }
    //3
    @GetMapping("/anlage/{anlageId}")
    public List<ModuleAnlage> getModulesByAnlage(@PathVariable Long anlageId) {
        return moduleAnlageService.getModulesByAnlage(anlageId);
    }
    //4
    @PostMapping
    public ModuleAnlage createModule(@RequestBody ModuleAnlage moduleAnlage) {
        return moduleAnlageService.saveModule(moduleAnlage);
    }
    //5
    @PutMapping("/{id}")
    public ModuleAnlage updateModule(@PathVariable Long id, @RequestBody ModuleAnlage updatedModule) {
        updatedModule.setId(id);
        return moduleAnlageService.saveModule(updatedModule);
    }
    //6
    @DeleteMapping("/{id}")
    public void deleteModule(@PathVariable Long id) {
        moduleAnlageService.deleteModule(id);
    }
}
