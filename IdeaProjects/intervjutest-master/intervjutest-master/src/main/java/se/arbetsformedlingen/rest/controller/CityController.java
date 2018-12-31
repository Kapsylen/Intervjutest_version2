package se.arbetsformedlingen.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.arbetsformedlingen.rest.exception.CityConflictException;
import se.arbetsformedlingen.rest.exception.CityNotFoundException;
import se.arbetsformedlingen.rest.model.City;
import se.arbetsformedlingen.rest.service.CityService;

import java.util.List;


@RestController
@RequestMapping
public class CityController {

    @Autowired
    CityService cityService;


    @GetMapping(value = "/find/{name}", produces = { "application/json", "application/xml" })
    @ResponseBody
    public ResponseEntity city(@PathVariable(value = "name", required = true) String name){
        return ResponseEntity.ok().body(cityService.findCity(name));
    }



    //TODO: Controllers persists data via CityJpaRepository


    @GetMapping(value = "/city/{name}")
    @ResponseBody
    public ResponseEntity<List<City>> cities(@PathVariable(value = "name") String name) throws CityNotFoundException {

    List<City> cities = cityService.findAllByName(name);
        if (cities.isEmpty()) {

            throw new CityNotFoundException("Unable to find. A City with name " +
                    name + " doesn't exist.");
        }
        return ResponseEntity.ok().body(cities);

      }

    @PostMapping(value = "/cities")
    public ResponseEntity<City> add(@RequestBody City city) throws CityConflictException {

        Integer id = city.getId();
        if(cityService.existCityById(id)){
            throw new CityConflictException("City with id "+ id  +" already exist.");
        }

        cityService.addCity(city);
        return ResponseEntity.ok().body(city);

    }

    @PutMapping(value = "/cities")
    public ResponseEntity<City> update(@RequestBody City city) throws CityNotFoundException {
        Integer id = city.getId();
        if(cityService.existCityById(id)){

            return ResponseEntity.ok().body(cityService.update(city));
        }
        throw new CityNotFoundException("Unable to update. A city with country code " +
                city.getCountry().getCode() + " and name " +  city.getName() + " doesn't exist.");

    }

    @DeleteMapping(value = "/city/{id}")
    public ResponseEntity<Integer> delete(@PathVariable(name="id") Integer id) throws CityNotFoundException {

        Integer found = cityService.deleteCity(id);

        if(found > 0){

            return ResponseEntity.ok().body(found);
        }
        throw new CityNotFoundException("Unable to delete. A city with id " +
                id + " doesn't exist.");

    }



    @GetMapping(value = "/cities/code/{code}")
    @ResponseBody
    public ResponseEntity<List<City>> findAllByCountryCode(@PathVariable(value = "code") String code) throws CityNotFoundException {

        if(cityService.findAllByCountryCode(code).isEmpty()){
          throw new CityNotFoundException("Unable to find. No City with country code " +
                  code + " found.");
        }
        List<City> cities = cityService.findAllByCountryCode(code);
            return ResponseEntity.ok().body(cities);
    }


    @GetMapping(value = "/cities/region/{region}")
    @ResponseBody
    public ResponseEntity<List<City>> findAllByCountryRegion(@PathVariable(value = "region") String region) throws CityNotFoundException {
        if(cityService.findAllByCountryRegion(region).isEmpty()){
           throw new CityNotFoundException("Unable to find. No City in region " +
                   region + " found.");
        }
        List<City> cities = cityService.findAllByCountryRegion(region);
        return ResponseEntity.ok().body(cities);

    }




}
