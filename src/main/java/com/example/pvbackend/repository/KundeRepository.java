package com.example.pvbackend.repository;

import com.example.pvbackend.model.Kunde;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KundeRepository extends JpaRepository<Kunde, Long> {
    List<Kunde> findByAnlageId(Long anlageId);
}
