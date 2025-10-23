package com.example.pvbackend.service;

import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.repository.PhotovoltaikAnlageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotovoltaikAnlageService {

    private final PhotovoltaikAnlageRepository repository;

    public PhotovoltaikAnlageService(PhotovoltaikAnlageRepository repository) {
        this.repository = repository;
    }

    // ✅ Create or update an Anlage
    public PhotovoltaikAnlage save(PhotovoltaikAnlage anlage) {
        return repository.save(anlage);
    }

    // ✅ Get all Anlagen
    public List<PhotovoltaikAnlage> findAll() {
        return repository.findAll();
    }

    // ✅ Get a single Anlage by ID
    public Optional<PhotovoltaikAnlage> findById(Long id) {
        return repository.findById(id);
    }

    // ✅ Delete an Anlage
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public boolean existsByProjektNummer(String projektNummer) {
        return repository.existsByProjektNummer(projektNummer);
    }
}