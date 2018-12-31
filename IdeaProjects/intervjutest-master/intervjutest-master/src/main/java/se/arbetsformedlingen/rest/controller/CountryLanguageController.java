package se.arbetsformedlingen.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.arbetsformedlingen.rest.exception.CountryLanguageNotFoundException;
import se.arbetsformedlingen.rest.service.CountryLanguageService;

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
           throw new CountryLanguageNotFoundException("Unable to find. Country name " +
                   name + " doesn't exist.");
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
            throw new CountryLanguageNotFoundException("Unable to find. No country found with " +
                    language + " as official language.");
        }

        return ResponseEntity.ok().body(countries);
    }



}
