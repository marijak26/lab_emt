package mk.finki.ukim.lab_emt.config.init;

import mk.finki.ukim.lab_emt.model.domain.User;
import mk.finki.ukim.lab_emt.model.enumerations.Role;
import mk.finki.ukim.lab_emt.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //    @PostConstruct
    public void init() {
        userRepository.save(new User(
                "mk",
                passwordEncoder.encode("mk"),
                "Marija",
                "Kostadinovikj",
                Role.ROLE_HOST
        ));

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "user",
                "user",
                Role.ROLE_USER
        ));
    }
}

