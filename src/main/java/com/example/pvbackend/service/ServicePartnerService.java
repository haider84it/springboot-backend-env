package com.example.pvbackend.service;

import com.example.pvbackend.model.ServicePartner;
import com.example.pvbackend.repository.ServicePartnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicePartnerService {

    private final ServicePartnerRepository repository;

    public ServicePartnerService(ServicePartnerRepository repository) {
        this.repository = repository;
    }

    public List<ServicePartner> getAll() {
        return repository.findAll();
    }

    public Optional<ServicePartner> getById(Long id) {
        return repository.findById(id);
    }

    public ServicePartner create(ServicePartner sp) {
        return repository.save(sp);
    }

    public ServicePartner update(Long id, ServicePartner updated) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setPartnerName(updated.getPartnerName());
                    existing.setTelefon(updated.getTelefon());
                    existing.setMobilNr(updated.getMobilNr());
                    existing.setEmail(updated.getEmail());
                    existing.setFirmenName(updated.getFirmenName());
                    existing.setStrasseHausnummer(updated.getStrasseHausnummer());
                    existing.setPlz(updated.getPlz());
                    existing.setOrt(updated.getOrt());




                    //
                    existing.setEinsatzradiusNeinKm(updated.getEinsatzradiusNeinKm());
                    existing.setFernwirktechnikErfahrung(updated.getFernwirktechnikErfahrung());
                    existing.setKonzession(updated.getKonzession());
                    existing.setZaehler(updated.getZaehler());


                    // Basic
                    existing.setEigeneAnlagen(updated.getEigeneAnlagen());
                    existing.setBetreuenPvAnlagen(updated.getBetreuenPvAnlagen());
                    existing.setMonitoringErfahrung(updated.getMonitoringErfahrung());

// Radius
                    existing.setEinsatzradius100km(updated.getEinsatzradius100km());

// Lehrgänge
                    existing.setLehrgangWechselrichter(updated.getLehrgangWechselrichter());
                    existing.setHerstellerWechselrichter(updated.getHerstellerWechselrichter());

                    existing.setLehrgangSpeicher(updated.getLehrgangSpeicher());
                    existing.setHerstellerSpeicher(updated.getHerstellerSpeicher());

                    existing.setLehrgangDatenlogger(updated.getLehrgangDatenlogger());
                    existing.setHerstellerDatenlogger(updated.getHerstellerDatenlogger());

// Normen
                    existing.setVde100_100(updated.getVde100_100());
                    existing.setVde0105_100(updated.getVde0105_100());
                    existing.setVde126_23(updated.getVde126_23());
                    existing.setVde100_410(updated.getVde100_410());
                    existing.setVde100_600(updated.getVde100_600());
                    existing.setVde100_712(updated.getVde100_712());
                    existing.setVde105_305_3(updated.getVde105_305_3());
                    existing.setPvCheck(updated.getPvCheck());

// Qualifikation
                    existing.setSolateurLSG(updated.getSolateurLSG());
                    existing.setSolateurFrei(updated.getSolateurFrei());
                    existing.setSolateurHK(updated.getSolateurHK());
                    existing.setSolateurIHK(updated.getSolateurIHK());
                    existing.setSolateurTUEV(updated.getSolateurTUEV());
                    existing.setGrosserRadiusKm(updated.getGrosserRadiusKm());


                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("ServicePartner not found with id " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
