package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Reol implements Serializable {
    private String rækkeNummer;
    private ArrayList<Hylde> hylder = new ArrayList<>();
    private Lager lager;

    public Reol(String rækkeNummer) {
        this.rækkeNummer = rækkeNummer;
    }

    public ArrayList<Hylde> getHylder() {
        return hylder;
    }

    public void addHylde(Hylde hylde) {
        hylder.add(hylde);
    }

    @Override
    public String toString() {
        return rækkeNummer;
    }
}