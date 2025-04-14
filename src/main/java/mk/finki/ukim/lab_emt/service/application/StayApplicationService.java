package mk.finki.ukim.lab_emt.service.application;

import mk.finki.ukim.lab_emt.model.dto.CreateStayDto;
import mk.finki.ukim.lab_emt.model.dto.DisplayStayDto;


import java.util.List;
import java.util.Optional;

public interface StayApplicationService {
    List<DisplayStayDto> findAll();
    List<DisplayStayDto> findFree();
    Optional<DisplayStayDto> findById(Long stayId);
    Optional<DisplayStayDto> save(CreateStayDto createStayDto);
    Optional<DisplayStayDto> update(Long stayId, CreateStayDto createStayDto);
    void deleteById(Long stayId);
    DisplayStayDto markStayAsRented(Long stayId);
}
