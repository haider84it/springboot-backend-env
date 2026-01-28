package com.example.pvbackend.service;

import com.example.pvbackend.model.WartungNeueAnlage;
import com.example.pvbackend.repository.PhotovoltaikAnlageRepository;
import com.example.pvbackend.repository.WartungNeueAnlageRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public WartungNeueAnlage save(WartungNeueAnlage wartung) {
        if (wartung.getAnlage() != null && wartung.getAnlage().getId() != null) {
            var anlage = photovoltaikRepo.findById(wartung.getAnlage().getId()).orElseThrow();
            if (anlage.getWartung() != null) {
                wartung.setId(anlage.getWartung().getId());
            }
            wartung.setAnlage(anlage);
            var savedWartung = wartungRepo.save(wartung); // save first, get ID
            anlage.setWartung(savedWartung);              // then link back
            photovoltaikRepo.save(anlage);                // now wartung_id is set
            return savedWartung;
        }
        return wartungRepo.save(wartung);
    }

    public WartungNeueAnlage findById(Long id) {
        return wartungRepo.findById(id).orElse(null);
    }

    public List<WartungNeueAnlage> findAll() {
        return wartungRepo.findAll();
    }

    public WartungNeueAnlage findByAnlageId(Long anlageId) {
        return wartungRepo.findByAnlage_Id(anlageId).orElse(null);
    }

    public void deleteById(Long id) {
        wartungRepo.deleteById(id);
    }
}
