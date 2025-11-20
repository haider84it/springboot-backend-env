package com.example.pvbackend.service;

import com.example.pvbackend.model.Wartungsprotokoll;
import com.example.pvbackend.model.WartungsprotokollBild;
import com.example.pvbackend.repository.WartungsprotokollRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

    @Transactional
    public void saveImage(Long id, MultipartFile file) throws IOException {

        System.out.println("saveImage() called → id=" + id + ", fileSize=" + file.getSize());

        Wartungsprotokoll p = repo.findById(id).orElseThrow();

        System.out.println("Found protocol: " + p.getId());
        System.out.println("Current image count BEFORE: " + p.getBilder().size());

        WartungsprotokollBild b = new WartungsprotokollBild();
        b.setDaten(file.getBytes());
        b.setProtokoll(p);
        p.getBilder().add(b);

        System.out.println("Image count AFTER: " + p.getBilder().size());

        repo.save(p);

        System.out.println("SAVE DONE");
    }

}
