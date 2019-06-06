package local.naught.countries;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/age")
public class AgeController {

    //localhost:8080/age/age/{age}
    @GetMapping(value = "/age/{age}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountriesByAge(
            @PathVariable
                    int age) {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList.findCountriesBySize(c -> c.getMedian_age() >= age);
        rtnCountries.sort((c1, c2) -> Integer.compare(c1.getMedian_age(), c2.getMedian_age()));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    //localhost:8080/age/min
    @GetMapping(value = "/min",
            produces = {"application/json"})
    public ResponseEntity<?> getYoungestAgeCountry() {
        CountriesApplication.ourCountryList.countryList.sort((c1, c2) -> Integer.compare(c1.getMedian_age(), c2.getMedian_age()));
        return new ResponseEntity<>(CountriesApplication.ourCountryList.countryList.get(0), HttpStatus.OK);
    }

    //localhost:8080/age/min
    @GetMapping(value = "/max",
            produces = {"application/json"})
    public ResponseEntity<?> getOldestAgeCountry() {
        CountriesApplication.ourCountryList.countryList.sort((c1, c2) -> Integer.compare(c2.getMedian_age(), c1.getMedian_age()));
        return new ResponseEntity<>(CountriesApplication.ourCountryList.countryList.get(0), HttpStatus.OK);
    }

    //localhost:8080/age/median
    @GetMapping( value = "/median",
            produces = {"application/json"})
    public ResponseEntity<?> getMedianAgeCountry()
    {
        int median = CountriesApplication.ourCountryList.countryList.size() / 2;
        CountriesApplication.ourCountryList.countryList.sort((c1, c2) -> Integer.compare(c2.getMedian_age(), c1.getMedian_age()));
        return new ResponseEntity<>(CountriesApplication.ourCountryList.countryList.get(median), HttpStatus.OK);
    }
}