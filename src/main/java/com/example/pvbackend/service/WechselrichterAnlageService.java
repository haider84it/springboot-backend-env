package com.example.pvbackend.service;

import com.example.pvbackend.model.WechselrichterAnlage;
import com.example.pvbackend.repository.WechselrichterAnlageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class WechselrichterAnlageService {


      private final WechselrichterAnlageRepository wechselrichterAnlageRepository;

      public WechselrichterAnlageService(WechselrichterAnlageRepository wechselrichterAnlageRepository) {
          this.wechselrichterAnlageRepository = wechselrichterAnlageRepository;
      }

      public List<WechselrichterAnlage> getAllWechselricher() {
          return wechselrichterAnlageRepository.findAll();
      }

      public Optional<WechselrichterAnlage> getWechselrichterById(Long id) {
          return wechselrichterAnlageRepository.findById(id);
      }

      public List<WechselrichterAnlage> getWechselrichterByAnlage(Long anlageId) {
          return wechselrichterAnlageRepository.findByAnlageId(anlageId);
      }

      public WechselrichterAnlage saveWechselrichter(WechselrichterAnlage wechselrichterAnlage)  {
          return wechselrichterAnlageRepository.save(wechselrichterAnlage);
      }

      public void deleteWechselrichter(Long id) {
          wechselrichterAnlageRepository.deleteById(id);
      }

}
