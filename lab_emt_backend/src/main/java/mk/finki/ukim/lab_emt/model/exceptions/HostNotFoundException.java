package mk.finki.ukim.lab_emt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HostNotFoundException extends RuntimeException {
    public HostNotFoundException(Long hostId) {
        super(String.format("Host with id: %d is not found", hostId));
    }
}
