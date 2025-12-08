package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class DelLagring extends Lagring implements Væske, Serializable {
    private double delMængde;

    public DelLagring(LocalDate startDato, LocalDate slutDato, double aftappetMængde, List<Deldestillat> deldestillater, Fad fad, double delMængde) {
        super(startDato, aftappetMængde, deldestillater, fad);
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
