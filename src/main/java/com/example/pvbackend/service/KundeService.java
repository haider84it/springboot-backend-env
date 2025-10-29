package com.example.pvbackend.service;

import com.example.pvbackend.model.Kunde;
import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.repository.KundeRepository;
import com.example.pvbackend.repository.PhotovoltaikAnlageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KundeService {

    private final KundeRepository repository;
    private final PhotovoltaikAnlageRepository anlageRepository;

    public KundeService(KundeRepository repository, PhotovoltaikAnlageRepository anlageRepository) {
        this.repository = repository;
        this.anlageRepository = anlageRepository;
    }

    // ✅ Create or update Kunde
    public Kunde save(Kunde kunde) {
        return repository.save(kunde);
    }

    // ✅ Get all Kunden
    public List<Kunde> findAll() {
        return repository.findAll();
    }

    // ✅ Get a single Kunde by ID
    public Optional<Kunde> findById(Long id) {
        return repository.findById(id);
    }

    // ✅ Delete a Kunde by ID
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Kunde> findByAnlageId(Long anlageId) {
        return repository.findByAnlageId(anlageId);
    }


    public Kunde assignToAnlage(Long kundeId, Long anlageId) {
        Kunde kunde = repository.findById(kundeId).orElseThrow();
        PhotovoltaikAnlage anlage = anlageRepository.findById(anlageId).orElseThrow();
        kunde.setAnlage(anlage);
        return repository.save(kunde);
    }

}
