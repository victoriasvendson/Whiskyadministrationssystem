package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lagring implements Serializable, Væske {
    private LocalDate startDato;
    private LocalDate slutDato;
    private double startMængde;
    private double aftappetMængde;
    private final List<Væske> indhold = new ArrayList<>();

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

    @Override
    public double getVolumen() {
        return indhold.stream()
                .mapToDouble(Væske::getVolumen)
                .sum();
    }

    @Override
    public double getAlkoholProcent() {
        double totalVolumen = getVolumen();
        if (totalVolumen == 0) return 0;

        double totalAlkohol = indhold.stream()
                .mapToDouble(s -> s.getVolumen() * s.getAlkoholProcent())
                .sum();

        return totalAlkohol / totalVolumen;
    }
}