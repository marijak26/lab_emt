package mk.finki.ukim.lab_emt.service.domain;
import mk.finki.ukim.lab_emt.model.domain.User;
import mk.finki.ukim.lab_emt.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    User findByUsername(String username);
}

