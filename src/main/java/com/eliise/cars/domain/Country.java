package com.eliise.cars.domain;

import java.util.UUID;

public class Country {

    private UUID countryUid;
    private String countryName;
    private String language;
    private String isoCode;
    private Integer population;
    private Boolean isEu;

    public UUID getCountryUid() {
        return countryUid;
    }

    public void setCountryUid(UUID countryUid) {
        this.countryUid = countryUid;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Boolean getIsEu() {
        return isEu;
    }

    public void setIsEu(Boolean eu) {
        isEu = eu;
    }

}
