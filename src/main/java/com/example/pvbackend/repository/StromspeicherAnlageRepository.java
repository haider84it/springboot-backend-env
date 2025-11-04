package com.example.pvbackend.repository;

import com.example.pvbackend.model.StromspeicherAnlage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StromspeicherAnlageRepository extends JpaRepository<StromspeicherAnlage, Long> {
    List<StromspeicherAnlage> findByAnlageId(Long anlageId);
}
