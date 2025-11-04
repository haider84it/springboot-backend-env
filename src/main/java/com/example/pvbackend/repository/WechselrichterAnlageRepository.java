package com.example.pvbackend.repository;

import com.example.pvbackend.model.WechselrichterAnlage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WechselrichterAnlageRepository extends JpaRepository<WechselrichterAnlage, Long> {

    List<WechselrichterAnlage> findByAnlageId(Long anlageId);

}
