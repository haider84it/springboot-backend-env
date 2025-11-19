package com.example.pvbackend.service;

import com.example.pvbackend.model.Wartungsprotokoll;
import com.example.pvbackend.repository.WartungsprotokollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class WartungsprotokollService {

    private final WartungsprotokollRepository repo;

    public Wartungsprotokoll save(Wartungsprotokoll w) {
        return repo.save(w);
    }

    public Wartungsprotokoll findById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void saveImage(Long id, MultipartFile file) {
        Wartungsprotokoll protokoll = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Protokoll not found"));

        try {
            byte[] bytes = file.getBytes();
            protokoll.getBilder().add(bytes);   // <-- your entity needs a List<byte[]> bilder
            repo.save(protokoll);
        } catch (IOException e) {
            throw new RuntimeException("Could not store image", e);
        }
    }

}
