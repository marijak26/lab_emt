package mk.finki.ukim.lab_emt.service.domain;

import mk.finki.ukim.lab_emt.model.domain.Stay;
import java.util.List;
import java.util.Optional;

public interface StayService {
    List<Stay> findAll();
    List<Stay> findFree();
    Optional<Stay> findById(Long stayId);
    Optional<Stay> save(Stay stay);
    Optional<Stay> update(Long stayId, Stay stay);
    void deleteById(Long stayId);
    Stay markStayAsRented(Long stayId);
}
