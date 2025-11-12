package com.example.pvbackend.service;


import com.example.pvbackend.model.AngabenZumDachAnlage;
import com.example.pvbackend.repository.AngabenZumDachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AngabenZumDachService {


    private final AngabenZumDachRepository angabenZumDachRepository;

    public List<AngabenZumDachAnlage> getAllAngabenZumDach() {
        return angabenZumDachRepository.findAll();
    }

    public Optional<AngabenZumDachAnlage> getAngabenZumDachById(Long id) {
        return angabenZumDachRepository.findById(id);
    }

    public AngabenZumDachAnlage getAngabenZumDachByAnlage(Long anlageId) {
        return angabenZumDachRepository.findByAnlageId(anlageId);
    }

    public AngabenZumDachAnlage saveAngabenZumDach(AngabenZumDachAnlage angabenZumDach) {
        return angabenZumDachRepository.save(angabenZumDach);
    }

    public void deleteAngabenZumDach(Long id) {
        angabenZumDachRepository.deleteById(id);
    }



}
