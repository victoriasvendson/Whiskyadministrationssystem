package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Lagring implements Serializable, Væske {
    private LocalDate startDato;
    private LocalDate slutDato;
    private double aftappetMængde;
    private final List<Væske> indhold = new ArrayList<>();
    private Fad fad;
    private boolean erTom = false;

    public Lagring(LocalDate startDato, Fad fad) {
        this.startDato = startDato;
        this.fad = fad;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public int getAlder() {
        return (int) ChronoUnit.MONTHS.between(startDato, LocalDate.now());
    }

    public List<Væske> getDestillater () {
        return indhold;
    }

    public void setErTom(boolean erTom) {
        this.erTom = erTom;

        if(erTom) {
            setSlutDato(LocalDate.now());
            fad.setiBrug(false);
        }
    }

    public Fad getFad() {
        return fad;
    }

    public double udregnAngelShare() {
        if (erTom) {
            return getVolumen() - aftappetMængde;
        } else {
            throw new RuntimeException("Lagring er ikke tom");
        }
    }

    public void setSlutDato(LocalDate slutDato) {
        this.slutDato = slutDato;
    }

    public List<Væske> getIndhold() {
        return indhold;
    }

    public void addDeldestillat(Væske væske) {
        indhold.add(væske);
    }

    public void aftapMængde (double mængde) {
        aftappetMængde+= mængde;
    }

    @Override
    public double getVolumen() {
        double totalVolumen = 0;
        double månedligTab = 0.0025;

        for (Væske væske : indhold) {
            double startVolumen = væske.getVolumen();
            double reduceretVolumen = startVolumen * Math.pow(1 - månedligTab, getAlder());
            totalVolumen += reduceretVolumen;
        }
        return totalVolumen - aftappetMængde;
    }

    @Override
    public Malt findMalt() {
        for (Væske v : indhold) {
            Malt malt = v.findMalt();
            if (malt != null) {
                return malt;
            }
        }
        return null;
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

    @Override
    public String toString() {
        return fad + "\nLagringstid: "+ getAlder() + " måneder\nNuværende mængde: " + getVolumen() + " liter ";
    }
}