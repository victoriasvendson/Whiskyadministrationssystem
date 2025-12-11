package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Deldestillat implements Væske, Serializable {
    private double mængde;
    private Destillat destillat;
    private int destillatId;
    private double alkoholprocent;
    private LocalDate datoForLagring;

    public Deldestillat(int destillatId, double mængde, double alkoholprocent, Destillat destillat) {
        this.destillatId = destillatId;
        this.mængde = mængde;
        this.destillat = destillat;
        this.alkoholprocent = alkoholprocent;

    }

    public void setDatoForTilføjelse(LocalDate datoForTilføjelse) {
        this.datoForLagring = datoForTilføjelse;
    }

    @Override
    public double getVolumen() {
        return mængde;
    }

    public Malt findMalt() {
        if (destillat == null) {
            return null;
        }

        return destillat.findMalt();
    }

    public double getAlkoholProcent() {
        return alkoholprocent;
    }

    @Override
    public String toString() {
        return "\nId: " + destillatId + ",  mængde: " + mængde + " liter, " + "Dato for tilføjelse: " + datoForLagring + "\n " +
                "malt: " + destillat.getDestillering().getMalt();
    }
}
