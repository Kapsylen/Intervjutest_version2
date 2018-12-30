package se.arbetsformedlingen.rest.model;

import javax.persistence.*;

@Entity(name="City")
@Table(name="city")
public class City {

    @Id
    @Column(name="ID")
    Integer id;
    @Column(name="Name")
    String name;
    @Column(name="District")
    String district;
    @Column(name="Population")
    Integer population;
    @ManyToOne()
    @JoinColumn(name="CountryCode", referencedColumnName = "Code")
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getPopulation() { return population; }

    public void setPopulation(Integer population) { this.population = population; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
