package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PoliticianTest {

    private Politician politician;

    @BeforeEach
    void setUp() {
        politician = new Politician(1, "John Doe", "Fine Gael", "Dublin");
    }

    @Test
    void getId() {
        assertEquals(1, politician.getId());
    }

    @Test
    void getName() {
        assertEquals("John Doe", politician.getName());
    }

    @Test
    void getParty() {
        assertEquals("Fine Gael", politician.getParty());
    }

    @Test
    void getCounty() {
        assertEquals("Dublin", politician.getCounty());
    }

    @Test
    void setName() {
        politician.setName("Jane Smith");
        assertEquals("Jane Smith", politician.getName());
    }

    @Test
    void setParty() {
        politician.setParty("Sinn Fein");
        assertEquals("Sinn Fein", politician.getParty());
    }

    @Test
    void setCounty() {
        politician.setCounty("Cork");
        assertEquals("Cork", politician.getCounty());
    }

    @Test
    void testToString() {
        String expected = "1 - John Doe (Fine Gael, Dublin)";
        assertEquals(expected, politician.toString());
    }
}