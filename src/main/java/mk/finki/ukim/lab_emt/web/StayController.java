package mk.finki.ukim.lab_emt.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.lab_emt.model.dto.CreateStayDto;
import mk.finki.ukim.lab_emt.model.dto.DisplayStayDto;
import mk.finki.ukim.lab_emt.service.application.StayApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stays")
@Tag(name = "Stay API", description = "Endpoints for managing stays")
public class StayController {
    private final StayApplicationService stayApplicationService;

    public StayController(StayApplicationService stayApplicationService) {
        this.stayApplicationService = stayApplicationService;
    }

    @Operation(summary = "Get all stays", description = "Retrieves a list of all available stays.")
    @GetMapping
    public List<DisplayStayDto> findAll() {
        return stayApplicationService.findAll();
    }

    @Operation(summary = "Get all free stays", description = "Retrieves a list of all available stays that are not rented.")
    @GetMapping("/free")
    public List<DisplayStayDto> findFree() {
        return stayApplicationService.findFree();
    }

    @Operation(summary = "Get stay by ID", description = "Retrieves a stay by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayStayDto> findById(@PathVariable Long id) {
        return stayApplicationService.findById(id)
                .map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new stay", description = "Creates a new stay.")
    @PostMapping("/add")
    public ResponseEntity<DisplayStayDto> save(@RequestBody CreateStayDto createStayDto) {
        return stayApplicationService.save(createStayDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing stay", description = "Updates an existing stay by its ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayStayDto> update(@PathVariable Long id, @RequestBody CreateStayDto createStayDto) {
        return stayApplicationService.update(id, createStayDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a stay", description = "Deletes a stay by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (stayApplicationService.findById(id).isPresent()) {
            stayApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Rent a stay", description = "Marks a stay as rented by its ID.")
    @PatchMapping("/rent/{id}")
    public ResponseEntity<DisplayStayDto> rent(@PathVariable Long id) {
        if(stayApplicationService.findById(id).isPresent()) {
            DisplayStayDto updatedStay = stayApplicationService.markStayAsRented(id);
            return ResponseEntity.ok(updatedStay);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}

//dop baranje - da dodademe nov entitet guest i da go povrzeme so host taka sto preku hostot kje moze da imame uvid koj go rezerviral smestuvanjeto
