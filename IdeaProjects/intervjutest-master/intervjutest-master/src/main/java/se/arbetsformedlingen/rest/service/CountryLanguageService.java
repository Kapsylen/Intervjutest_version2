package se.arbetsformedlingen.rest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.arbetsformedlingen.rest.repository.CountryLanguageJpaRepository;

import java.util.List;

@Service
public class CountryLanguageService {

    @Autowired
    CountryLanguageJpaRepository countryLanguageJpaRepo;



    public List<String> findOfficialLangByCountryName(String name){

        return countryLanguageJpaRepo.findOfficialLangByCountryName(name);

    }

}
