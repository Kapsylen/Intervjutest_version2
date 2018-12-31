package se.arbetsformedlingen.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Unable to find country language")
public class CountryLanguageNotFoundException extends RuntimeException {
    public CountryLanguageNotFoundException(String message) {
        super(message);
    }
}
