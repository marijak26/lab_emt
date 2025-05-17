package mk.finki.ukim.lab_emt.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

import mk.finki.ukim.lab_emt.constants.JwtConstants;
import mk.finki.ukim.lab_emt.helpers.JwtHelper;
import mk.finki.ukim.lab_emt.dto.DisplayAccommodationDto;
import mk.finki.ukim.lab_emt.dto.ReservationDto;
import mk.finki.ukim.lab_emt.service.application.ReservationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static mk.finki.ukim.lab_emt.constants.JwtConstants.TOKEN_PREFIX;


@RestController
@RequestMapping("/api/reservation")
@Tag(name = "Reservation API", description = "Endpoints for managing reservations")
public class ReservationController {
    private final ReservationApplicationService reservationApplicationService;
    private final JwtHelper jwtHelper;

    public ReservationController(ReservationApplicationService reservationApplicationService, JwtHelper jwtHelper) {
        this.reservationApplicationService = reservationApplicationService;
        this.jwtHelper = jwtHelper;
    }

    @Operation(
            summary = "Get all accommodations in reservation",
            description = "Retrieves a list of all accommodations in a reservation by its ID"
    )
    @GetMapping("/list-all-for-reservation/{id}")
    public List<DisplayAccommodationDto> listAllAccommodationsInReservation(@PathVariable Long id) {
        return reservationApplicationService.listAllAccommodationsInReservation(id);
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
    public ResponseEntity<ReservationDto> getActiveReservation(HttpServletRequest request) {
        String username = extractUsernameFromRequest(request);
        return reservationApplicationService.getActiveReservation(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add accommodation to reservation",
            description = "Adds an accommodation to the reservation for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200", description = "Accommodation added to reservation successfully"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            ), @ApiResponse(
                    responseCode = "404",
                    description = "Accommodation not found")}
    )
    @PostMapping("/add-accommodation/{id}")
    public ResponseEntity<ReservationDto> addAccommodationToReservation(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        try {
            String username = extractUsernameFromRequest(request);
            return reservationApplicationService.addAccommodationToReservation(username, id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Remove accommodation from reservation",
            description = "Removes an accommodation from the reservation for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Accommodation removed from reservation successfully"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            ), @ApiResponse(
                    responseCode = "404",
                    description = "Accommodation not found")}
    )
    @DeleteMapping("/remove-accommodation/{id}")
    public ResponseEntity<ReservationDto> removeAccommodationFromReservation(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        try {
            String username = extractUsernameFromRequest(request);
            return reservationApplicationService.removeAccommodationFromReservation(username, id)
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
    public ResponseEntity<ReservationDto> confirmReservation(HttpServletRequest request) {
        try {
            String username = extractUsernameFromRequest(request);
            return reservationApplicationService.confirmReservation(username)
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
    public ResponseEntity<ReservationDto> cancelReservation(HttpServletRequest request) {
        try {
            String username = extractUsernameFromRequest(request);
            return reservationApplicationService.cancelReservation(username)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    private String extractUsernameFromRequest(HttpServletRequest request) {
        String headerValue = request.getHeader(JwtConstants.HEADER);
        if (headerValue == null || !headerValue.startsWith(TOKEN_PREFIX)) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }
        String token = headerValue.substring(TOKEN_PREFIX.length());
        return jwtHelper.extractUsername(token);
    }

}
