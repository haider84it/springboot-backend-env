package com.example.pvbackend.service;

import com.example.pvbackend.model.Wartungsprotokoll;
import com.example.pvbackend.model.WartungsprotokollBild;
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

    public void saveImage(Long id, MultipartFile file) throws IOException {
        Wartungsprotokoll p = repo.findById(id).orElseThrow();
        WartungsprotokollBild b = new WartungsprotokollBild();
        b.setDaten(file.getBytes());
        b.setProtokoll(p);
        p.getBilder().add(b);
        repo.save(p);
    }

}
