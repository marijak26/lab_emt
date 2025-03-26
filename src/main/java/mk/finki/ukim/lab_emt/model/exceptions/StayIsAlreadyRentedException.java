package mk.finki.ukim.lab_emt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class StayIsAlreadyRentedException extends RuntimeException {
    public StayIsAlreadyRentedException(Long stayId) {
        super(String.format("Stay with id: %d is already rented", stayId));
    }

}
