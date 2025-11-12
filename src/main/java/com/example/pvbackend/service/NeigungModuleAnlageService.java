package com.example.pvbackend.service;


import com.example.pvbackend.model.NeigungModuleAnlage;
import com.example.pvbackend.repository.NeigungModuleAnlageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NeigungModuleAnlageService {

    private final NeigungModuleAnlageRepository neigungModuleAnlageRepository;

    public List<NeigungModuleAnlage> getAllNeigungModule() {
        return neigungModuleAnlageRepository.findAll();
    }

    public Optional<NeigungModuleAnlage> getNeigungModuleById(Long id) {
        return neigungModuleAnlageRepository.findById(id);
    }

    public NeigungModuleAnlage getNeigungModuleByAnlage(Long anlageId) {
        return neigungModuleAnlageRepository.findByAnlageId(anlageId);
    }

    public NeigungModuleAnlage saveNeigungModule(NeigungModuleAnlage neigungModule) {
        return neigungModuleAnlageRepository.save(neigungModule);
    }

    public void deleteNeigungModule(Long id) {
        neigungModuleAnlageRepository.deleteById(id);
    }


}
