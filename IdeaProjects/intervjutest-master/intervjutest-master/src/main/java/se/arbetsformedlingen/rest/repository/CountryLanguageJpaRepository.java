package se.arbetsformedlingen.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.arbetsformedlingen.rest.model.CountryLanguage;

import java.util.List;

@Repository
public interface CountryLanguageJpaRepository extends JpaRepository<CountryLanguage, Integer> {


    @Query("SELECT cl.language FROM CountryLanguage cl, Country c WHERE c.name = :name AND cl.country = c.code AND cl.isOfficial = 'T'")
    List<String> findOfficialLangByCountryName(@Param("name")String name);


    @Query("SELECT c.name FROM CountryLanguage cl, Country c WHERE cl.isOfficial = :isOfficial AND cl.language = :language AND cl.country = c.code")
    List<String> getAllCountriesByLanguageAndIsOfficial(@Param("isOfficial") Character isOfficial, @Param("language") String language);

}
