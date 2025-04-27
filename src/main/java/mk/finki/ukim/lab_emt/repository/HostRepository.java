package mk.finki.ukim.lab_emt.repository;

import mk.finki.ukim.lab_emt.model.domain.Host;
import mk.finki.ukim.lab_emt.model.projections.HostProjection;
import mk.finki.ukim.lab_emt.model.projections.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    @Query("select h.name as name, h.surname as surname from Host h")
    List<HostProjection> takeNameAndSurnameByProjection();
}
