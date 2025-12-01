package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Lager implements Serializable {
    private String adresse;
    private int størrelse;
    private ArrayList<Reol> reoler = new ArrayList<>();

    public Lager(String adresse, int størrelse) {
        this.adresse = adresse;
        this.størrelse = størrelse;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getStørrelse() {
        return størrelse;
    }

    public ArrayList<Reol> getReoler() {
        return reoler;
    }

    public void addReol(Reol reol) {
        reoler.add(reol);
    }

    @Override
    public String toString() {
        return adresse + " " + størrelse + " m²";
    }
}