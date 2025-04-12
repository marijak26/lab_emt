package mk.finki.ukim.lab_emt.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hostId;
    private String name;
    private String surname;

    @ManyToOne
    private Country country;

    @ManyToMany
    @JoinTable(
            name = "hosts_guests",
            joinColumns = @JoinColumn(name = "hostId"),
            inverseJoinColumns = @JoinColumn(name = "guestId"))
    @ToString.Exclude
    private List<Guest> guests;

    public Host(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.guests = new ArrayList<>();
    }

}
