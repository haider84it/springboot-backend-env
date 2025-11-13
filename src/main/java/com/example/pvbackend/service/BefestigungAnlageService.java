package com.example.pvbackend.service;


import com.example.pvbackend.model.BefestigungAnlage;
import com.example.pvbackend.repository.BefestigungAnlageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BefestigungAnlageService {

    private final BefestigungAnlageRepository befestigungAnlageRepository;

    public List<BefestigungAnlage> getAllBefestigungen() {
        return befestigungAnlageRepository.findAll();
    }

    public Optional<BefestigungAnlage>  getBefestigungAnlageById(Long id) {
        return befestigungAnlageRepository.findById(id);
    }

    public BefestigungAnlage getBefestigungByAnlage(Long anlageId) {
        return befestigungAnlageRepository.findByAnlageId(anlageId);
    }

    public BefestigungAnlage saveBefestigungAnlage(BefestigungAnlage befestigungAnlage) {
        return befestigungAnlageRepository.save(befestigungAnlage);
    }

    public void deleteBefestigungAnlage(Long id) {
        befestigungAnlageRepository.deleteById(id);
    }
}


