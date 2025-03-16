package mk.finki.ukim.lab_emt.service;

import mk.finki.ukim.lab_emt.model.Stay;
import mk.finki.ukim.lab_emt.model.dto.StayDto;

import java.util.List;
import java.util.Optional;

public interface StayService {
    List<Stay> findAll();
    Optional<Stay> findById(Long stayId);
    Optional<Stay> save(StayDto stay);
    Optional<Stay> update(Long stayId, StayDto stay);
    void deleteById(Long stayId);
}
