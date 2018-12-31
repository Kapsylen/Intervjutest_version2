package se.arbetsformedlingen.rest.exception;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Unable to find city")
public class CityNotFoundException extends Exception {

    public CityNotFoundException(String message) {
        super(message);
    }



}
