package com.eliise.cars.service;

import com.eliise.cars.dao.PersonDao;
import com.eliise.cars.domain.Person;
import org.springframework.stereotype.Service;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private PersonDao personDao;

    public PersonService(PersonDao personDao)  {
        this.personDao = personDao;
    }

    public List<Person> getAllPersons() {
        return personDao.selectAllPersons();
    }

    public Optional<Person> getPerson(UUID personUid) {
        Person person = personDao.selectPersonByPersonUid(personUid);
        return Optional.ofNullable(person);
    }
    public UUID insertPerson(Person person) {
        UUID personUid  = person.getPersonUid() == null ? UUID.randomUUID()  : person.getPersonUid();
        return personDao.insertPerson(person);
    }
    public int removePerson(UUID uid) {
        UUID personUid = getPerson(uid)
                .map(Person::getPersonUid)
                .orElseThrow(() -> new NotFoundException("person " + uid + " not found"));
        return personDao.deletePersonByPersonId(personUid);
    }
}
