package com.wardrobe.controller;

import com.wardrobe.model.Cloth;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clothes")
public class ClothController {

    private final List<Cloth> clothes = new ArrayList<>(List.of(
            new Cloth(1L, "Red", "M", "Casual"),
            new Cloth(2L, "Blue", "L", "Formal"),
            new Cloth(3L, "Black", "S", "Sport")
    ));

    @GetMapping
    public ResponseEntity<List<Cloth>> getAllClothes() {
        return ResponseEntity.ok(clothes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cloth> getClothById(@PathVariable Long id) {
        Optional<Cloth> cloth = clothes.stream().filter(c -> c.getId().equals(id)).findFirst();
        return cloth.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cloth> createCloth(@RequestBody Cloth cloth) {
        clothes.add(cloth);
        return ResponseEntity.status(HttpStatus.CREATED).body(cloth);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cloth> updateCloth(@PathVariable Long id, @RequestBody Cloth updatedCloth) {
        for (int i = 0; i < clothes.size(); i++) {
            if (clothes.get(i).getId().equals(id)) {
                clothes.set(i, updatedCloth);
                return ResponseEntity.ok(updatedCloth);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCloth(@PathVariable Long id) {
        if (clothes.removeIf(cloth -> cloth.getId().equals(id))) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
