package com.example.pvbackend.repository;

import com.example.pvbackend.model.ZaehlerAnlage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZaehlerAnlageRepository extends JpaRepository<ZaehlerAnlage, Long> {

    ZaehlerAnlage findByAnlageId(Long anlageId);

}
