package model;

public class Destillat implements Væske {
    private int destillatId;
    private double mængde;
    private String rygemateriale;
    private Destillering destillering;
    private double alkoholProcent;

    public Destillat(int destillatId, double mængde, String rygemateriale, Destillering destillering, double alkoholProcent) {
        this.destillatId = destillatId;
        this.mængde = mængde;
        this.rygemateriale = rygemateriale;
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
}
