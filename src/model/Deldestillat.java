package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Deldestillat implements Væske, Serializable {
    private double mængde;
    private Lagring lagring;
    private Destillat destillat;
    private int destillatId;
    private double alkoholprocent;
    private LocalDate datoForLagring;

    public Deldestillat(
            int destillatId,
            double delMængde,
            double alkoholprocent,
            Destillat destillat
    ) {
        this.destillatId = destillatId;
        this.mængde = delMængde;
        this.destillat = destillat;
        this.alkoholprocent = alkoholprocent;

    }

    public void setDatoForLagring(LocalDate datoForLagring) {
        this.datoForLagring = datoForLagring;
    }

    @Override
    public double getVolumen() {
        return mængde;
    }


    public double getAlkoholProcent() {
        return alkoholprocent;
    }


    @Override
    public String toString() {
        return "\nId: " + destillatId + ",  mængde: " + mængde + " liter, " +
                "Dato for tilføjelse: " + datoForLagring;
    }
}
