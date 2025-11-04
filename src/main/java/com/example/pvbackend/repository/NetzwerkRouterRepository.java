package com.example.pvbackend.repository;

import com.example.pvbackend.model.NetzwerkRouterAnlage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NetzwerkRouterRepository extends JpaRepository<NetzwerkRouterAnlage, Long> {


    NetzwerkRouterAnlage findByAnlageId(Long anlageId);

}
