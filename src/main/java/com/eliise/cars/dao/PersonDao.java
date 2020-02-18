package com.eliise.cars.dao;

import com.eliise.cars.domain.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

@Repository
public class PersonDao {
   private final NamedParameterJdbcOperations jdbcOperations;

    public PersonDao(DataSource dataSource) {
        this.jdbcOperations = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Person> selectAllPersons() {
        BeanPropertyRowMapper<Person> rowMapper = new BeanPropertyRowMapper<>(Person.class);
        return jdbcOperations.query("select * from public.persons", rowMapper);
    }
    public Person selectPersonByPersonUid(UUID personUid) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("personUid", personUid);

        BeanPropertyRowMapper<Person> rowMapper = new BeanPropertyRowMapper<>(Person.class);
        List<Person> persons = jdbcOperations.query("select * from public.persons where person_uid = :personUid", parameterSource, rowMapper);

        if (persons.isEmpty()) {
            return null;
        } else {
            return persons.get(0);
        }
    }
    public int deletePersonByPersonId(UUID personUid) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("personUid", personUid);
        jdbcOperations.update("delete from public.persons where person_uid = :personUid", parameterSource);
        return 0;
    }
    public UUID insertPerson(Person person) {
        UUID uuid = UUID.randomUUID();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("personUid", uuid);
        parameterSource.addValue("firstName", person.getFirstName());
        parameterSource.addValue("lastName", person.getLastName());
        parameterSource.addValue("gender", person.getGender());
        parameterSource.addValue("country", person.getCountry());
        parameterSource.addValue("dateOfBirth", person.getDateOfBirth());

        jdbcOperations.update("insert into public.persons (person_uid, first_name, last_name, gender, country, date_of_birth)" +
                "values (:personUid, :firstName, :lastName, :gender, :country, :dateOfBirth)", parameterSource);

        return uuid;

    }

}
