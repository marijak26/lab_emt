package mk.finki.ukim.lab_emt.service.application;

import mk.finki.ukim.lab_emt.model.dto.CreateUserDto;
import mk.finki.ukim.lab_emt.model.dto.DisplayUserDto;
import mk.finki.ukim.lab_emt.model.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
}

