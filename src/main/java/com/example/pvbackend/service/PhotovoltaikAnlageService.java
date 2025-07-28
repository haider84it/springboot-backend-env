package com.example.pvbackend.service;

import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.repository.PhotovoltaikAnlageRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PhotovoltaikAnlageService {

    private final PhotovoltaikAnlageRepository repository;

    public PhotovoltaikAnlageService(PhotovoltaikAnlageRepository repository) {
        this.repository = repository;
    }

    public PhotovoltaikAnlage save(PhotovoltaikAnlage anlage) {
        return repository.save(anlage);
    }

    public List<PhotovoltaikAnlage> findAll() {
        return repository.findAll();
    }
}