package com.example.pvbackend.repository;

import com.example.pvbackend.model.NeigungModuleAnlage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NeigungModuleAnlageRepository extends JpaRepository<NeigungModuleAnlage, Long> {

    NeigungModuleAnlage findByAnlageId(Long anlageId);

}
