package com.example.pvbackend.repository;

import com.example.pvbackend.model.MobilefunkRouterAnlage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobilefunkRouterRepository extends JpaRepository<MobilefunkRouterAnlage, Long> {

     MobilefunkRouterAnlage findByAnlageId(Long anlageId);

}
