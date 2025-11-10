package com.example.pvbackend.service;

import com.example.pvbackend.model.AufstellungsortAnlage;
import com.example.pvbackend.repository.AufstellungsortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AufstellungsortService {

    private final AufstellungsortRepository aufstellungsortRepository;



    public List<AufstellungsortAnlage> getAllAufstellungsorte() {
        return aufstellungsortRepository.findAll();
    }


    public Optional<AufstellungsortAnlage> getAufstellungsortById(Long id) {
        return aufstellungsortRepository.findById(id);
    }

    public AufstellungsortAnlage getAufstellungsortByAnlage(Long anlageId) {
        return aufstellungsortRepository.findByAnlageId(anlageId);
    }

    public AufstellungsortAnlage saveAufstellungsort(AufstellungsortAnlage aufstellungsort) {
        return aufstellungsortRepository.save(aufstellungsort);
    }

    public void deleteAufstellungsort(Long id) {
        aufstellungsortRepository.deleteById(id);
    }



}
