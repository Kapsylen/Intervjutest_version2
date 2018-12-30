package se.arbetsformedlingen.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.arbetsformedlingen.rest.model.City;

import java.util.List;

@Repository
public interface CityJpaRepository extends JpaRepository<City, Integer> {


    boolean existsCityByIdAndCountry_Code(Integer id, String code);

    Integer deleteCityByNameAndCountry_Code(String name, String code);

    City findCityByNameAndCountry_Code(String name, String code);

    List<City> findAllByName(String name);

    List<City> findAllByCountryCode(String code);

    List<City> findAllByCountry_Region(String region);



}
