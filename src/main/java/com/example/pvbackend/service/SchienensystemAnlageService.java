package com.example.pvbackend.service;


import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.model.SchienensystemAnlage;
import com.example.pvbackend.repository.PhotovoltaikAnlageRepository;
import com.example.pvbackend.repository.SchienensystemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SchienensystemAnlageService {


    private final SchienensystemRepository schienensystemRepository;
    private final PhotovoltaikAnlageRepository photovoltaikAnlageRepository;

    public List<SchienensystemAnlage> getAllSchienensysteme() {
        return schienensystemRepository.findAll();
    }


    public Optional<SchienensystemAnlage> getSchienensystemById(Long id) {
        return schienensystemRepository.findById(id);
    }

    public Optional<SchienensystemAnlage> getSchienensystemByAnlage(Long anlageId) {
        return schienensystemRepository.findByAnlageId(anlageId);
    }


    public SchienensystemAnlage saveSchienensystem(SchienensystemAnlage sys) {
        if (sys.getAnlage() != null && sys.getAnlage().getId() != null) {
            PhotovoltaikAnlage anlage =
                    photovoltaikAnlageRepository.findById(sys.getAnlage().getId())
                            .orElseThrow(() -> new RuntimeException("Anlage not found"));

            sys.setAnlage(anlage);
            anlage.setSchienensystemAnlage(sys);
        }

        return schienensystemRepository.save(sys);
    }

    public void deleteSchienensystem(Long id) {
        schienensystemRepository.deleteById(id);
    }


}
