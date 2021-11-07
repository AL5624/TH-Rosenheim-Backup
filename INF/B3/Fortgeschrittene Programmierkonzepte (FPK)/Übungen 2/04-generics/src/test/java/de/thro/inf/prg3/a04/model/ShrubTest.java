package de.thro.inf.prg3.a04.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShrubTest {

    @Test
    void testGetColor() {
        assertEquals(PlantColor.GREEN, new Shrub("Buxus", "Buxus sempervirens", 1.5).getColor());
    }

    @Test
    void testCreateShrubWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Shrub("Buxus", null, 1.5));
    }

    @Test
    void testCreateShrubWithEmptyFamily() {
        assertThrows(IllegalArgumentException.class, () -> new Shrub(null, "Buxus sempervirens", 1.5));
    }

    @Test
    void testCreateShrubWithNegativeHeight() {
        assertThrows(IllegalArgumentException.class, () -> new Shrub("Buxus", "Buxus sempervirens", -1.5));
    }

    @Test
    void testGetCorrectHeight() {
        Shrub s = new Shrub("Buxus", "Buxus sempervirens", 1.5);
        assertEquals(1.5, s.getHeight(), 0.1);
    }

    @Test
    void testGetCorrectName() {
        Shrub s = new Shrub("Buxus", "Buxus sempervirens", 1.5);
        assertEquals("Buxus sempervirens", s.getName());
    }

    @Test
    void testGetCorrectFamily() {
        Shrub s = new Shrub("Buxus", "Buxus sempervirens", 1.5);
        assertEquals("Buxus", s.getFamily());
    }

}