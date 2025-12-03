package model;

import java.io.Serializable;

public class Deldestillat implements Væske, Serializable {
    private double delMængde;
    private Lagring lagring;
    private Destillat destillat;
    private int destillatId;
    private double alkoholProcent;

    public Deldestillat(
            int destillatId,
            double delMængde,
            double alkoholProcent,
            Destillat destillat
    ) {
        this.destillatId = destillatId;
        this.delMængde = delMængde;
        this.destillat = destillat;
        this.alkoholProcent = alkoholProcent;

    }

    @Override
    public double getVolumen() {
        return delMængde;
    }


    public double getAlkoholProcent() {
        return alkoholProcent;
    }



}
