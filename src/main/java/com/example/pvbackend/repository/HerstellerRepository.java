package com.example.pvbackend.repository;


import com.example.pvbackend.model.Hersteller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HerstellerRepository extends JpaRepository<Hersteller, Long> {
}
