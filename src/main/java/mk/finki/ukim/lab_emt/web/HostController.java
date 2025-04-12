package mk.finki.ukim.lab_emt.web;

import mk.finki.ukim.lab_emt.model.dto.CreateGuestDto;
import mk.finki.ukim.lab_emt.model.dto.CreateHostDto;
import mk.finki.ukim.lab_emt.model.dto.DisplayGuestDto;
import mk.finki.ukim.lab_emt.model.dto.DisplayHostDto;
import mk.finki.ukim.lab_emt.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
public class HostController {
    private final HostApplicationService hostApplicationService;

    public HostController(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @GetMapping
    public List<DisplayHostDto> findAll() {
        return hostApplicationService.findAll();
    }

    @GetMapping("/getGuests")
    public List<DisplayGuestDto> findGuestsByHostId(Long hostId) {
        return hostApplicationService.findGuestsByHostId(hostId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return hostApplicationService.findById(id)
                .map(displayHostDto -> ResponseEntity.ok().body(displayHostDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto createHostDto) {
        return hostApplicationService.save(createHostDto)
                .map(displayHostDto -> ResponseEntity.ok().body(displayHostDto))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/saveGuest/{id}")
    public ResponseEntity<DisplayHostDto> saveGuest(@PathVariable Long id, @RequestBody CreateGuestDto createGuestDto) {
        return hostApplicationService.saveGuest(id, createGuestDto)
                .map(displayHostDto -> ResponseEntity.ok().body(displayHostDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id, @RequestBody CreateHostDto createHostDto) {
        return hostApplicationService.update(id, createHostDto)
                .map(displayHostDto -> ResponseEntity.ok().body(displayHostDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}