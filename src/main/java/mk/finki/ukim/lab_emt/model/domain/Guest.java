package mk.finki.ukim.lab_emt.model.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guestId;

    private String name;
    private String surname;

    @ManyToOne
    private Country country;

    @ManyToMany(mappedBy = "guests")
    @ToString.Exclude
    private List<Host> hosts;


    public Guest(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.hosts = new ArrayList<>();
    }
}
