package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Destillering implements Serializable {
    private int destilleringsId;
    private double mængde;
    private LocalDate startDato;
    private LocalDate slutDato;
    private String rygemateriale;
    private Malt malt;
    private Medarbejder medarbejder;
    private boolean erFærdig = false;


    public Destillering(int destilleringsId, double mængde, LocalDate startDato,
                        LocalDate slutDato, String rygemateriale, Malt malt, Medarbejder medarbejder) {
        this.destilleringsId = destilleringsId;
        this.mængde = mængde;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.rygemateriale = rygemateriale;
        this.malt = malt;
        this.medarbejder = medarbejder;
    }

    public int getDestilleringsId() {
        return destilleringsId;
    }

    public void setSlutDato (LocalDate date) {
        slutDato = date;
    }

    public void setErFærdig(boolean erFærdig) {
        this.erFærdig = erFærdig;
    }

    public Malt getMalt() {
        return malt;
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }

    @Override
    public String toString() {
        String s;
        if (erFærdig) {
             s = "Aftappet";
        } else {
            s = "Destillerer";
        }
        return "ID: " + destilleringsId + " Start dato: " + startDato + "\nmalt: " + malt + "\nStatus: " + s
                + "\nMængde: " + mængde + " L";
    }
}
