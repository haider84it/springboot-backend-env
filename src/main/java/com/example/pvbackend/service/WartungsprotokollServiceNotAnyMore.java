/* package com.example.pvbackend.service;

import com.example.pvbackend.model.Wartungsprotokoll;
import com.example.pvbackend.model.WartungsprotokollImage;
import com.example.pvbackend.repository.WartungsprotokollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WartungsprotokollServiceNotAnyMore {

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

        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        Path uploadPath = Paths.get("uploads");
        Files.createDirectories(uploadPath);
        Files.copy(file.getInputStream(), uploadPath.resolve(fileName));

        WartungsprotokollImage img = new WartungsprotokollImage();
        img.setFilename(fileName);
        img.setProtokoll(p);

        p.getBilder().add(img);
        repo.save(p);
    }

}
*/

