package com.example.pvbackend.service;


import com.example.pvbackend.model.DatenloggerAnlage;
import com.example.pvbackend.repository.DatenloggerAnlageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatenloggerAnlageService {


    private final DatenloggerAnlageRepository datenloggerAnlageRepository;


    public DatenloggerAnlageService(DatenloggerAnlageRepository datenloggerAnlageRepository) {
        this.datenloggerAnlageRepository = datenloggerAnlageRepository;
    }


    public List<DatenloggerAnlage> getAllDatenlogger() {
        return datenloggerAnlageRepository.findAll();
    }


    public Optional<DatenloggerAnlage> getDatenloggerById(Long id) {
        return datenloggerAnlageRepository.findById(id);
    }


    public List<DatenloggerAnlage> getDatenloggerByAnlage(Long anlageId) {
        return datenloggerAnlageRepository.findByAnlageId(anlageId);
    }


    public DatenloggerAnlage saveDatenlogger(DatenloggerAnlage datenloggerAnlage) {
        return datenloggerAnlageRepository.save(datenloggerAnlage);
    }

    public void deleteDatenlogger(Long id) {
        datenloggerAnlageRepository.deleteById(id);
    }



}
