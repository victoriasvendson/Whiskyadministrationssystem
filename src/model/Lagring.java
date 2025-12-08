package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lagring implements Serializable, Væske {
    private LocalDate startDato;
    private LocalDate slutDato;
    private double aftappetMængde;
    private final List<Væske> indhold = new ArrayList<>();
    private List<Deldestillat> deldestillater = new ArrayList<>();
    private Fad fad;

    public Lagring(LocalDate startDato, LocalDate slutDato, double aftappetMængde, List<Deldestillat> deldestillater, Fad fad) {
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.aftappetMængde = aftappetMængde;
        this.deldestillater = deldestillater;
        this.fad = fad;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public double getAftappetMængde() {
        return aftappetMængde;
    }

    public double udregnAngelShare() {
        return getVolumen() - aftappetMængde;
    }

    public void addDeldestillat(Deldestillat deldestillat) {
        deldestillater.add(deldestillat);
    }

    @Override
    public double getVolumen() {

       double totalVolumen = 0;
        for (int i = 0; i < indhold.size(); i++) {
            totalVolumen+=indhold.get(i).getVolumen();

        }
        return totalVolumen;
    }

    @Override
    public double getAlkoholProcent() {
        double totalAlkohol = 0;
        double totalVolumen = 0;

        for (int i = 0; i < indhold.size(); i++) {
            double alkoholProcent = indhold.get(i).getAlkoholProcent();
            double vol = indhold.get(i).getVolumen();

            totalAlkohol += (alkoholProcent / 100.0) * vol;
            totalVolumen += vol;
        }

        if (totalVolumen == 0) return 0;

        return (totalAlkohol / totalVolumen) * 100.0;
    }
}