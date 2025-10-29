package com.example.pvbackend.service;

import com.example.pvbackend.model.KundenAnlageZuordnung;
import com.example.pvbackend.repository.KundenAnlageZuordnungRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KundenAnlageZuordnungService {

    private final KundenAnlageZuordnungRepository repository;


    public KundenAnlageZuordnungService(KundenAnlageZuordnungRepository repository) {
        this.repository = repository;
    }


    public KundenAnlageZuordnung save(KundenAnlageZuordnung zuordnung) {
        return repository.save(zuordnung);
    }

    public List<KundenAnlageZuordnung> findAll() {
        return repository.findAll();
    }

    public List<KundenAnlageZuordnung> findByAnlageId(Long anlageId) {
        return repository.findByAnlageId(anlageId);
    }
}