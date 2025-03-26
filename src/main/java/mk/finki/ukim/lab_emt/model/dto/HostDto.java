package mk.finki.ukim.lab_emt.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter
public class HostDto {
    private String name;
    private String surname;
    private String country;

    public HostDto(String name, String surname, String country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
