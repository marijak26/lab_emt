package mk.finki.ukim.lab_emt.dto;

import mk.finki.ukim.lab_emt.model.domain.Reservation;
import mk.finki.ukim.lab_emt.model.enumerations.ReservationStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ReservationDto(
        Long id,
        LocalDateTime dateCreated,
        DisplayUserDto user,
        List<DisplayAccommodationDto> accommodations,
        ReservationStatus status
) {
    public static ReservationDto from(Reservation reservation) {
        return new ReservationDto(
                reservation.getId(),
                reservation.getDateCreated(),
                DisplayUserDto.from(reservation.getUser()),
                DisplayAccommodationDto.from(reservation.getAccommodations()),
                reservation.getStatus()
        );
    }
}
