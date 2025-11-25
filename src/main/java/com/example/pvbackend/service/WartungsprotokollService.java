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

                    existing.setVorgang(updated.getVorgang());
                    existing.setAnlagenbezeichnung(updated.getAnlagenbezeichnung());
                    existing.setAuftraggeber(updated.getAuftraggeber());
                    existing.setWartungspaket(updated.getWartungspaket());

                    existing.setDcMessungen(updated.getDcMessungen());
                    existing.setDcNurBeiUnregelmaessigkeiten(updated.getDcNurBeiUnregelmaessigkeiten());
                    existing.setDcVollstaendigOderBereich(updated.getDcVollstaendigOderBereich());
                    existing.setVollstaendigGemaessDin(updated.getVollstaendigGemaessDin());

                    existing.setAcMessungen(updated.getAcMessungen());
                    existing.setAcNurBeiUnregelmaessigkeiten(updated.getAcNurBeiUnregelmaessigkeiten());
                    existing.setAcVollstaendigOderBereich(updated.getAcVollstaendigOderBereich());

                    existing.setZentralwechselrichter(updated.getZentralwechselrichter());
                    existing.setMittelspannungsanlagenErweitert(updated.getMittelspannungsanlagenErweitert());
                    existing.setErdungsmessungenStationen(updated.getErdungsmessungenStationen());
                    existing.setSichtpruefungMittelspannungsanlagen(updated.getSichtpruefungMittelspannungsanlagen());

                    existing.setReinigung(updated.getReinigung());
                    existing.setReinigungWr(updated.getReinigungWr());
                    existing.setReinigungGak(updated.getReinigungGak());
                    existing.setReinigungModule(updated.getReinigungModule());

                    existing.setThermografie(updated.getThermografie());
                    existing.setThermografieVerteiler(updated.getThermografieVerteiler());
                    existing.setThermografieModule(updated.getThermografieModule());
                    existing.setThermografieMspAnlagen(updated.getThermografieMspAnlagen());

                    existing.setKennlinienmessungen(updated.getKennlinienmessungen());

                    existing.setErstErsatzPunkt(updated.getErstErsatzPunkt());
                    existing.setZweitErsatzPunkt(updated.getZweitErsatzPunkt());
                    existing.setDrittErsatzPunkt(updated.getDrittErsatzPunkt());

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
