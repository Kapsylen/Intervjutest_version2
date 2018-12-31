package se.arbetsformedlingen.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Country already exist")
public class CountryConflictException extends RuntimeException {

    public CountryConflictException(String message) { super(message);}
}
