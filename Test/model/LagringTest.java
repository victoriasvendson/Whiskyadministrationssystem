package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

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

    @Test
    void udregnAngelShare() {
    }

    @Test
    void getVolumen() {
    }

    @Test
    void getAlkoholProcent() {
    }
}