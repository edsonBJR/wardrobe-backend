package com.wardrobe.controller;

import com.wardrobe.model.User;
import com.wardrobe.model.Vanity;
import com.wardrobe.model.Wardrobe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vanities")
public class VanityController {

    private final List<Vanity> vanities = new ArrayList<>(List.of(
            new Vanity(1L, new Wardrobe(1L, new User(1L, "John Doe", "john.doe@example.com"))),
            new Vanity(2L, new Wardrobe(2L, new User(2L, "Jane Doe", "jane.doe@example.com"))),
            new Vanity(3L, new Wardrobe(3L, new User(3L, "Alice Smith", "alice.smith@example.com")
            ))));

    @GetMapping
    public ResponseEntity<List<Vanity>> getAllVanities() {
        return ResponseEntity.ok(vanities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vanity> getVanityById(@PathVariable Long id) {
        Optional<Vanity> vanity = vanities.stream().filter(v -> v.getId().equals(id)).findFirst();
        return vanity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vanity> createVanity(@RequestBody Vanity vanity) {
        vanities.add(vanity);
        return ResponseEntity.status(HttpStatus.CREATED).body(vanity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vanity> updateVanity(@PathVariable Long id, @RequestBody Vanity updatedVanity) {
        for (int i = 0; i < vanities.size(); i++) {
            if (vanities.get(i).getId().equals(id)) {
                vanities.set(i, updatedVanity);
                return ResponseEntity.ok(updatedVanity);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVanity(@PathVariable Long id) {
        if (vanities.removeIf(vanity -> vanity.getId().equals(id))) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
