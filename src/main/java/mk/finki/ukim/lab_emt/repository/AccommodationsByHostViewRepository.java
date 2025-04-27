package mk.finki.ukim.lab_emt.repository;

import mk.finki.ukim.lab_emt.model.views.AccommodationsByHostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccommodationsByHostViewRepository extends JpaRepository<AccommodationsByHostView, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.accommodations_by_host", nativeQuery = true)
    void refreshMaterializedView();
}
