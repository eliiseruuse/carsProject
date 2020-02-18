package com.eliise.cars.dao;

import com.eliise.cars.domain.Car;
import com.eliise.cars.domain.Country;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

@Repository
public class CountryDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    public CountryDao(DataSource dataSource) {
        this.jdbcOperations = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Country> selectAllCountries() {
        BeanPropertyRowMapper<Country> rowMapper = new BeanPropertyRowMapper<>(Country.class);
        return jdbcOperations.query("select * from public.countries", rowMapper);
    }

    public Country selectCountryByCountryUid(UUID countryUid) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("countryUid", countryUid);

        BeanPropertyRowMapper<Country> rowMapper = new BeanPropertyRowMapper<>(Country.class);
        List<Country> countries = jdbcOperations.query("select * from public.countries where country_uid = :countryUid", parameterSource, rowMapper);

        if (countries.isEmpty()) {
            return null;
        } else {
            return countries.get(0);
        }
    }

    public int deleteCountryByCountryUid(UUID countryUid) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("countryUid", countryUid);
        jdbcOperations.update("delete from public.countries where country_uid = :countryUid", parameterSource);

        return 0;
    }

    public UUID insertCountry(Country country) {
        UUID uuid = UUID.randomUUID();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("countryUid", uuid);
        parameterSource.addValue("countryName", country.getCountryName());
        parameterSource.addValue("language", country.getLanguage());
        parameterSource.addValue("isoCode", country.getIsoCode());
        parameterSource.addValue("population", country.getPopulation());
        parameterSource.addValue("isEu", country.getIsEu());

        jdbcOperations.update("insert into public.countries(country_uid, country_name, language, iso_code, population, is_eu) " +
                "values (:countryUid, :countryName, :language, :isoCode, :population, :isEu)", parameterSource);

        return uuid;
    }
}
