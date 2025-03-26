package mk.finki.ukim.lab_emt.model.dto;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.finki.ukim.lab_emt.model.Category;

@Data
@NoArgsConstructor
@Setter
@Getter
public class StayDto {
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Long host;
    private Integer numRooms;
    private boolean isRented;

    public StayDto(String name, Category category, Long host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
    }
}
