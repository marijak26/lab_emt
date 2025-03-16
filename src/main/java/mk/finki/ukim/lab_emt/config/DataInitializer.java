package mk.finki.ukim.lab_emt.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.lab_emt.model.Category;
import mk.finki.ukim.lab_emt.model.Country;
import mk.finki.ukim.lab_emt.model.Host;
import mk.finki.ukim.lab_emt.model.Stay;
import mk.finki.ukim.lab_emt.repository.CountryRepository;
import mk.finki.ukim.lab_emt.repository.HostRepository;
import mk.finki.ukim.lab_emt.repository.StayRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final StayRepository stayRepository;

    public DataInitializer(CountryRepository countryRepository, HostRepository hostRepository, StayRepository stayRepository) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.stayRepository = stayRepository;
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

        stayRepository.save(new Stay("Marija", Category.ROOM, hostRepository.findAll().get(0), 1));
        stayRepository.save(new Stay("Petar", Category.APARTMENT, hostRepository.findAll().get(1), 1));
        stayRepository.save(new Stay("Julija", Category.FLAT, hostRepository.findAll().get(2), 2));
        stayRepository.save(new Stay("Ana", Category.HOTEL, hostRepository.findAll().get(3), 3));
        stayRepository.save(new Stay("Andrej", Category.MOTEL, hostRepository.findAll().get(4), 2));
        stayRepository.save(new Stay("Popo", Category.HOUSE, hostRepository.findAll().get(1), 1));
        stayRepository.save(new Stay("Magdalena", Category.HOTEL, hostRepository.findAll().get(0), 4));


    }
}
