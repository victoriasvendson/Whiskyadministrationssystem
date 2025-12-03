package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Whisky implements Væske, Serializable {
    private String navn;
    private double whiskyMængde;
    private double vandMængde;
    private List<Væske> lagringer = new ArrayList<>();

    public Whisky(String navn, double whiskyMængde, double vandMængde) {
        this.navn = navn;
        this.whiskyMængde = whiskyMængde;
        this.vandMængde = vandMængde;

    }

    public String getNavn() {
        return navn;
    }

    public double getWhiskyMængde() {
        return whiskyMængde;
    }

    public double getVandMængde() {
        return vandMængde;
    }

    @Override
    public double getVolumen() {
        return lagringer.stream()
                .mapToDouble(Væske::getVolumen)
                .sum();
    }

    @Override
    public double getAlkoholProcent() {
        double totalVolumen = getVolumen();
        if (totalVolumen == 0) return 0;

        double totalAlkohol = lagringer.stream()
                .mapToDouble(s -> s.getVolumen() * s.getAlkoholProcent())
                .sum();

        return totalAlkohol / totalVolumen;
    }
}