package mk.finki.ukim.lab_emt.model.dto;

import mk.finki.ukim.lab_emt.model.domain.Reservation;
import mk.finki.ukim.lab_emt.model.enumerations.ReservationStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ReservationDto(
        Long id,
        LocalDateTime dateCreated,
        DisplayUserDto user,
        List<DisplayStayDto> stays,
        ReservationStatus status
) {
    public static ReservationDto from(Reservation reservation) {
        return new ReservationDto(
                reservation.getId(),
                reservation.getDateCreated(),
                DisplayUserDto.from(reservation.getUser()),
                DisplayStayDto.from(reservation.getStays()),
                reservation.getStatus()
        );
    }
}
