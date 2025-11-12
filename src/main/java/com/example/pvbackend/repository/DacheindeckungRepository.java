package com.example.pvbackend.repository;

import com.example.pvbackend.model.DacheindeckungAnlage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DacheindeckungRepository extends JpaRepository<DacheindeckungAnlage, Long> {
     DacheindeckungAnlage findByAnlageId(Long anlageId);
}
