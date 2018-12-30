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

        List<String> languages = countryLangService.findOfficialLangByCountryName(name);

        if (languages.isEmpty()) {
            return new ResponseEntity(new CustomErrorType("Unable to find. Country name " +
                    name + " doesn't exist."), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(languages);
    }


    @GetMapping(
            value = "/languages/countries",
            params = { "isOfficial","language" })
    public ResponseEntity<List<String>> findAllCountriesByOfficialLanguage(@RequestParam(value = "isOfficial") Character isOfficial,
                                                                           @RequestParam(value = "language") String language) {
        List<String> countries = countryLangService.getAllCountriesByLanguageAndIsOfficial(isOfficial, language);

        if(countries.isEmpty()){
            return new ResponseEntity(new CustomErrorType("Unable to find. No country found with " +
                    language + " as official language."),HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().body(countries);
    }



}
