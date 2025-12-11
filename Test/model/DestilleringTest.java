package model;

import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DestilleringTest {
    private Malt malt;
    private Medarbejder medarbejder;
    private Destillering destillering;

    @BeforeEach
    void setUp() {
        malt = new Malt("Skårsbæk", "Evergreen");
        medarbejder = new Medarbejder("Alexander", "Produktionsmedarbejder");
        destillering = new Destillering(10, 100, LocalDate.of(2026, 1, 1),
                null, "Tørv", malt, medarbejder);
    }

    @org.junit.jupiter.api.Test
    void getDestilleringsId() {
        assertEquals(10, destillering.getDestilleringsId());
    }

    @org.junit.jupiter.api.Test
    void setSlutDato() {
        LocalDate date = LocalDate.of(2027, 1, 1);
        destillering.setSlutDato(date);
        assertEquals(LocalDate.of(2027, 1, 1), destillering.getSlutDato());
    }

    @org.junit.jupiter.api.Test
    void setErFærdigTrue() {
        destillering.setErFærdig(true);
        assertTrue(destillering.isErFærdig());
    }

    @org.junit.jupiter.api.Test
    void setErFærdigFalse() {
        destillering.setErFærdig(false);
        assertFalse(destillering.isErFærdig());
    }

    @org.junit.jupiter.api.Test
    void getMalt() {
        assertEquals(malt, destillering.getMalt());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        destillering.setDestilleringsId(9999);
        String result = destillering.toString();

        assertTrue(result.contains("9999"));
    }
}