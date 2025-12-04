package model;

import java.io.Serializable;

public class Destillat implements Væske, Serializable {
    private int destillatId;
    private double mængde;
    private Destillering destillering;
    private double alkoholProcent;

    public Destillat(int destillatId, double mængde, Destillering destillering, double alkoholProcent) {
        this.destillatId = destillatId;
        this.mængde = mængde;
        this.destillering = destillering;
        this.alkoholProcent = alkoholProcent;
    }

    @Override
    public double getVolumen() {
        return mængde;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public void setMængde (double delMængde) {
        mængde = delMængde;
    }

    public int getDestillatId() {
        return destillatId;
    }

    @Override
    public String toString() {
        return "ID: " + destillatId + " Mængde: " + mængde + " Alkohol procent: " + alkoholProcent;
    }
}
