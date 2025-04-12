package mk.finki.ukim.lab_emt.web;

import mk.finki.ukim.lab_emt.model.Guest;
import mk.finki.ukim.lab_emt.model.Host;
import mk.finki.ukim.lab_emt.model.dto.GuestDto;
import mk.finki.ukim.lab_emt.model.dto.HostDto;
import mk.finki.ukim.lab_emt.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
public class HostController {
    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    public List<Host> findAll() {
        return hostService.findAll();
    }

    @GetMapping("/getGuests")
    public List<Guest> findGuestsByHostId(Long hostId) {
        return hostService.findGuestsByHostId(hostId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Host> findById(@PathVariable Long id) {
        return hostService.findById(id)
                .map(host -> ResponseEntity.ok().body(host))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Host> save(@RequestBody HostDto host) {
        return hostService.save(host)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/saveGuest/{id}")
    public ResponseEntity<Host> saveGuest(@PathVariable Long id, @RequestBody GuestDto guest) {
        return hostService.saveGuest(id, guest)
                .map(host -> ResponseEntity.ok().body(host))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Host> update(@PathVariable Long id, @RequestBody HostDto host) {
        return hostService.update(id, host)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}