package mk.finki.ukim.lab_emt.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

import mk.finki.ukim.lab_emt.model.domain.User;
import mk.finki.ukim.lab_emt.model.dto.DisplayStayDto;
import mk.finki.ukim.lab_emt.model.dto.ReservationDto;
import mk.finki.ukim.lab_emt.service.application.ReservationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/reservation")
@Tag(name = "Reservation API", description = "Endpoints for managing reservations")
public class ReservationController {
    private final ReservationApplicationService reservationApplicationService;

    public ReservationController(ReservationApplicationService reservationApplicationService) {
        this.reservationApplicationService = reservationApplicationService;
    }

    @Operation(
            summary = "Get all stays in reservation",
            description = "Retrieves a list of all stays in a reservation by its ID"
    )
    @GetMapping("/list-all-for-reservation/{id}")
    public List<DisplayStayDto> listAllStaysInReservation(@PathVariable Long id) {
        return reservationApplicationService.listAllStaysInReservation(id);
    }


    @Operation(
            summary = "Get active reservation",
            description = "Retrieves the active reservation for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Reservation retrieved successfully"
            ), @ApiResponse(responseCode = "404", description = "Reservation not found")}
    )
    @GetMapping
    public ResponseEntity<ReservationDto> getActiveReservation(HttpServletRequest req) {
        String username = req.getRemoteUser();
        return reservationApplicationService.getActiveReservation(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add stay to reservation",
            description = "Adds a stay to the reservation for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200", description = "Stay added to reservation successfully"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            ), @ApiResponse(
                    responseCode = "404",
                    description = "Stay not found")}
    )
    @PostMapping("/add-stay/{id}")
    public ResponseEntity<ReservationDto> addStayToReservation(
            @PathVariable Long id,
            Authentication authentication
    ) {
        try {
            User user = (User) authentication.getPrincipal();
            return reservationApplicationService.addStayToReservation(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Confirm reservation",
            description = "Confirms the reservation for the logged-in user")
    @PutMapping("/confirm-reservation")
    public ResponseEntity<ReservationDto> confirmReservation(Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            return reservationApplicationService.confirmReservation(user.getUsername())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Cancel reservation",
            description = "Cancels the reservation for the logged-in user")
    @PutMapping("/cancel-reservation")
    public ResponseEntity<ReservationDto> cancelReservation(Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            return reservationApplicationService.cancelReservation(user.getUsername())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

}
