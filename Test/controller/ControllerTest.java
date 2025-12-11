package controller;

import model.Destillat;
import model.Destillering;
import model.Malt;
import model.Medarbejder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ListStorage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    //TEST AF USECASE "Opret destillat"
    private Malt malt;
    private Medarbejder medarbejder;
    private Destillering destillering;
    @BeforeEach
    void setUp() {
        Controller.setStorage(new ListStorage());
        malt = new Malt("Skårsbæk", "Evergreen");
        medarbejder = new Medarbejder("Alexander", "Produktionsmedarbejder");

        destillering = new Destillering(10, 100, LocalDate.of(2020, 1, 1), null,
                "Tørv", malt, medarbejder);
    }

    @Test
    void opretDestillat() {
        Destillat d = Controller.opretDestillat(1, 50, destillering, 60);

        assertEquals(1, d.getDestillatId());
        assertEquals(50, d.getMængde());
        assertEquals(60, d.getAlkoholProcent());
        assertEquals(destillering, d.getDestillering());

        assertEquals(LocalDate.now(), destillering.getSlutDato());
    }

    @Test
    void opretDestillatExceptionHvisIdFAlleredeFindes() {
        Controller.opretDestillat(5, 40, destillering, 70);

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                Controller.opretDestillat(5, 30, destillering, 50)
        );
        assertEquals(" destillatID findes allerede", ex.getMessage());
    }
}
