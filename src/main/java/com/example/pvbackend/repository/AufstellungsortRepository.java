package com.example.pvbackend.repository;

import com.example.pvbackend.model.AufstellungsortAnlage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AufstellungsortRepository  extends JpaRepository<AufstellungsortAnlage, Long> {


      AufstellungsortAnlage findByAnlageId(Long anlageId);


}
