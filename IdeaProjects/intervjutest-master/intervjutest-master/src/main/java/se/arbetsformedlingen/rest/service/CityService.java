package se.arbetsformedlingen.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.arbetsformedlingen.rest.model.City;
import se.arbetsformedlingen.rest.model.Country;
import se.arbetsformedlingen.rest.repository.CityDAO;
import se.arbetsformedlingen.rest.repository.CityJpaRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Service
public class CityService {

    @Autowired
    CityDAO cityDAO;

    @Autowired
    CityJpaRepository cityJpaRep;

    public City findCity(String name){
        City city = cityDAO.findCity(name);

        //Simple service logic
        city.setName(city.getName().toUpperCase());

        return city;

    }


    public  List<City> findAllByCountryCode(String code){

        List<City> cities = cityJpaRep.findAllByCountryCode(code);


        cities.sort(Comparator.comparing(City::getName, Comparator.nullsFirst(Comparator.naturalOrder())));



        return cities;
    }


    public List<City> findAllByCountryRegion(String region){

        return cityJpaRep.findAllByCountry_Region(region);
    }


    public City addCity(City city) {
        return cityJpaRep.save(city);
    }


    public List<City> findAllByName(String name) {
       return cityJpaRep.findCitiesByName(name);
    }


    @Transactional
    public City update(City city) {
        String code = city.getCountry().getCode();
        String name = city.getName();

        City updateCity = cityJpaRep.findCityByNameAndCountry_Code(name, code);

        updateCity.setName(name);
        updateCity.setCountry(city.getCountry());
        updateCity.setDistrict(city.getDistrict());
        updateCity.setId(city.getId());
        updateCity.setPopulation(city.getPopulation());

        return updateCity;

    }

    @Transactional
    public Integer deleteCity(Integer id) {
        return cityJpaRep.deleteCityById(id);
    }


    public boolean existCityById(Integer id) {
       return cityJpaRep.existsCityById(id);
    }


}
