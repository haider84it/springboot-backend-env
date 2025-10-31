package com.example.pvbackend.service;

import com.example.pvbackend.model.ModuleAnlage;
import com.example.pvbackend.repository.ModuleAnlageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleAnlageService {

    private final ModuleAnlageRepository moduleAnlageRepository;

    public ModuleAnlageService(ModuleAnlageRepository moduleAnlageRepository) {
        this.moduleAnlageRepository = moduleAnlageRepository;
    }

    public List<ModuleAnlage> getAllModules() {
        return moduleAnlageRepository.findAll();
    }

    public Optional<ModuleAnlage> getModuleById(Long id) {
        return moduleAnlageRepository.findById(id);
    }

    public ModuleAnlage saveModule(ModuleAnlage moduleAnlage) {
        return moduleAnlageRepository.save(moduleAnlage);
    }

    public void deleteModule(Long id) {
        moduleAnlageRepository.deleteById(id);
    }
}
