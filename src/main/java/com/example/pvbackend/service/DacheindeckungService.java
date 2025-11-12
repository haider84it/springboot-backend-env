package com.example.pvbackend.service;

import com.example.pvbackend.model.DacheindeckungAnlage;
import com.example.pvbackend.repository.DacheindeckungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DacheindeckungService {

    public final DacheindeckungRepository dacheindeckungRepository;

    public List<DacheindeckungAnlage> getAllDacheindeckungen() {
        return dacheindeckungRepository.findAll();
    }

    public Optional<DacheindeckungAnlage> getDacheindeckungById(Long id) {
        return dacheindeckungRepository.findById(id);
    }


    public DacheindeckungAnlage getDacheindeckungByAnlage(Long anlageId) {
        return dacheindeckungRepository.findByAnlageId(anlageId);
    }


    public DacheindeckungAnlage saveDacheindeckung(DacheindeckungAnlage dacheindeckung) {
        return dacheindeckungRepository.save(dacheindeckung);
    }


    public void deleteDacheindeckung(Long id) {
        dacheindeckungRepository.deleteById(id);
    }



}
