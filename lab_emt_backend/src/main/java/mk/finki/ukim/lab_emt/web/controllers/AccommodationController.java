package mk.finki.ukim.lab_emt.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.lab_emt.dto.CreateAccommodationDto;
import mk.finki.ukim.lab_emt.dto.DisplayAccommodationByIdDto;
import mk.finki.ukim.lab_emt.dto.DisplayAccommodationDto;
import mk.finki.ukim.lab_emt.dto.StatisticsDto;
import mk.finki.ukim.lab_emt.model.views.AccommodationsByHostView;
import mk.finki.ukim.lab_emt.repository.AccommodationsByHostViewRepository;
import mk.finki.ukim.lab_emt.service.application.AccommodationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
@Tag(name = "Accommodation API", description = "Endpoints for managing accommodations")
public class AccommodationController {
    private final AccommodationApplicationService accommodationApplicationService;
    private final AccommodationsByHostViewRepository accommodationsByHostViewRepository;

    public AccommodationController(AccommodationApplicationService accommodationApplicationService, AccommodationsByHostViewRepository accommodationsByHostViewRepository) {
        this.accommodationApplicationService = accommodationApplicationService;
        this.accommodationsByHostViewRepository = accommodationsByHostViewRepository;
    }

    @Operation(summary = "Get all accommodations", description = "Retrieves a list of all available accommodations.")
    @GetMapping
    public List<DisplayAccommodationDto> findAll() {
        return accommodationApplicationService.findAll();
    }

    @Operation(summary = "Get all free accommodations", description = "Retrieves a list of all available accommodations that are not rented.")
    @GetMapping("/free")
    public List<DisplayAccommodationDto> findFree() {
        return accommodationApplicationService.findFree();
    }

    @Operation(summary = "Get accommodation by ID", description = "Retrieves a accommodation by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id) {
        return accommodationApplicationService.findById(id)
                .map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Get accommodation by ID with host", description = "Retrieves a accommodation by its ID along with the host information.")
    @GetMapping("/{id}/details")
    public ResponseEntity<DisplayAccommodationByIdDto> findByIdWithHost(@PathVariable Long id) {
        return accommodationApplicationService.findByIdWithHost(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new accommodation", description = "Creates a new accommodation.")
    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDto> save(@RequestBody CreateAccommodationDto createAccommodationDto) {
        return accommodationApplicationService.save(createAccommodationDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing accommodation", description = "Updates an existing accommodation by its ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAccommodationDto> update(@PathVariable Long id, @RequestBody CreateAccommodationDto createAccommodationDto) {
        return accommodationApplicationService.update(id, createAccommodationDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an accommodation", description = "Deletes an accommodation by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (accommodationApplicationService.findById(id).isPresent()) {
            accommodationApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Rent an accommodation",
               description = "Marks an accommodation as rented by its ID.")
    @PatchMapping("/rent/{id}")
    public ResponseEntity<DisplayAccommodationDto> rent(@PathVariable Long id) {
        if(accommodationApplicationService.findById(id).isPresent()) {
            DisplayAccommodationDto updatedAccommodation = accommodationApplicationService.markAccommodationAsRented(id);
            return ResponseEntity.ok(updatedAccommodation);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Count accommodations by category",
               description = "Counts the number of accommodations in each category.")
    @GetMapping("/accommodationsByCategory")
    public List<StatisticsDto> countAccommodationsByCategory() {
        return accommodationApplicationService.countAccommodationsByCategory();
    }

    @Operation(summary = "Get accommodations by host",
               description = "Retrieves a list of accommodations grouped by host.")
    @GetMapping("/by-host")
    public List<AccommodationsByHostView> getAccommodationsByHost() {
        return accommodationsByHostViewRepository.findAll();
    }
}