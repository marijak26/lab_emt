package mk.finki.ukim.lab_emt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class AccommodationAlreadyInReservationException extends RuntimeException {
    public AccommodationAlreadyInReservationException(Long stayId, String username){
        super(String.format("Stay with id: %d already exists in reservation for user %s", stayId, username));
    }
}
