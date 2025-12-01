package model;

import java.io.Serializable;

public class Destillering implements Serializable, DestilleringsComponent {
    private int destilleringsId;
    private double mængde;
    private double alkoholprocent;

    public Destillering(int destilleringsId, double mængde, double alkoholprocent) {
        this.destilleringsId = destilleringsId;
        this.mængde = mængde;
        this.alkoholprocent = alkoholprocent;
    }

    @Override
    public double getMængde() {
        return mængde;
    }

    @Override
    public double getAlkoholprocent() {
        return alkoholprocent;
    }
}