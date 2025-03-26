package mk.finki.ukim.lab_emt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StayNotFoundException extends RuntimeException {
    public StayNotFoundException(Long stayId) {
        super(String.format("Stay with id: %d is not found", stayId));
    }
}
