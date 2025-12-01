package model;

import java.util.ArrayList;

public class Whisky {
    private String navn;
    private double whiskyMængde;
    private double vandMængde;
    private ArrayList<Lagring> lagringer = new ArrayList<>();

    public Whisky(String navn, double whiskyMængde, double vandMængde, ArrayList<Lagring> lagringer) {
        this.navn = navn;
        this.whiskyMængde = whiskyMængde;
        this.vandMængde = vandMængde;
        this.lagringer = lagringer;
    }

    public String getNavn() {
        return navn;
    }

    public double getWhiskyMængde() {
        return whiskyMængde;
    }

    public double getVandMængde() {
        return vandMængde;
    }

    public ArrayList<Lagring> getLagringer() {
        return lagringer;
    }

    public void addLagring(Lagring lagring) {
        lagringer.add(lagring);
    }
}