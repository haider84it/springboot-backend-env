package com.example.pvbackend.repository;

import com.example.pvbackend.model.ServicePartner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicePartnerRepository extends JpaRepository<ServicePartner, Long> {
}
