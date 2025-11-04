package com.example.pvbackend.service;


import com.example.pvbackend.model.NetzwerkRouterAnlage;
import com.example.pvbackend.repository.NetzwerkRouterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class NetzwerkRouterService {


    private final NetzwerkRouterRepository netzwerkRouterRepository;



    public NetzwerkRouterService(NetzwerkRouterRepository netzwerkRouterRepository) {
        this.netzwerkRouterRepository = netzwerkRouterRepository;
    }


    public List<NetzwerkRouterAnlage> getAllNetzwerkRouter() {
        return netzwerkRouterRepository.findAll();
    }


    public Optional<NetzwerkRouterAnlage>  getNetzwerkRouterById(Long id) {
        return netzwerkRouterRepository.findById(id);
    }


    public NetzwerkRouterAnlage getNetzwerkRouterByAnlage(Long anlageId) {
        return netzwerkRouterRepository.findByAnlageId(anlageId);
    }


    public NetzwerkRouterAnlage saveNetzwerkRouter(NetzwerkRouterAnlage netzwerkRouterAnlage) {
        return netzwerkRouterRepository.save(netzwerkRouterAnlage);
    }


    public void deleteNetzwerkRouter(Long id) {
        netzwerkRouterRepository.deleteById(id);
    }



}
