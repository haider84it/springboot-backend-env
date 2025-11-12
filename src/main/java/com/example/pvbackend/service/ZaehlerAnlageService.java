package com.example.pvbackend.service;


import com.example.pvbackend.model.ZaehlerAnlage;
import com.example.pvbackend.repository.ZaehlerAnlageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ZaehlerAnlageService {


    private final ZaehlerAnlageRepository zaehlerAnlageRepository;

    public List<ZaehlerAnlage> getAllZaehlerAnlagen() {
        return zaehlerAnlageRepository.findAll();
    }

    public Optional<ZaehlerAnlage> getZaehlerAnlageById(Long id) {
        return zaehlerAnlageRepository.findById(id);
    }

    public ZaehlerAnlage getZaehlerByAnlage(Long anlageId) {
        return zaehlerAnlageRepository.findByAnlageId(anlageId);
    }


    public ZaehlerAnlage saveZaehlerAnlage(ZaehlerAnlage zaehlerAnlage) {
        return zaehlerAnlageRepository.save(zaehlerAnlage);
    }

    public void deleteZaehlerAnlage(Long id) {
        zaehlerAnlageRepository.deleteById(id);
    }


}
