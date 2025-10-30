package com.example.pvbackend.service;

import com.example.pvbackend.model.WartungNeueAnlage;
import com.example.pvbackend.repository.PhotovoltaikAnlageRepository;
import com.example.pvbackend.repository.WartungNeueAnlageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WartungNeueAnlageService {

    private final WartungNeueAnlageRepository wartungRepo;
    private final PhotovoltaikAnlageRepository photovoltaikRepo;

    @Autowired
    public WartungNeueAnlageService(WartungNeueAnlageRepository wartungRepo, PhotovoltaikAnlageRepository photovoltaikRepo) {
        this.wartungRepo = wartungRepo;
        this.photovoltaikRepo = photovoltaikRepo;
    }

    public WartungNeueAnlage save(WartungNeueAnlage wartung) {
        if (wartung.getAnlage() != null && wartung.getAnlage().getId() != null) {
            var anlage = photovoltaikRepo.findById(wartung.getAnlage().getId()).orElse(null);
            if (anlage != null) {
                anlage.setWartung(wartung);
                wartung.setAnlage(anlage);
                photovoltaikRepo.save(anlage);
            }
        } else {
            throw new IllegalArgumentException("Anlage darf nicht null sein");
        }
        return wartungRepo.save(wartung);
    }

    public WartungNeueAnlage findById(Long id) {
        return wartungRepo.findById(id).orElse(null);
    }

    public List<WartungNeueAnlage> findAll() {
        return wartungRepo.findAll();
    }

    public void deleteById(Long id) {
        wartungRepo.deleteById(id);
    }
}
