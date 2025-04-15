package mk.finki.ukim.lab_emt.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.lab_emt.model.dto.CreateGuestDto;
import mk.finki.ukim.lab_emt.model.dto.DisplayGuestDto;
import mk.finki.ukim.lab_emt.service.application.GuestApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
@Tag(name = "Guest API", description = "Endpoints for managing guests")
public class GuestController {
    private final GuestApplicationService guestApplicationService;

    public GuestController(GuestApplicationService guestApplicationService) {
        this.guestApplicationService = guestApplicationService;
    }

    @Operation(summary = "Get all guests", description = "Retrieves a list of all available guests.")
    @GetMapping
    public List<DisplayGuestDto> findAll() {
        return guestApplicationService.findAll();
    }

    @Operation(summary = "Get guest by ID", description = "Retrieves a guest by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayGuestDto> findById(@PathVariable Long id) {
        return guestApplicationService.findById(id)
                .map(displayGuestDto -> ResponseEntity.ok().body(displayGuestDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new guest", description = "Creates a new guest.")
    @PostMapping("/add")
    public ResponseEntity<DisplayGuestDto> save(@RequestBody CreateGuestDto createGuestDto) {
        return guestApplicationService.save(createGuestDto)
                .map(displayGuestDto -> ResponseEntity.ok().body(displayGuestDto))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update an existing guest", description = "Updates an existing guest by its ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayGuestDto> update(@PathVariable Long id, @RequestBody CreateGuestDto createGuestDto) {
        return guestApplicationService.update(id, createGuestDto)
                .map(displayGuestDto -> ResponseEntity.ok().body(displayGuestDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
