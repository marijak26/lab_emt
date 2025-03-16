package mk.finki.ukim.lab_emt.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.lab_emt.model.Country;
import mk.finki.ukim.lab_emt.model.Host;
import mk.finki.ukim.lab_emt.repository.CountryRepository;
import mk.finki.ukim.lab_emt.repository.HostRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;

    public DataInitializer(CountryRepository countryRepository, HostRepository hostRepository) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
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
    }
}
