package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Hylde implements Serializable {
    private String hyldeNummer;
    private Reol reol;
    private ArrayList<Fad> fade = new ArrayList<>();

    public Hylde(String hyldeNummer, Reol reol) {
        this.hyldeNummer = hyldeNummer;
        this.reol = reol;
    }

    public String getHyldeNummer() {
        return hyldeNummer;
    }

    public void addFad(Fad fad) {
        fade.add(fad);
    }

    public void removeFad(Fad fad) {
        fade.remove(fad);
    }

    public ArrayList<Fad> getFade() {
        return fade;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Fad fad : fade) {
            sb.append(fad);
        }
        return "Hylde: " + hyldeNummer + " \n" + sb;
    }
}