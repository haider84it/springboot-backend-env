package com.example.pvbackend.repository;

import com.example.pvbackend.model.ModuleAnlage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleAnlageRepository extends JpaRepository<ModuleAnlage, Long> {
    List<ModuleAnlage> findByAnlageId(Long anlageId);
}
