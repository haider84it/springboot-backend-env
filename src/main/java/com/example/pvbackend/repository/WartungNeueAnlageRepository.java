package com.example.pvbackend.repository;

import com.example.pvbackend.model.WartungNeueAnlage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WartungNeueAnlageRepository extends JpaRepository<WartungNeueAnlage, Long> {
    Optional<WartungNeueAnlage> findByAnlage_Id(Long anlageId);
}
