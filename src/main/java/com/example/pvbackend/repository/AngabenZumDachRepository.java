package com.example.pvbackend.repository;

import com.example.pvbackend.model.AngabenZumDachAnlage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AngabenZumDachRepository extends JpaRepository<AngabenZumDachAnlage, Long> {

    AngabenZumDachAnlage findByAnlageId(Long anlageId);

}
