package se.arbetsformedlingen.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Unable to find country")
public class CountryNotFoundException  extends RuntimeException{

    public CountryNotFoundException(String message) {
        super(message);
    }
}
