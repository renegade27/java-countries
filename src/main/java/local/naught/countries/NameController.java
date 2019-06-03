package local.naught.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/names")
public class NameController {
    //localhost:8080/names/all
    @GetMapping(value = "/all",
            produces = {"application/json"})
    public ResponseEntity<?> getAllCountryNames() {
        ArrayList<String> rtnCountries = CountriesApplication.ourCountryList.getNames();
        rtnCountries.sort((c1, c2) -> c1.compareToIgnoreCase(c2));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    //localhost:8080/names/start/letter
    @GetMapping(value = "/start/{letter}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountriesByLetter(
            @PathVariable
                    char letter)
    {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList.findCountriesByLetter(c -> c.getName().toUpperCase().charAt(0) == Character.toUpperCase(letter));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    //localhost:8080/names/size/number
    @GetMapping(value = "/size/{number}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountriesBySize(
            @PathVariable
                    int number)
    {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList.findCountriesByLength(c1 -> c1.getName().length() == number);
        rtnCountries.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }
}

