package local.naught.countries;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/population")
public class PopulationController {

    //localhost:8080/population/size/people
    @GetMapping( value = "/size/{people}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountriesByPopulation(
            @PathVariable
                int people)
    {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList.findCountriesBySize(c -> c.getPopulation() >= people);
        rtnCountries.sort((c1, c2) -> Integer.compare(c1.getPopulation(), c2.getPopulation()));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    //localhost:8080/population/min
    @GetMapping( value = "/min",
            produces = {"application/json"})
    public ResponseEntity<?> getSmallestCountry()
    {
        CountriesApplication.ourCountryList.countryList.sort((c1, c2) -> Integer.compare(c1.getPopulation(), c2.getPopulation()));
        return new ResponseEntity<>(CountriesApplication.ourCountryList.countryList.get(0), HttpStatus.OK);
    }

    //localhost:8080/population/max
    @GetMapping( value = "/max",
            produces = {"application/json"})
    public ResponseEntity<?> getLargestCountry()
    {
        CountriesApplication.ourCountryList.countryList.sort((c1, c2) -> Integer.compare(c2.getPopulation(), c1.getPopulation()));
        return new ResponseEntity<>(CountriesApplication.ourCountryList.countryList.get(0), HttpStatus.OK);
    }

    //localhost:8080/population/median
    @GetMapping( value = "/median",
            produces = {"application/json"})
    public ResponseEntity<?> getMedianPopCountry()
    {
        int median = CountriesApplication.ourCountryList.countryList.size() / 2;
        CountriesApplication.ourCountryList.countryList.sort((c1, c2) -> Integer.compare(c2.getPopulation(), c1.getPopulation()));
        return new ResponseEntity<>(CountriesApplication.ourCountryList.countryList.get(median), HttpStatus.OK);
    }

}
