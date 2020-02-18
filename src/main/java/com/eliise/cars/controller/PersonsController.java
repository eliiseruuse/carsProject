package com.eliise.cars.controller;


import com.eliise.cars.domain.Person;
import com.eliise.cars.service.PersonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
        path = "/api/persons"
)
public class PersonsController {
    private final PersonService personService;

    public PersonsController(PersonService personService) {this.personService = personService; }

    private List<Person> fetchPersons() {return PersonService.getAllPersons}

}
