package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DelLagring implements Væske, Serializable {
    private double delMængde;
    private Lagring lagring;
    private LocalDate aftappetDato;

    public DelLagring(double delMængde, LocalDate aftappetDato) {
        this.delMængde = delMængde;
        this.aftappetDato = aftappetDato;
    }

    @Override
    public double getVolumen() {
        return delMængde;
    }

    @Override
    public double getAlkoholProcent() {

        double diff = (double) ChronoUnit.MONTHS.between(lagring.getStartDato(), LocalDate.now());
        double mistetAlkoholProcent = diff * 0.033;
        return lagring.getAlkoholProcent() - mistetAlkoholProcent;
    }

    public Malt findMalt() {
        if (lagring == null) {
            return null;
        }
        return lagring.findMalt();
    }

    public Lagring getLagring() {
        return lagring;
    }

    public void setLagring(Lagring l1) {
        lagring = l1;
    }

    @Override
    public String toString() {
        return "Mængde: " + delMængde + " L\nLageret på: " + lagring.getFad().getAlder() +
                " år gammelt fad fra " + lagring.getFad().getLand() + "\nProduceret af: " +
                lagring.getFad().getLeverandør().getNavn() + "\n";
    }
}
