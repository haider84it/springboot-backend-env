package com.example.pvbackend.service;


import com.example.pvbackend.model.AusrichtungNeigungModule;
import com.example.pvbackend.repository.AusrichtungNeigungModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AusrichtungNeigungModuleService {


    private final AusrichtungNeigungModuleRepository ausrichtungNeigungModuleRepository;

    public List<AusrichtungNeigungModule> getAllAusrichtungNeigungModule() {
        return ausrichtungNeigungModuleRepository.findAll();
    }


    public Optional<AusrichtungNeigungModule> getAusrichtungNeigungById(Long id) {
        return ausrichtungNeigungModuleRepository.findById(id);
    }

    public AusrichtungNeigungModule getAusrichtungNeigungByAnlage(Long anlageId) {
        return ausrichtungNeigungModuleRepository.findByAnlageId(anlageId);
    }

    public AusrichtungNeigungModule saveAusrichtungNeigungModule(AusrichtungNeigungModule ausrichtungModule) {
        return ausrichtungNeigungModuleRepository.save(ausrichtungModule);
    }


    public void deleteAusrichtungNeigungModule(Long id) {
        ausrichtungNeigungModuleRepository.deleteById(id);
    }

}
