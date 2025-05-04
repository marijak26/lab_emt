package mk.finki.ukim.lab_emt.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.lab_emt.dto.CreateGuestDto;
import mk.finki.ukim.lab_emt.dto.CreateHostDto;
import mk.finki.ukim.lab_emt.dto.DisplayGuestDto;
import mk.finki.ukim.lab_emt.dto.DisplayHostDto;
import mk.finki.ukim.lab_emt.model.projections.HostProjection;
import mk.finki.ukim.lab_emt.model.views.HostsByCountryView;
import mk.finki.ukim.lab_emt.repository.HostsByCountryViewRepository;
import mk.finki.ukim.lab_emt.service.application.HostApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Host API", description = "Endpoints for managing hosts")
public class HostController {
    private final HostApplicationService hostApplicationService;
    private final HostsByCountryViewRepository hostsByCountryViewRepository;

    public HostController(HostApplicationService hostApplicationService, HostsByCountryViewRepository hostsByCountryViewRepository) {
        this.hostApplicationService = hostApplicationService;
        this.hostsByCountryViewRepository = hostsByCountryViewRepository;
    }

    @Operation(summary = "Get all hosts", description = "Retrieves a list of all available hosts.")
    @GetMapping
    public List<DisplayHostDto> findAll() {
        return hostApplicationService.findAll();
    }

    @Operation(summary = "Get all guests by host ID", description = "Retrieves a list of all guests for a specific host.")
    @GetMapping("/getGuests")
    public List<DisplayGuestDto> findGuestsByHostId(Long hostId) {
        return hostApplicationService.findGuestsByHostId(hostId);
    }

    @Operation(summary = "Get host by ID", description = "Retrieves a host by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return hostApplicationService.findById(id)
                .map(displayHostDto -> ResponseEntity.ok().body(displayHostDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get guest by ID", description = "Retrieves a guest by its ID.")
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto createHostDto) {
        return hostApplicationService.save(createHostDto)
                .map(displayHostDto -> ResponseEntity.ok().body(displayHostDto))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Add a guest to a host", description = "Adds a guest to a specific host.")
    @PostMapping("/saveGuest/{id}")
    public ResponseEntity<DisplayHostDto> saveGuest(@PathVariable Long id, @RequestBody CreateGuestDto createGuestDto) {
        return hostApplicationService.saveGuest(id, createGuestDto)
                .map(displayHostDto -> ResponseEntity.ok().body(displayHostDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing host", description = "Updates an existing host by its ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id, @RequestBody CreateHostDto createHostDto) {
        return hostApplicationService.update(id, createHostDto)
                .map(displayHostDto -> ResponseEntity.ok().body(displayHostDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a host", description = "Deletes a host by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (hostApplicationService.findById(id).isPresent()) {
            hostApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    @Operation(summary = "Get hosts by country",
            description = "Retrieves a list of hosts filtered by country.")
    @GetMapping("/by-country")
    public ResponseEntity<?> getHostsByCountry(){
        return ResponseEntity.status(HttpStatus.OK).body(hostApplicationService.findAllHostsByCountry());
    }

    @Operation(summary = "Get host names and surnames",
            description = "Retrieves a list of host names and surnames.")
    @GetMapping("/names")
    public List<HostProjection> getHostNamesAndSurnames() {
        return hostApplicationService.getHostNamesAndSurnames();
    }



}