package com.example.pvbackend.service;

import com.example.pvbackend.model.Arbeitszeit;
import com.example.pvbackend.model.Wartungsprotokoll;
import com.example.pvbackend.repository.WartungsprotokollRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WartungsprotokollService {

    private final WartungsprotokollRepository repository;

    public WartungsprotokollService(WartungsprotokollRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Wartungsprotokoll create(Wartungsprotokoll protokoll) {
        return repository.save(protokoll);
    }

    // GET ALL
    public List<Wartungsprotokoll> findAll() {
        return repository.findAll();
    }

    // GET BY ID
    public Optional<Wartungsprotokoll> findById(Long id) {
        return repository.findById(id);
    }

    // UPDATE
    // UPDATE
    public Wartungsprotokoll update(Long id, Wartungsprotokoll updated) {
        return repository.findById(id)
                .map(existing -> {

                    // Replace embedded pages completely
                    existing.setSeite1(updated.getSeite1());
                    existing.setSeite2(updated.getSeite2());
                    existing.setSeite3(updated.getSeite3()); // ✅ ADD THIS
                    existing.setSeite4(updated.getSeite4()); // ✅ ADD THIS
                    existing.setSeite5(updated.getSeite5()); // ✅ ADD THIS

                    // Arbeitszeiten (reset relationship)
                    existing.getArbeitszeiten().clear();
                    if (updated.getArbeitszeiten() != null) {
                        updated.getArbeitszeiten().forEach(a -> a.setWartungsprotokoll(existing));
                        existing.getArbeitszeiten().addAll(updated.getArbeitszeiten());
                    }

                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Wartungsprotokoll not found"));
    }

    public Wartungsprotokoll saveProtokoll(Wartungsprotokoll protokoll) {

        if (protokoll.getArbeitszeiten() != null) {
            for (Arbeitszeit a : protokoll.getArbeitszeiten()) {
                a.setWartungsprotokoll(protokoll);
            }
        }

        return repository.save(protokoll);
    }



    // DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
