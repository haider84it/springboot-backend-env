package com.example.pvbackend.repository;

import com.example.pvbackend.model.SchienensystemAnlage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchienensystemRepository extends JpaRepository<SchienensystemAnlage, Long> {

    Optional<SchienensystemAnlage> findByAnlageId(Long anlageId);
}
