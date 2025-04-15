package mk.finki.ukim.lab_emt.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.finki.ukim.lab_emt.model.enumerations.ReservationStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany
    private List<Stay> stays;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public Reservation(User user){
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.stays = new ArrayList<>();
        this.status = ReservationStatus.CREATED;
    }

}
