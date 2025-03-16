package mk.finki.ukim.lab_emt.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HostDto {
    private String name;
    private String surname;
    private Long country;

    public HostDto(String name, String surname, Long country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
