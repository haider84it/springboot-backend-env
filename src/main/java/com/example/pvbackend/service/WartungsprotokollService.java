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
                    existing.setSeite6(updated.getSeite6()); // ✅ ADD THIS
                    existing.setSeite7(updated.getSeite7()); // ✅ ADD THIS
                    existing.setSeite8(updated.getSeite8()); // ✅ ADD THIS Seite 8
                    existing.setSeite9(updated.getSeite9()); // ✅ ADD THIS Seite 9
                    existing.setSeite10(updated.getSeite10()); // ✅ ADD THIS Seite 10
                    existing.setSeite11(updated.getSeite11()); // ✅ ADD THIS Seite 11
                    existing.setSeite11b(updated.getSeite11b()); // ✅ ADD THIS Seite 11b
                    existing.setSeite12(updated.getSeite12()); // ✅ ADD THIS Seite 12

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

        String name = protokoll.getSeite1().getAnlagenbezeichnung();

        Optional<Wartungsprotokoll> existingOpt =
                repository.findBySeite1_Anlagenbezeichnung(name);

        if (existingOpt.isPresent()) {
            Wartungsprotokoll existing = existingOpt.get();

            protokoll.setId(existing.getId()); // 🔥 force UPDATE instead of INSERT
        }

        // Fix Arbeitszeiten relationships
        if (protokoll.getArbeitszeiten() != null) {
            protokoll.getArbeitszeiten()
                    .forEach(a -> a.setWartungsprotokoll(protokoll));
        }

        return repository.save(protokoll);
    }


    // DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
