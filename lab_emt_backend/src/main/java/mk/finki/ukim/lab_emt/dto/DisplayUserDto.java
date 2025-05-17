package mk.finki.ukim.lab_emt.dto;

import mk.finki.ukim.lab_emt.model.domain.Reservation;
import mk.finki.ukim.lab_emt.model.domain.User;
import mk.finki.ukim.lab_emt.model.enumerations.Role;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayUserDto(String username, String name, String surname, Role role) {

    public static DisplayUserDto from(User user) {
        return new DisplayUserDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }
    public static List<DisplayUserDto> from(List<User> users) {
        return users.stream().map(DisplayUserDto::from).collect(Collectors.toList());
    }

    public User toUser() {
        return new User(username, name, surname, role.name());
    }
}

