package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Whisky implements Væske, Serializable {
    private String navn;
    private double vandMængde;
    private List<Væske> delLagringer = new ArrayList<>();

    public Whisky(String navn,double vandMængde) {
        this.navn = navn;
        this.vandMængde = vandMængde;
    }

    public String getNavn() {
        return navn;
    }

    public double getTotalMængde() {
        return getVolumen() + getVandMængde();
    }

    public double getVandMængde() {
        return vandMængde;
    }

    public void addVand(double mængde) { vandMængde+= mængde;}

    public void addDelLagring (Væske væske) {
        delLagringer.add(væske);
    }

    public List<Væske> getDelLagringer() {
        return delLagringer;
    }

    @Override
    public double getVolumen() {
        double totalVolumen = 0;
        for (int i = 0; i < delLagringer.size(); i++) {
            totalVolumen+=delLagringer.get(i).getVolumen();

        }
        return totalVolumen;
    }

    @Override
    public double getAlkoholProcent() {
        double totalAlkohol = 0;
        double totalVolumen = vandMængde;

        for (int i = 0; i < delLagringer.size(); i++) {
            double alkoholProcent = delLagringer.get(i).getAlkoholProcent();
            double vol = delLagringer.get(i).getVolumen();

            totalAlkohol += (alkoholProcent / 100.0) * vol;
            totalVolumen += vol;
        }

        if (totalVolumen == 0) return 0;

        return (totalAlkohol / totalVolumen) * 100.0;
    }

    @Override
    public String toString() {
        return navn + ", "  + "Lagringsmængde: " + String.format("%.2f", getVolumen()) + ", Vandmængde: "
                + vandMængde + ", Alkoholprocent: " +  String.format("%.2f", getAlkoholProcent()) + " %, " ;
    }
}