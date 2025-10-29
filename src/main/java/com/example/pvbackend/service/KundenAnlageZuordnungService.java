package com.example.pvbackend.service;

import com.example.pvbackend.model.KundenAnlageZuordnung;
import com.example.pvbackend.repository.KundenAnlageZuordnungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KundenAnlageZuordnungService {

    private final KundenAnlageZuordnungRepository repository;

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