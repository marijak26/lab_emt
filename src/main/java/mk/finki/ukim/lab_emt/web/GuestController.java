package mk.finki.ukim.lab_emt.web;

import mk.finki.ukim.lab_emt.model.Guest;
import mk.finki.ukim.lab_emt.model.dto.GuestDto;
import mk.finki.ukim.lab_emt.service.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public List<Guest> findAll() {
        return guestService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> findById(@PathVariable Long id) {
        return guestService.findById(id)
                .map(guest -> ResponseEntity.ok().body(guest))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Guest> save(@RequestBody GuestDto guest) {
        return guestService.save(guest)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Guest> update(@PathVariable Long id, @RequestBody GuestDto guest) {
        return guestService.update(id, guest)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
