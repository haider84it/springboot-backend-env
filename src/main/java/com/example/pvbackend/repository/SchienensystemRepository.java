package com.example.pvbackend.repository;

import com.example.pvbackend.model.SchienensystemAnlage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchienensystemRepository extends JpaRepository<SchienensystemAnlage, Long> {
    SchienensystemAnlage findByAnlageId(Long anlageId);
}
