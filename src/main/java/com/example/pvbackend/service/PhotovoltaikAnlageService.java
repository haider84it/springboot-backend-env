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

        if(anlage.getMobilefunkRouter() != null) {
            anlage.getMobilefunkRouter().setAnlage(anlage);
        }

        if(anlage.getNetzwerkRouterAnlage() != null) {
            anlage.getNetzwerkRouterAnlage().setAnlage(anlage);
        }

        if(anlage.getZaehlerAnlage() != null) {
            anlage.getZaehlerAnlage().setAnlage(anlage);
        }

        // 🔧 ADD THIS before repository.save(anlage)
        if (anlage.getAufstellungsortAnlage() != null) {
            anlage.getAufstellungsortAnlage().setAnlage(anlage);
        }

        if (anlage.getAngabenZumDachAnlage() != null) {
            anlage.getAngabenZumDachAnlage().setAnlage(anlage);
        }


        if (anlage.getDacheindeckungAnlage() != null) {
            anlage.getDacheindeckungAnlage().setAnlage(anlage);
        }

        if(anlage.getSchienensystemAnlage() != null) {
            anlage.getSchienensystemAnlage().setAnlage(anlage);
        }

        if(anlage.getModulbefestigungAnlage() != null) {
            anlage.getModulbefestigungAnlage().setAnlage(anlage);
        }

        if(anlage.getBefestigungAnlage() != null) {
            anlage.getBefestigungAnlage().setAnlage(anlage);
        }

        if(anlage.getBeschwerungAnlage() != null) {
            anlage.getBeschwerungAnlage().setAnlage(anlage);
        }

        if(anlage.getAusrichtungNeigungModule() != null) {
            anlage.getAusrichtungNeigungModule().setAnlage(anlage);
        }

        if(anlage.getNeigungModuleAnlage() != null) {
            anlage.getNeigungModuleAnlage().setAnlage(anlage);
        }




        if (anlage.getWartung() == null) {
            anlage = repository.save(anlage);
            WartungNeueAnlage wartung = new WartungNeueAnlage();
            wartung.setAnlage(anlage);
            wartungRepo.save(wartung);
            anlage.setWartung(wartung);
        } else {
            anlage.getWartung().setAnlage(anlage);
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