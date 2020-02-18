package com.eliise.cars.controller;

import com.eliise.cars.domain.Country;
import com.eliise.cars.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(
        path = "/api/countries"
)
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> fetchCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{countryUid}")
    public ResponseEntity<?> fetchCountry(@PathVariable("countryUid") UUID countryUid) {
        Optional<Country> countryOptional = countryService.getCountry(countryUid);
        if (countryOptional.isPresent()) {
            return ResponseEntity.ok(countryOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage("country " + countryUid + " not found"));
    }

    @PostMapping
    public ResponseEntity<UUID> insertNewCountry(@RequestBody Country country) {
        UUID countryUid = countryService.insertCountry(country);
        return ResponseEntity.ok().body(countryUid);
    }

    @DeleteMapping("/{countryUid}")
    public ResponseEntity<Integer> deleteCountry(@PathVariable("countryUid") UUID countryUid) {
        int result = countryService.removeCountry(countryUid);
        return getIntegerResponseEntity(result);
    }

    private ResponseEntity<Integer> getIntegerResponseEntity(int result) {
        if (result == 1) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
