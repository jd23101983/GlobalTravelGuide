package com.deshaies.globaltravelguide.model;

import java.util.ArrayList;
import java.util.List;

public class CountryByContinent {

    private String continent;
    private List<String> countryList;

    public CountryByContinent(String continent) {
        this.continent = continent;
        this.countryList = new ArrayList<>();
    }

    public void addCountry (String targetContinent, String newCountry) {

    }
}
