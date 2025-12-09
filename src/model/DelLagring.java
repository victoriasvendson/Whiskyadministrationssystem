package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class DelLagring implements Væske, Serializable {
    private double delMængde;
    private double alkoholprocent;
    private Lagring lagring;
    private LocalDate aftappetDato;


    public DelLagring(double delMængde) {
        this.delMængde = delMængde;
    }

    @Override
    public double getVolumen() {
        return delMængde;
    }

    @Override
    public double getAlkoholProcent() {
        return alkoholprocent;
    }


}
