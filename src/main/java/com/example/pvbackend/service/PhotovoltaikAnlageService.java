package com.example.pvbackend.service;

import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.model.WartungNeueAnlage;
import com.example.pvbackend.repository.PhotovoltaikAnlageRepository;
import com.example.pvbackend.repository.WartungNeueAnlageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotovoltaikAnlageService {

    private final PhotovoltaikAnlageRepository repository;
    private final WartungNeueAnlageRepository wartungRepo;

    public PhotovoltaikAnlageService(PhotovoltaikAnlageRepository repository,  WartungNeueAnlageRepository wartungRepo) {
        this.repository = repository;
        this.wartungRepo = wartungRepo;
    }

    // ✅ Create or update an Anlage
    public PhotovoltaikAnlage save(PhotovoltaikAnlage anlage) {
        if (anlage.getWartung() == null) { //
            WartungNeueAnlage wartung = new WartungNeueAnlage();
            wartung.setAnlage(anlage);
            wartungRepo.save(wartung);
            anlage.setWartung(wartung);
        }
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