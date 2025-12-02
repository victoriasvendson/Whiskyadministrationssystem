package model;

public class Destillat {
    private int destillatId;
    private double mængde;
    private String rygemateriale;
    private Destillering destillering;

    public Destillat(int destillatId, double mængde, String rygemateriale, Destillering destillering) {
        this.destillatId = destillatId;
        this.mængde = mængde;
        this.rygemateriale = rygemateriale;
        this.destillering = destillering;
    }
}
