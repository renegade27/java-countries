package local.naught.countries;

import java.util.concurrent.atomic.AtomicLong;

public class Country {

    private static final AtomicLong counter = new AtomicLong();
    private long id;
    private String name;
    private int population;
    private int land_mass;
    private int median_age;

    public Country(String name, int population, int land_mass, int median_age) {
        this.id = counter.incrementAndGet();
        this.name = name;
        this.population = population;
        this.land_mass = land_mass;
        this.median_age = median_age;
    }

    public Country (Country toClone)
    {
        this.id = toClone.getId();
        this.name = toClone.getName();
        this.population = toClone.getPopulation();
        this.land_mass = toClone.getLand_mass();
        this.median_age = toClone.getMedian_age();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getLand_mass() {
        return land_mass;
    }

    public void setLand_mass(int land_mass) {
        this.land_mass = land_mass;
    }

    public int getMedian_age() {
        return median_age;
    }

    public void setMedian_age(int median_age) {
        this.median_age = median_age;
    }
}
