package com.wardrobe.controller;

import com.wardrobe.model.User;
import com.wardrobe.model.Vanity;
import com.wardrobe.model.Wardrobe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VanityControllerTest {

    private VanityController vanityController;

    @BeforeEach
    void setUp() {
        vanityController = new VanityController();
    }

    @Test
    void testGetAllVanities() {
        ResponseEntity<List<Vanity>> response = vanityController.getAllVanities();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void testGetVanityById_Found() {
        ResponseEntity<Vanity> response = vanityController.getVanityById(1L);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testGetVanityById_NotFound() {
        ResponseEntity<Vanity> response = vanityController.getVanityById(99L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testCreateVanity() {
        Vanity newVanity = new Vanity(4L, new Wardrobe(4L, new User(1L, "John Doe", "john.doe@example.com")));
        ResponseEntity<Vanity> response = vanityController.createVanity(newVanity);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newVanity, response.getBody());
    }

    @Test
    void testUpdateVanity_Found() {
        Vanity updatedVanity = new Vanity(1L, new Wardrobe(10L, new User(1L, "John Doe", "john.doe@example.com")));
        ResponseEntity<Vanity> response = vanityController.updateVanity(1L, updatedVanity);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedVanity.getWardrobe().getId(), response.getBody().getWardrobe().getId());
    }

    @Test
    void testUpdateVanity_NotFound() {
        Vanity updatedVanity = new Vanity(99L, new Wardrobe(10L, new User(1L, "John Doe", "john.doe@example.com")));
        ResponseEntity<Vanity> response = vanityController.updateVanity(99L, updatedVanity);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteVanity_Found() {
        ResponseEntity<Void> response = vanityController.deleteVanity(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteVanity_NotFound() {
        ResponseEntity<Void> response = vanityController.deleteVanity(99L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
