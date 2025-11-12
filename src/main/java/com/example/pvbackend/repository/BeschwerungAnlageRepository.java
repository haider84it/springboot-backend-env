package com.example.pvbackend.repository;

import com.example.pvbackend.model.BeschwerungAnlage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeschwerungAnlageRepository extends JpaRepository<BeschwerungAnlage, Long> {
    BeschwerungAnlage findByAnlageId(Long anlageId);

}
