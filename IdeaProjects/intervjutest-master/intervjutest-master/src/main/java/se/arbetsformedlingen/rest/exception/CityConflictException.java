package se.arbetsformedlingen.rest.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="City already exist")
public class CityConflictException extends Exception {

    public CityConflictException(String message) { super(message);}
}
