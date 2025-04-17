package mk.finki.ukim.lab_emt.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.lab_emt.model.domain.*;
import mk.finki.ukim.lab_emt.model.enumerations.Category;
import mk.finki.ukim.lab_emt.model.enumerations.Role;
import mk.finki.ukim.lab_emt.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final AccommodationRepository accommodationRepository;
    private final GuestRepository guestRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CountryRepository countryRepository, HostRepository hostRepository, AccommodationRepository accommodationRepository, GuestRepository guestRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.accommodationRepository = accommodationRepository;
        this.guestRepository = guestRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        countryRepository.save(new Country("Italy", "Europe"));
        countryRepository.save(new Country("Spain", "Europe"));
        countryRepository.save(new Country("England", "Europe"));
        countryRepository.save(new Country("Macedonia", "Europe"));
        countryRepository.save(new Country("USA", "North America"));

        hostRepository.save(new Host("Lorenzo", "Zurzolo", countryRepository.findAll().get(0)));
        hostRepository.save(new Host("Penelope", "Cruz", countryRepository.findAll().get(1)));
        hostRepository.save(new Host("Cillian","Murphy", countryRepository.findAll().get(2)));
        hostRepository.save(new Host("Marija", "Kostadinovikj", countryRepository.findAll().get(3)));
        hostRepository.save(new Host("Julia", "Roberts", countryRepository.findAll().get(4)));

        guestRepository.save(new Guest("Ana", "Todorova", countryRepository.findAll().get(1)));
        guestRepository.save(new Guest("Marko", "Markovski", countryRepository.findAll().get(4)));
        guestRepository.save(new Guest("Petar", "Petrovski", countryRepository.findAll().get(3)));
        guestRepository.save(new Guest("Kristijan", "Kristijanoski", countryRepository.findAll().get(2)));
        guestRepository.save(new Guest("Stefan", "Stefanovski", countryRepository.findAll().get(0)));

        accommodationRepository.save(new Accommodation("Smestuvanje 1", Category.ROOM, hostRepository.findAll().get(0), 1));
        accommodationRepository.save(new Accommodation("Smestuvanje 2", Category.APARTMENT, hostRepository.findAll().get(1), 1));
        accommodationRepository.save(new Accommodation("Smestuvanje 3", Category.FLAT, hostRepository.findAll().get(2), 2));
        accommodationRepository.save(new Accommodation("Smestuvanje 4", Category.HOTEL, hostRepository.findAll().get(3), 3));
        accommodationRepository.save(new Accommodation("Smestuvanje 5", Category.MOTEL, hostRepository.findAll().get(4), 2));
        accommodationRepository.save(new Accommodation("Smestuvanje 6", Category.HOUSE, hostRepository.findAll().get(1), 1));
        accommodationRepository.save(new Accommodation("Smestuvanje 7", Category.HOTEL, hostRepository.findAll().get(0), 4));
        accommodationRepository.save(new Accommodation("Smestuvanje 8", Category.APARTMENT, hostRepository.findAll().get(2), 3));
        accommodationRepository.save(new Accommodation("Smestuvanje 9", Category.MOTEL, hostRepository.findAll().get(1), 1));
        accommodationRepository.save(new Accommodation("Smestuvanje 10", Category.APARTMENT, hostRepository.findAll().get(3), 4));

        userRepository.save(new User(
                "mk",
                passwordEncoder.encode("mk"),
                hostRepository.findAll().get(3).getName(),
                hostRepository.findAll().get(3).getSurname(),
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
