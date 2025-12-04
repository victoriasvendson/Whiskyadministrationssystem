package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Destillering implements Serializable {
    private int destilleringsId;
    private double mængde;
    private LocalDate startDato;
    private LocalDate slutDato;
    private String rygemateriale;
    private Malt malt;


    public Destillering(int destilleringsId, double mængde, LocalDate startDato,
                        LocalDate slutDato, String rygemateriale, Malt malt) {
        this.destilleringsId = destilleringsId;
        this.mængde = mængde;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.rygemateriale = rygemateriale;
        this.malt = malt;

    }

    public void setSlutDato (LocalDate date) {
        slutDato = date;
    }

    @Override
    public String toString() {
        return "ID: " + destilleringsId + " Start dato: " + startDato + " malt: " + malt;
    }
}
