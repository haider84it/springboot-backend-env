package com.example.pvbackend.service;

import com.example.pvbackend.model.Kunde;
import com.example.pvbackend.repository.KundeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KundeService {

    private final KundeRepository repository;

    public KundeService(KundeRepository repository) {
        this.repository = repository;
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
}
