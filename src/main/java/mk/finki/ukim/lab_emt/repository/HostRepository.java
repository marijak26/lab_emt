package mk.finki.ukim.lab_emt.repository;

import mk.finki.ukim.lab_emt.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
}
