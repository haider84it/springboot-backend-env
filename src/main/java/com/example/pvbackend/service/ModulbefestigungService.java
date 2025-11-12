package com.example.pvbackend.service;



import com.example.pvbackend.model.ModulbefestigungAnlage;
import com.example.pvbackend.repository.ModulbefestigungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModulbefestigungService {

    private final ModulbefestigungRepository modulbefestigungRepository;



    public List<ModulbefestigungAnlage> getAllModulbefestigungen() {
        return modulbefestigungRepository.findAll();
    }


    public Optional<ModulbefestigungAnlage> getModulbefestigungById(Long id) {
      return modulbefestigungRepository.findById(id);
    }


    public ModulbefestigungAnlage getModulbefestigungByAnlage(Long anlageId) {
        return modulbefestigungRepository.findByAnlageId(anlageId);
    }

    public ModulbefestigungAnlage saveModulbefestigung(ModulbefestigungAnlage modulbefestigung) {
        return modulbefestigungRepository.save(modulbefestigung);
    }

    public void deleteModulbefestigung(Long id) {
        modulbefestigungRepository.deleteById(id);
    }


}
