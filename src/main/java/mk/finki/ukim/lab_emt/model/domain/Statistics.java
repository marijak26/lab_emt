package mk.finki.ukim.lab_emt.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statisticsId;

    private Category category;
    private Integer count;

    public Statistics(Category category, Integer count) {
        this.category = category;
        this.count = count;
    }
}
