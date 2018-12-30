package se.arbetsformedlingen.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.arbetsformedlingen.rest.execption.CustomErrorType;
import se.arbetsformedlingen.rest.model.Country;
import se.arbetsformedlingen.rest.service.CountryService;

import java.util.List;



@RestController
@RequestMapping
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping(value = "/{name}")
    @ResponseBody
    public ResponseEntity find(@PathVariable(value = "name", required = true) String name){
        return ResponseEntity.ok().body(countryService.findCountry(name));
    }

    //TODO: Controllers persists data via CountryJpaRepository



    @GetMapping(value = "/countries")
    @ResponseBody
    public ResponseEntity<List<String>> countries(){
        if(countryService.listAllCountries().isEmpty()){

            return new ResponseEntity(new CustomErrorType("No countries found"),HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(countryService.listAllCountries());

         }

    @GetMapping(value = "/country/{name}")
    @ResponseBody
    public ResponseEntity<Country> country(@PathVariable(value = "name", required = true) String name){
        if(countryService.findCountry(name) == null){

            return new ResponseEntity(new CustomErrorType("Unable to find. A country with name " +
                    name + " doesn't exist."),HttpStatus.NOT_FOUND);
        }
        Country country = countryService.findCountry(name);
        return ResponseEntity.ok().body(country);
    }

    @PostMapping(value = "/countries")
    public ResponseEntity<Country> add(@RequestBody Country country) {

        if(countryService.isCountryExist(country)){
            return new ResponseEntity(new CustomErrorType("Unable to create. A country with country code " +
                    country.getCode() + " already exist."), HttpStatus.CONFLICT);
        }
        countryService.addCountry(country);
        return ResponseEntity.ok().body(country);

    }


    @PutMapping(value = "/countries")
    public ResponseEntity<Country> update(@RequestBody Country country) {
        if(countryService.isCountryExist(country)){

            return ResponseEntity.ok().body(countryService.update(country));
        }
            return new ResponseEntity(new CustomErrorType("Unable to update. A country with country code " +
                    country.getCode() + " doesn't exist."), HttpStatus.NOT_FOUND);

    }

    @DeleteMapping(value = "/country/{code}")
    public ResponseEntity<Integer> delete(@PathVariable(name = "code") String code){

        if(countryService.deleteCountry(code) > 0){
            Integer found = countryService.deleteCountry(code);
            return ResponseEntity.ok(found);
        }
        return new ResponseEntity(new CustomErrorType("Unable to delete. A country with country country code " +
                code + " doesn't exist."), HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "/counties/population/greaterthan/{population}")
    @ResponseBody
    public ResponseEntity<List<Country>> findCountriesByPopulationIsGreaterThanXPopulation (@PathVariable(value = "population") Integer population){
        return new ResponseEntity(countryService.findCountriesByPopulationIsGreaterThanXPopulation(population), HttpStatus.FOUND);
    }

    @GetMapping(value = "/counties/governmentform/{govform}")
    public  ResponseEntity<List<Country>> findAllByGovernmentForm(@PathVariable(value = "govform") String govform){
        return new ResponseEntity(countryService.findAllByGovernmentForm(govform), HttpStatus.FOUND);
    }

    @GetMapping(value = "/counties/capitals")
    public  ResponseEntity<List<String>> findAllCapitals(){

        return new ResponseEntity(countryService.capitals(), HttpStatus.FOUND);
    }

    @GetMapping(value = "/counties/cities/{continent}/{population}")
    public  ResponseEntity<List<Country>> findAllCitiesInAContinentWithAPopulationEqualOrGreaterThanXPopulation(@PathVariable(value = "continent") String continent,
                                                                                     @PathVariable(value = "population") Integer population){
        return new ResponseEntity(countryService.findAllCitiesInAContinentWithAPopulationEqualOrGreaterThanX(continent, population),
                HttpStatus.FOUND);
    }


}
