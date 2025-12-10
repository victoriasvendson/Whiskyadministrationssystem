package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Deldestillat implements Væske, Serializable {
    private double mængde;
    private Destillat destillat;
    private int destillatId;
    private double alkoholprocent;
    private LocalDate datoForLagring;

    public Deldestillat(int destillatId, double delMængde, double alkoholprocent, Destillat destillat) {
        this.destillatId = destillatId;
        this.mængde = delMængde;
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

    public Destillat getDestillat() {
        return destillat;
    }

    public Malt findMalt() {
        if (destillat == null) {
            return null;
        }

        return destillat.findMalt();
    }

    public LocalDate getDatoForLagring() {
        return datoForLagring;
    }

    public double getAlkoholProcent() {
        return alkoholprocent;
    }

    @Override
    public String toString() {
        return "\nId: " + destillatId + ",  mængde: " + mængde + " liter, " + "Dato for tilføjelse: " + datoForLagring;
    }
}
