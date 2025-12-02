package model;

import java.time.LocalDate;

public class DelLagring extends Lagring implements Væske{
    private double delMængde;
    public DelLagring(
            LocalDate startDato,
            LocalDate slutDato,
            double aftappetMængde,
            double delMængde)
    {
        super(startDato, slutDato, aftappetMængde);
        this.delMængde = delMængde;
    }

    @Override
    public double getVolumen() {
        return delMængde;
    }

    @Override
    public double getAlkoholProcent() {
        return super.getAlkoholProcent();
    }






}
