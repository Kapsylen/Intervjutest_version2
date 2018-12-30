package se.arbetsformedlingen.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.arbetsformedlingen.rest.execption.CustomErrorType;
import se.arbetsformedlingen.rest.model.City;
import se.arbetsformedlingen.rest.service.CountryLanguageService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping()
public class CountryLanguageController {

    @Autowired
    CountryLanguageService countryLangService;


    @GetMapping(value = "/languages/{name}")
    public ResponseEntity<List<String>> findOfficialLangByCountryName(@PathVariable(name = "name") String name){

        if (countryLangService.findOfficialLangByCountryName(name).isEmpty()) {
            return new ResponseEntity(new CustomErrorType("Unable to find. Country name " +
                    name + " doesn't exist."), HttpStatus.NOT_FOUND);
        }
        List<String> languages = countryLangService.findOfficialLangByCountryName(name);
        return new ResponseEntity(languages,HttpStatus.FOUND);
    }


    @GetMapping(
    value = "/languages/countries",
    params = { "isOfficial","language" })
    public List<String> findAllCountriesByOfficialLanguage(@RequestParam(value = "isOfficial") Character isOfficial,
                                   @RequestParam(value = "language") String language) {

        List<String> countries = countryLangService.getAllCountriesByLanguageAndIsOfficial(isOfficial, language);

        return countries;
    }



}
