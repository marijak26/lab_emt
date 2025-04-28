package mk.finki.ukim.lab_emt.service.application.impl;

import mk.finki.ukim.lab_emt.dto.LoginResponseDto;
import mk.finki.ukim.lab_emt.helpers.JwtHelper;
import mk.finki.ukim.lab_emt.model.domain.User;
import mk.finki.ukim.lab_emt.dto.CreateUserDto;
import mk.finki.ukim.lab_emt.dto.DisplayUserDto;
import mk.finki.ukim.lab_emt.dto.LoginUserDto;
import mk.finki.ukim.lab_emt.service.application.UserApplicationService;
import mk.finki.ukim.lab_emt.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        );

        String token = jwtHelper.generateToken(user);

        return Optional.of(new LoginResponseDto(token));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

    @Override
    public List<DisplayUserDto> findAll() {
        return DisplayUserDto.from(userService.findAll());
    }
}



