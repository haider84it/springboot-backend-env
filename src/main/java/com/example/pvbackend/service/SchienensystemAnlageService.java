package com.example.pvbackend.service;


import com.example.pvbackend.model.SchienensystemAnlage;
import com.example.pvbackend.repository.SchienensystemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SchienensystemAnlageService {


    private final SchienensystemRepository schienensystemRepository;

    public List<SchienensystemAnlage> getAllSchienensysteme() {
        return schienensystemRepository.findAll();
    }


    public Optional<SchienensystemAnlage> getSchienensystemById(Long id) {
        return schienensystemRepository.findById(id);
    }

    public SchienensystemAnlage getSchienensystemByAnlage(Long anlageId) {
        return schienensystemRepository.findByAnlageId(anlageId);
    }


    public SchienensystemAnlage saveSchienensystem(SchienensystemAnlage schienensystemAnlage) {
        return schienensystemRepository.save(schienensystemAnlage);
    }

    public void deleteSchienensystem(Long id) {
        schienensystemRepository.deleteById(id);
    }


}
