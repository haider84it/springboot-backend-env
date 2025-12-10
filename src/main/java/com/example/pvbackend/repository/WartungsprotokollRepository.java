package com.example.pvbackend.repository;

import com.example.pvbackend.model.Wartungsprotokoll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WartungsprotokollRepository extends JpaRepository<Wartungsprotokoll, Long> {

    Optional<Wartungsprotokoll> findBySeite1_Anlagenbezeichnung(String anlagenbezeichnung);

}
