package com.server.service;

import com.server.entity.City;

import java.util.List;

public interface CityService {
    List<City> getAllCities();

    void saveCities(City city);

    City getCity(int id);

    City getCityByName(String name);

    void deleteCity(int id);

}
