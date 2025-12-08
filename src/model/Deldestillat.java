package model;

import java.io.Serializable;

public class Deldestillat implements Væske, Serializable {
    private double mængde;
    private Lagring lagring;
    private Destillat destillat;
    private int destillatId;
    private double alkoholprocent;

    public Deldestillat(
            int destillatId,
            double delMængde,
            double alkoholprocent,
            Destillat destillat
    ) {
        this.destillatId = destillatId;
        this.mængde = delMængde;
        this.destillat = destillat;
        this.alkoholprocent = alkoholprocent;

    }

    @Override
    public double getVolumen() {
        return mængde;
    }


    public double getAlkoholProcent() {
        return alkoholprocent;
    }


    @Override
    public String toString() {
        return "Delmængde: " + mængde + " liter";
    }
}
