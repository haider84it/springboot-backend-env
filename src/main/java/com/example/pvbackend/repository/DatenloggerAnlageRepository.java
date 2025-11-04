package com.example.pvbackend.repository;

import com.example.pvbackend.model.DatenloggerAnlage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatenloggerAnlageRepository extends JpaRepository<DatenloggerAnlage, Long> {
    List<DatenloggerAnlage> findByAnlageId(Long anlageId);
}
