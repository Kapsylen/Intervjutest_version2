package se.arbetsformedlingen.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.arbetsformedlingen.rest.execption.CustomErrorType;
import se.arbetsformedlingen.rest.model.City;
import se.arbetsformedlingen.rest.model.Country;
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



    @GetMapping(value = "/cities/{name}")
    @ResponseBody
    public ResponseEntity<List<City>> cities(@PathVariable(value = "name") String name){

        if (cityService.findAllByName(name).isEmpty()) {
            return new ResponseEntity(new CustomErrorType("Unable to find. A City with name " +
                    name + " doesn't exist."),HttpStatus.NOT_FOUND);
        }
        List<City> cities = cityService.findAllByName(name);
        return ResponseEntity.ok().body(cities);

    }


    @PostMapping(value = "/city")
    public ResponseEntity<City> add(@RequestBody City city) {


        if(cityService.isCityExist(city)){
            return new ResponseEntity(new CustomErrorType("Unable to create. A city with name " +
                    city.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        cityService.addCity(city);
        return ResponseEntity.ok().body(city);

    }

    @PutMapping(value = "/city")
    public ResponseEntity<City> update(@RequestBody City city) {
        if(cityService.isCityExist(city)){

            return ResponseEntity.ok().body(cityService.update(city));
        }
        return new ResponseEntity(new CustomErrorType("Unable to update. A city with country code " +
                city.getCountry().getCode() + " and name " +  city.getName() + " doesn't exist."), HttpStatus.NOT_FOUND);

    }

    @DeleteMapping(value = "/city/{name}/{code}")
    public ResponseEntity<Integer> delete(@PathVariable(name="name") String name, @PathVariable(name = "code") String code){

        if(cityService.deleteCity(name,code) > 0){
            Integer found = cityService.deleteCity(name, code);
            return ResponseEntity.ok(found);
        }
        return new ResponseEntity(new CustomErrorType("Unable to delete. A country with country country name " +
                name + "and country code" + code + " doesn't exist."), HttpStatus.NOT_FOUND);

    }



    @GetMapping(value = "/cities/code/{code}")
    @ResponseBody
    public ResponseEntity<List<City>> findAllByCountryCode(@PathVariable(value = "code") String code){

        if(cityService.findAllByCountryCode(code).isEmpty()){
            return new ResponseEntity(new CustomErrorType("Unable to find. No City with country code " +
                    code + " found."),HttpStatus.NOT_FOUND);
        }
        List<City> cities = cityService.findAllByCountryCode(code);
            return ResponseEntity.ok().body(cities);
    }


    @GetMapping(value = "/cities/region/{region}")
    @ResponseBody
    public ResponseEntity<List<City>> findAllByCountryRegion(@PathVariable(value = "region") String region){
        if(cityService.findAllByCountryRegion(region).isEmpty()){
            return new ResponseEntity(new CustomErrorType("Unable to find. No City in region " +
                    region + " found."),HttpStatus.NOT_FOUND);
        }
        List<City> cities = cityService.findAllByCountryRegion(region);
        return ResponseEntity.ok().body(cities);

    }



}
