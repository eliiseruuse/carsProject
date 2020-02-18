package com.eliise.cars.controller;

import com.eliise.cars.domain.Person;
import com.eliise.cars.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(
        path = "/api/persons"
)
public class PersonsController {
    private final PersonService personService;

    public PersonsController(PersonService personService) {this.personService = personService; }
@GetMapping
    private List<Person> fetchPersons() {return personService.getAllPersons(); }

    @GetMapping("/{countryUid}")
    public ResponseEntity<?> fetchPerson(@PathVariable("personUid")UUID personUid) {
        Optional<Person> personOptional = personService.getPerson(personUid);
        if (personOptional.isPresent()) {
            return ResponseEntity.ok(personOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage("person " + personUid + " not found"));
    }

    @PostMapping
    public ResponseEntity<UUID> insertNewPerson(@RequestBody Person person) {
        UUID personUid = personService.insertPerson(person);
        return ResponseEntity.ok().body(personUid);
    }
    @DeleteMapping("/{personUid}")
    public ResponseEntity<Integer> deletePerson(@PathVariable("personUid") UUID personUid){
        int result = personService.removePerson(personUid);
        return getIntegerResponseEntity(result);
    }
    private ResponseEntity<Integer> getIntegerResponseEntity(int result) {
        if (result == 1) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }



}
