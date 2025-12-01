package model;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Destillering implements Serializable {
    private int destilleringsId;
    private LocalDate startDato;
    private LocalDate slutDato;
    private double mængde;
    private double alkoholprocent;
    private String rygemateriale;

    public Destillering(int destilleringsId, LocalDate startDato, LocalDate slutDato, double mængde,
                        double alkoholprocent, String rygemateriale) {
        this.destilleringsId = destilleringsId;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.mængde = mængde;
        this.alkoholprocent = alkoholprocent;
        this.rygemateriale = rygemateriale;
    }

    public int getDestilleringsId() {
        return destilleringsId;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public double getMængde() {
        return mængde;
    }

    public double getAlkoholprocent() {
        return alkoholprocent;
    }

    public String getRygemateriale() {
        return rygemateriale;
    }
}