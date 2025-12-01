package model;

public class Medarbejder {
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