package com.example.pvbackend.repository;

import com.example.pvbackend.model.ModulbefestigungAnlage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModulbefestigungRepository extends JpaRepository<ModulbefestigungAnlage, Long> {

    ModulbefestigungAnlage findByAnlageId(Long anlageId);

}
