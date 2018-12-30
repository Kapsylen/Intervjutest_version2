package se.arbetsformedlingen.rest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.arbetsformedlingen.rest.repository.CountryLanguageJpaRepository;

import java.util.List;

@Service
public class CountryLanguageService {

    @Autowired
    CountryLanguageJpaRepository countryLangJpaRepo;


    public List<String> findOfficialLangByCountryName(String name){

        return countryLangJpaRepo.findOfficialLangByCountryName(name);

    }


    public List<String> getAllCountriesByLanguageAndIsOfficial(Character isOfficial, String language) {
       return countryLangJpaRepo.getAllCountriesByLanguageAndIsOfficial(isOfficial, language);
    }
}
