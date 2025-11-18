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
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("ServicePartner not found with id " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
