package com.wardrobe.controller;

import com.wardrobe.model.Accesory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accesories")
public class AccesoryController {

    private final List<Accesory> accesories = new ArrayList<>(List.of(
            new Accesory(1L, "Gold", "M", "Elegant"),
            new Accesory(2L, "Silver", "L", "Casual"),
            new Accesory(3L, "Black", "S", "Sport")
    ));

    @GetMapping
    public ResponseEntity<List<Accesory>> getAllAccesories() {
        return ResponseEntity.ok(accesories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accesory> getAccesoryById(@PathVariable Long id) {
        Optional<Accesory> accesory = accesories.stream().filter(a -> a.getId().equals(id)).findFirst();
        return accesory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Accesory> createAccesory(@RequestBody Accesory accesory) {
        accesories.add(accesory);
        return ResponseEntity.status(HttpStatus.CREATED).body(accesory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Accesory> updateAccesory(@PathVariable Long id, @RequestBody Accesory updatedAccesory) {
        for (int i = 0; i < accesories.size(); i++) {
            if (accesories.get(i).getId().equals(id)) {
                accesories.set(i, updatedAccesory);
                return ResponseEntity.ok(updatedAccesory);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccesory(@PathVariable Long id) {
        if (accesories.removeIf(accesory -> accesory.getId().equals(id))) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
