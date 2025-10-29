package com.example.pvbackend.repository;


import com.example.pvbackend.model.KundenAnlageZuordnung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KundenAnlageZuordnungRepository extends JpaRepository<KundenAnlageZuordnung, Long> {

    List<KundenAnlageZuordnung> findByAnlageId(Long anlageId);
}
