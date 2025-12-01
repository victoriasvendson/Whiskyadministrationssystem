package model;

import java.io.Serializable;

public class Medarbejder implements Serializable {
    private String navn;
    private String stillingsbetegnelse;

    public Medarbejder(String navn, String stillingsbetegnelse) {
        this.navn = navn;
        this.stillingsbetegnelse = stillingsbetegnelse;
    }

    public String getNavn() {
        return navn;
    }

    public String getStillingsbetegnelse() {
        return stillingsbetegnelse;
    }
}