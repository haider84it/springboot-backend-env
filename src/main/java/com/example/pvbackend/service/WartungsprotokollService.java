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
    public Wartungsprotokoll update(Long id, Wartungsprotokoll updated) {
        return repository.findById(id)
                .map(existing -> {

                    // ⭐ NEW: replace whole Seite 1 in one step
                    existing.setSeite1(updated.getSeite1());

                    // ⭐ Seite 2 fields (still in main entity)
                    existing.setZugangsschluesselVorhanden(updated.getZugangsschluesselVorhanden());
                    existing.setZugangsschluesselAnmerkung(updated.getZugangsschluesselAnmerkung());

                    existing.setThermoKameraVorhanden(updated.getThermoKameraVorhanden());
                    existing.setThermoKameraAnmerkung(updated.getThermoKameraAnmerkung());

                    existing.setVorAbfahrtUnterlagenStand(updated.getVorAbfahrtUnterlagenStand());
                    existing.setVorAbfahrtUnterlagenAnmerkung(updated.getVorAbfahrtUnterlagenAnmerkung());

                    existing.setVorAbfahrtBetreiberKontaktiertStand(updated.getVorAbfahrtBetreiberKontaktiertStand());
                    existing.setVorAbfahrtBetreiberKontaktiertAnmerkung(updated.getVorAbfahrtBetreiberKontaktiertAnmerkung());

                    existing.setVorAbfahrtEigentuemerKontaktiertStand(updated.getVorAbfahrtEigentuemerKontaktiertStand());
                    existing.setVorAbfahrtEigentuemerKontaktiertAnmerkung(updated.getVorAbfahrtEigentuemerKontaktiertAnmerkung());

                    existing.setElektrofachkraftName(updated.getElektrofachkraftName());

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
