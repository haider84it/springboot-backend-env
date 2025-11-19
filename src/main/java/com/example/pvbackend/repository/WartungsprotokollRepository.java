package com.example.pvbackend.repository;

import com.example.pvbackend.model.Wartungsprotokoll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WartungsprotokollRepository extends JpaRepository<Wartungsprotokoll, Long> {
}
