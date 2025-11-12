package com.example.pvbackend.service;





import com.example.pvbackend.model.BeschwerungAnlage;
import com.example.pvbackend.repository.BeschwerungAnlageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BeschwerungAnlageService {


    private final BeschwerungAnlageRepository beschwerungAnlageRepository;



    public List<BeschwerungAnlage> getAllBeschwerungen() {
        return beschwerungAnlageRepository.findAll();
    }


    public Optional<BeschwerungAnlage> getBeschwerungById(Long id) {
        return beschwerungAnlageRepository.findById(id);
    }


    public BeschwerungAnlage getBeschwerungByAnlage(Long anlageId) {
        return beschwerungAnlageRepository.findByAnlageId(anlageId);
    }

    public BeschwerungAnlage saveBeschwerung(BeschwerungAnlage beschwerungAnlage) {
        return beschwerungAnlageRepository.save(beschwerungAnlage);
    }


    public void deleteBeschwerungAnlage(Long id) {
        beschwerungAnlageRepository.deleteById(id);
    }


}
