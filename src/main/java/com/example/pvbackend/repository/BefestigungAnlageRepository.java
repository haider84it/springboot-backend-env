package com.example.pvbackend.repository;

import com.example.pvbackend.model.BefestigungAnlage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BefestigungAnlageRepository extends JpaRepository<BefestigungAnlage, Long> {

    BefestigungAnlage findByAnlageId(Long anlageId);

}
