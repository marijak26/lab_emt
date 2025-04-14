package mk.finki.ukim.lab_emt.model.dto;

import mk.finki.ukim.lab_emt.model.enumerations.Role;

public record RegisterUserDto(String username,
                              String password,
                              String repeatPassword,
                              String name,
                              String surname,
                              Role role) {
}
