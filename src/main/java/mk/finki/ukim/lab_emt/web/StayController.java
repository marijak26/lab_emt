package mk.finki.ukim.lab_emt.web;

import mk.finki.ukim.lab_emt.model.Stay;
import mk.finki.ukim.lab_emt.model.dto.StayDto;
import mk.finki.ukim.lab_emt.service.StayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stays")
public class StayController {
    private final StayService stayService;

    public StayController(StayService stayService) {
        this.stayService = stayService;
    }

    @GetMapping
    public List<Stay> findAll() {
        return stayService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stay> findById(@PathVariable Long id) {
        return stayService.findById(id)
                .map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Stay> save(@RequestBody StayDto stay) {
        return stayService.save(stay)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Stay> update(@PathVariable Long id, @RequestBody StayDto stay) {
        return stayService.update(id, stay)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (stayService.findById(id).isPresent()) {
            stayService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
