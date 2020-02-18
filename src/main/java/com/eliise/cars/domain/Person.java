package com.eliise.cars.domain;

import java.util.Date;
import java.util.UUID;

public class Person {
    private UUID personUid;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String country;
    private Date dateOfBirth;



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UUID getPersonUid() {
        return personUid;
    }

    public void setPersonUid(UUID personUid) {
        this.personUid = personUid;
    }


    public enum Gender {
        MALE,
        FEMALE
    }
}
