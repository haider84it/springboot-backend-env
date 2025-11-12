package com.example.pvbackend.repository;

import com.example.pvbackend.model.AusrichtungNeigungModule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AusrichtungNeigungModuleRepository extends JpaRepository<AusrichtungNeigungModule, Long> {

    AusrichtungNeigungModule findByAnlageId(Long anlageId);

}
