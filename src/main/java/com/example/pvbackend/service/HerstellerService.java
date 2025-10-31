package com.example.pvbackend.service;

import com.example.pvbackend.model.Hersteller;
import com.example.pvbackend.repository.HerstellerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HerstellerService {

    private final HerstellerRepository herstellerRepository;

    public HerstellerService(HerstellerRepository herstellerRepository) {
        this.herstellerRepository = herstellerRepository;
    }

    public List<Hersteller> getAllHersteller() {
        return herstellerRepository.findAll();
    }

    public Optional<Hersteller> getHerstellerById(Long id) {
        return herstellerRepository.findById(id);
    }

    public Hersteller createHersteller(Hersteller hersteller) {
        return herstellerRepository.save(hersteller);
    }

    public Hersteller updateHersteller(Long id, Hersteller updatedHersteller) {
        return herstellerRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedHersteller.getName());
                    existing.setWechselrichter(updatedHersteller.isWechselrichter());
                    existing.setEnergiespeicher(updatedHersteller.isEnergiespeicher());
                    existing.setDatenlogger(updatedHersteller.isDatenlogger());
                    existing.setModul(updatedHersteller.isModul());
                    return herstellerRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Hersteller not found with id " + id));
    }

    public void deleteHersteller(Long id) {
        herstellerRepository.deleteById(id);
    }
}