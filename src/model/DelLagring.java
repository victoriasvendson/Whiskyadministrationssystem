package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

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

        Period diff = Period.between(lagring.getStartDato(), aftappetDato);
        double mistetAlkoholProcent = diff.getMonths() * 0.033;
        return lagring.getAlkoholProcent() - mistetAlkoholProcent;
    }


}
