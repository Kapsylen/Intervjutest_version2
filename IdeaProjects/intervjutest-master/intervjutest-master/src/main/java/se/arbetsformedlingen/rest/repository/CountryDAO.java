package se.arbetsformedlingen.rest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.arbetsformedlingen.rest.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CountryDAO {

    @Autowired
    private EntityManager em;


    public Country findCountry(String name){
        TypedQuery<Country> query = em.createQuery(
                "SELECT c FROM Country c WHERE c.name = :name", Country.class);
        return query.setParameter("name", name).getSingleResult();
    }



}
