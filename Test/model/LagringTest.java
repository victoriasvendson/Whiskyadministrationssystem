package model;


import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class LagringTest {
    private Lagring lagring;
    private Fad fad;

    class TestVæske implements Væske {
        double volume;
        double alkohol;

        TestVæske(double vol, double alc) {
            this.volume = vol;
            this.alkohol = alc;
        }

        @Override
        public double getVolumen() {
            return volume;
        }

        @Override
        public double getAlkoholProcent() {
            return alkohol;
        }

        @Override
        public Malt findMalt() {
            return null;
        }
    }

    @BeforeEach
    void setUp() {
        fad = new Fad(5, 5, 200, "Skotland", true, false, null);
        lagring = new Lagring(LocalDate.of(2026, 1, 12), fad);

        lagring.setSlutDato(LocalDate.of(2026, 1, 12));
    }

    @org.junit.jupiter.api.Test
    void udregnAngelShareTomListe() {
        lagring.setErTom(true);
        assertEquals(0 ,lagring.udregnAngelShare());
    }

    @org.junit.jupiter.api.Test
    void udregnAngelShare1Væske() {
        lagring.addDeldestillat(new TestVæske(10, 40));
        lagring.aftapMængde(2);
        lagring.setErTom(true);
        assertEquals(8 ,lagring.udregnAngelShare());
    }

    @org.junit.jupiter.api.Test
    void udregnAngelShare2Væsker() {
        lagring.addDeldestillat(new TestVæske(20, 40));
        lagring.aftapMængde(10);
        lagring.setErTom(true);
        assertEquals(15 ,lagring.udregnAngelShare());
    }

    @org.junit.jupiter.api.Test
    void getVolumen() {

    }

    @org.junit.jupiter.api.Test
    void getAlkoholProcent() {
    }
}