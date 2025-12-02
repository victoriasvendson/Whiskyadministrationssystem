package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Hylde implements Serializable {
    private String hyldeNummer;
    private Reol reol;
    private ArrayList<Fad> fade = new ArrayList<>();

    public Hylde(String hyldeNummer) {
        this.hyldeNummer = hyldeNummer;
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

    @Override
    public String toString() {
        return hyldeNummer;
    }
}