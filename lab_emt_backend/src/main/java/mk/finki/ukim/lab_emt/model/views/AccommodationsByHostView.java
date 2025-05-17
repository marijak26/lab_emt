package mk.finki.ukim.lab_emt.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * FROM public.accommodations_by_host")
@Immutable
public class AccommodationsByHostView {
    @Id
    @Column(name="host_id")
    private Long hostId;

    @Column(name="accommodations_count")
    private Integer accommodationsCount;
}
