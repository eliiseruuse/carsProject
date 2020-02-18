package com.eliise.cars.service;

import com.eliise.cars.dao.CountryDao;
import com.eliise.cars.domain.Country;
import org.springframework.stereotype.Service;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CountryService {

    private CountryDao countryDao;

    public CountryService(CountryDao countryDao) {
        this.countryDao = countryDao;
    }
    public List<Country> getAllCountries() {
        return countryDao.selectAllCountries();
    }

    public Optional<Country> getCountry(UUID countryUid) {
        Country country = countryDao.selectCountryByCountryUid(countryUid);
        return Optional.ofNullable(country);
    }
    public UUID insertCountry(Country country) {
        UUID countryUid = country.getCountryUid() == null ? UUID.randomUUID() : country.getCountryUid();
        return countryDao.insertCountry(country);
    }
    public int removeCountry(UUID uid) {
        UUID countryUid = getCountry(uid)
                .map(Country::getCountryUid)
                .orElseThrow(() -> new NotFoundException("country " + uid + " not found"));
        return countryDao.deleteCountryByCountryUid(countryUid);
    }
}
