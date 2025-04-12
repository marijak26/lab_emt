package mk.finki.ukim.lab_emt.web;

import mk.finki.ukim.lab_emt.model.dto.CreateGuestDto;
import mk.finki.ukim.lab_emt.model.dto.DisplayGuestDto;
import mk.finki.ukim.lab_emt.service.application.GuestApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
    private final GuestApplicationService guestApplicationService;

    public GuestController(GuestApplicationService guestApplicationService) {
        this.guestApplicationService = guestApplicationService;
    }

    @GetMapping
    public List<DisplayGuestDto> findAll() {
        return guestApplicationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayGuestDto> findById(@PathVariable Long id) {
        return guestApplicationService.findById(id)
                .map(displayGuestDto -> ResponseEntity.ok().body(displayGuestDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayGuestDto> save(@RequestBody CreateGuestDto createGuestDto) {
        return guestApplicationService.save(createGuestDto)
                .map(displayGuestDto -> ResponseEntity.ok().body(displayGuestDto))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayGuestDto> update(@PathVariable Long id, @RequestBody CreateGuestDto createGuestDto) {
        return guestApplicationService.update(id, createGuestDto)
                .map(displayGuestDto -> ResponseEntity.ok().body(displayGuestDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
