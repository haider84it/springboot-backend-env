package com.example.pvbackend.service;

import com.example.pvbackend.model.WartungNeueAnlage;
import com.example.pvbackend.repository.WartungNeueAnlageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WartungNeueAnlageService {

    private final WartungNeueAnlageRepository wartungRepo;

    @Autowired
    public WartungNeueAnlageService(WartungNeueAnlageRepository wartungRepo) {
        this.wartungRepo = wartungRepo;
    }

    public WartungNeueAnlage save(WartungNeueAnlage wartung) {
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
