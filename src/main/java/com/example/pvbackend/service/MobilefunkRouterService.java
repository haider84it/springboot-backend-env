package com.example.pvbackend.service;


import com.example.pvbackend.model.MobilefunkRouterAnlage;
import com.example.pvbackend.repository.MobilefunkRouterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MobilefunkRouterService {


    private final MobilefunkRouterRepository mobilefunkRouterRepository;

    public MobilefunkRouterService(MobilefunkRouterRepository mobilefunkRouterRepository) {
        this.mobilefunkRouterRepository = mobilefunkRouterRepository;
    }


    public List<MobilefunkRouterAnlage> getAllMobilefunkRouter() {
        return mobilefunkRouterRepository.findAll();
    }


    public Optional<MobilefunkRouterAnlage> getMobilefunkRouterById(Long id) {
        return mobilefunkRouterRepository.findById(id);
    }


    public MobilefunkRouterAnlage getMobilefunkRouterByAnlage(Long anlageId) {
        return mobilefunkRouterRepository.findByAnlageId(anlageId);
    }


    public MobilefunkRouterAnlage saveMobilefunkRouter(MobilefunkRouterAnlage mobilefunkRouterAnlage) {
        return mobilefunkRouterRepository.save(mobilefunkRouterAnlage);
    }


    public void deleteMobilefunkRouter(Long id) {
        mobilefunkRouterRepository.deleteById(id);
    }


}
