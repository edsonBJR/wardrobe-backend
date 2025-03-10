package com.wardrobe.controller;

import com.wardrobe.model.User;
import com.wardrobe.model.Wardrobe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wardrobes")
public class WardrobeController {

    private final List<Wardrobe> wardrobes = new ArrayList<>(List.of(
            new Wardrobe(1L,  new User(1L, "John Doe", "john.doe@example.com")),
            new Wardrobe(2L,  new User(2L, "Jane Doe", "jane.doe@example.com")),
            new Wardrobe(3L, new User(3L, "Alice Smith", "alice.smith@example.com"))
    ));

    @GetMapping
    public ResponseEntity<List<Wardrobe>> getAllWardrobes() {
        return ResponseEntity.ok(wardrobes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wardrobe> getWardrobeById(@PathVariable Long id) {
        Optional<Wardrobe> wardrobe = wardrobes.stream().filter(u -> u.getId().equals(id)).findFirst();
        return wardrobe.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Wardrobe> createWardrobe(@RequestBody Wardrobe wardrobe) {
        wardrobes.add(wardrobe);
        return ResponseEntity.status(HttpStatus.CREATED).body(wardrobe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wardrobe> updateWardrobe(@PathVariable Long id, @RequestBody Wardrobe updatedWardrobe) {
        for (int i = 0; i < wardrobes.size(); i++) {
            if (wardrobes.get(i).getId().equals(id)) {
                wardrobes.set(i, updatedWardrobe);
                return ResponseEntity.ok(updatedWardrobe);
            }
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deleteWardrobe(@PathVariable Long id) {
        if (wardrobes.removeIf(wardrobe -> wardrobe.getId().equals(id))) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
