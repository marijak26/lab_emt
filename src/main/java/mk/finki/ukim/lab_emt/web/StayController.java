package mk.finki.ukim.lab_emt.web;

import mk.finki.ukim.lab_emt.model.dto.CreateStayDto;
import mk.finki.ukim.lab_emt.model.dto.DisplayStayDto;
import mk.finki.ukim.lab_emt.service.application.StayApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stays")
public class StayController {
    private final StayApplicationService stayApplicationService;

    public StayController(StayApplicationService stayApplicationService) {
        this.stayApplicationService = stayApplicationService;
    }

    @GetMapping
    public List<DisplayStayDto> findAll() {
        return stayApplicationService.findAll();
    }

    @GetMapping("/free")
    public List<DisplayStayDto> findFree() {
        return stayApplicationService.findFree();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayStayDto> findById(@PathVariable Long id) {
        return stayApplicationService.findById(id)
                .map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayStayDto> save(@RequestBody CreateStayDto createStayDto) {
        return stayApplicationService.save(createStayDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayStayDto> update(@PathVariable Long id, @RequestBody CreateStayDto createStayDto) {
        return stayApplicationService.update(id, createStayDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (stayApplicationService.findById(id).isPresent()) {
            stayApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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
