package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Lagring implements Serializable {
    private LocalDate startDato;
    private LocalDate slutDato;
    private double startMængde;
    private double aftappetMængde;

    public Lagring(LocalDate startDato, LocalDate slutDato, double startMængde, double aftappetMængde) {
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.startMængde = startMængde;
        this.aftappetMængde = aftappetMængde;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public double getStartMængde() {
        return startMængde;
    }

    public double getAftappetMængde() {
        return aftappetMængde;
    }

    public double udregnAngelShare() {
        return startMængde - aftappetMængde;
    }
}