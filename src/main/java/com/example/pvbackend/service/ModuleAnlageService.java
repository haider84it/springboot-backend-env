package com.example.pvbackend.service;

import com.example.pvbackend.model.ModuleAnlage;
import com.example.pvbackend.repository.ModuleAnlageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ModuleAnlageService {

    //repsitory for dependency injection
    private final ModuleAnlageRepository moduleAnlageRepository;

    //constructor
    public ModuleAnlageService(ModuleAnlageRepository moduleAnlageRepository) {
        this.moduleAnlageRepository = moduleAnlageRepository;
    }

    //1
    public List<ModuleAnlage> getAllModules() {
        return moduleAnlageRepository.findAll();
    }

    //2
    public Optional<ModuleAnlage> getModuleById(Long id) {
        return moduleAnlageRepository.findById(id);
    }

    //3
    public List<ModuleAnlage> getModulesByAnlage(Long anlageId) {
        return moduleAnlageRepository.findByAnlageId(anlageId);
    }

    //4
    public ModuleAnlage saveModule(ModuleAnlage moduleAnlage) {
        return moduleAnlageRepository.save(moduleAnlage);
    }

    //5
    public void deleteModule(Long id) {
        moduleAnlageRepository.deleteById(id);
    }
}
