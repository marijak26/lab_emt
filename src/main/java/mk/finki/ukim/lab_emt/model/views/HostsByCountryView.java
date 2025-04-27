package mk.finki.ukim.lab_emt.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * FROM public.hosts_by_country")
public class HostsByCountryView {
    @Id
    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "hosts_count")
    private Integer hostsCount;
}
