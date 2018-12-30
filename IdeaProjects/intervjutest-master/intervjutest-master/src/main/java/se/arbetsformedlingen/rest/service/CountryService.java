package se.arbetsformedlingen.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.arbetsformedlingen.rest.repository.CountryDAO;
import se.arbetsformedlingen.rest.model.Country;
import se.arbetsformedlingen.rest.repository.CountryJpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CountryService {

    @Autowired
    CountryDAO countryDAO;

    @Autowired
    CountryJpaRepository countryJpaRep;

    public List<String> listAllCountries() {
        return  countryJpaRep.listAllCountries();
    }

    public Country findCountry(String name){
        return countryJpaRep.findByName(name);
    }


    public Country addCountry(Country country) {
        return countryJpaRep.save(country);
    }

    @Transactional
    public Country update(Country country) {

        String code = country.getCode();
        Country updatedCountry = countryJpaRep.findByCode(code);

        updatedCountry.setName(country.getName());
        updatedCountry.setContinent(country.getContinent());
        updatedCountry.setSurfaceArea(country.getSurfaceArea());
        updatedCountry.setIndepYear(country.getIndepYear());
        updatedCountry.setPopulation(country.getPopulation());
        updatedCountry.setLifeExpectancy(country.getLifeExpectancy());
        updatedCountry.setGnp(country.getGnp());
        updatedCountry.setGnpOld(country.getGnpOld());
        updatedCountry.setLocalName(country.getLocalName());
        updatedCountry.setHeadOfState(country.getHeadOfState());
        updatedCountry.setCapital(country.getCapital());
        updatedCountry.setCode2(updatedCountry.getCode2());

        return countryJpaRep.save(updatedCountry);
    }

    @Transactional
    public Integer deleteCountry(String name) {
      return countryJpaRep.deleteCountryByCode(name);
    }

    public List<Country> findCountriesByPopulationIsGreaterThan(Integer population) {
        return countryJpaRep.findCountriesByPopulationIsGreaterThan(population);
    }

    public List<Country> findAllByGovernmentForm(String name){
        return countryJpaRep.findAllByGovernmentForm(name);
    }

    public List<String> capitals(){
        return countryJpaRep.findAllCapitals();
    }

    public List<Country> findAllCitiesInAContinentWithAPopulationEqualOrGreaterThanX(String continent, Integer population){
        return countryJpaRep.findAllCitiesInAContinentWithAPopulationEqualOrGreaterThanX(continent, population);
    }


    public boolean isCountryExist(Country country) {
        String code = country.getCode();
        return countryJpaRep.existsByCode(code);
    }
}
