package mk.finki.ukim.lab_emt.model.domain;

import jakarta.persistence.*;
import lombok.*;
//import mk.finki.ukim.lab_emt.model.enumerations.enumerations.Role;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "hosts")
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hostId;
    private String name;
    private String surname;

    @ManyToOne
    private Country country;

//    @Enumerated(value = EnumType.STRING)
//    private Role role;

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
//        this.role = Role.ROLE_HOST;
    }

}
