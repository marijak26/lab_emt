package mk.finki.ukim.lab_emt.service.application;

import mk.finki.ukim.lab_emt.dto.CreateUserDto;
import mk.finki.ukim.lab_emt.dto.DisplayUserDto;
import mk.finki.ukim.lab_emt.dto.LoginResponseDto;
import mk.finki.ukim.lab_emt.dto.LoginUserDto;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

    List<DisplayUserDto> findAll();
}

