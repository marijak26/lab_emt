package mk.finki.ukim.lab_emt.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.finki.ukim.lab_emt.model.enumerations.Category;

@Data
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Stay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Host host;

    private Integer numRooms;
    private boolean isRented;

    public Stay(String name, Category category, Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
    }

}
