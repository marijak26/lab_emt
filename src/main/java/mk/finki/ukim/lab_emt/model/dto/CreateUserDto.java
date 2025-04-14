package mk.finki.ukim.lab_emt.model.dto;

import mk.finki.ukim.lab_emt.model.domain.User;
import mk.finki.ukim.lab_emt.model.enumerations.Role;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {

    /*
        todo: add repeat password logic

     */
    public boolean passwordsMatch() {
        return password != null && password.equals(repeatPassword);
    }

    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}

