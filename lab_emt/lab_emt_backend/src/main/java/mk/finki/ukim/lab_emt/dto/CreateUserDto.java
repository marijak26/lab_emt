package mk.finki.ukim.lab_emt.dto;

import mk.finki.ukim.lab_emt.model.domain.Host;
import mk.finki.ukim.lab_emt.model.domain.User;
import mk.finki.ukim.lab_emt.model.enumerations.Role;

import java.util.List;
import java.util.stream.Collectors;

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
    public static CreateUserDto from(User user) {
        return new CreateUserDto(
                user.getUsername(),
                null,
                null,
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }
    public static List<CreateUserDto> from(List<User> users) {
        return users.stream().map(CreateUserDto::from).collect(Collectors.toList());
    }
}

