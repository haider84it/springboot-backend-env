package com.example.pvbackend.service;

import com.example.pvbackend.model.StromspeicherAnlage;
import com.example.pvbackend.repository.StromspeicherAnlageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StromspeicherAnlageService {

    private final StromspeicherAnlageRepository stromspeicherAnlageRepository;


    public StromspeicherAnlageService(StromspeicherAnlageRepository stromspeicherAnlageRepository) {
        this.stromspeicherAnlageRepository = stromspeicherAnlageRepository;
    }


    public List<StromspeicherAnlage> getAllStromspeicher() {
        return stromspeicherAnlageRepository.findAll();
    }


    public Optional<StromspeicherAnlage> getStromspeicherById(Long id) {
        return stromspeicherAnlageRepository.findById(id);
    }

    public List<StromspeicherAnlage> getStromspeicherByAnlage(Long anlageId) {
        return stromspeicherAnlageRepository.findByAnlageId(anlageId);
    }

    public StromspeicherAnlage saveStromspeicher(StromspeicherAnlage stromspeicherAnlage) {
        return stromspeicherAnlageRepository.save(stromspeicherAnlage);
    }

    public void deleteStromspeicher(Long id) {
        stromspeicherAnlageRepository.deleteById(id);
    }


}
