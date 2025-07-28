package com.example.pvbackend.repository;

import com.example.pvbackend.model.PhotovoltaikAnlage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotovoltaikAnlageRepository extends JpaRepository<PhotovoltaikAnlage, Long> {
}
