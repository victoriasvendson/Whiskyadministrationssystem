package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Hylde implements Serializable {
    private String hyldeNummer;
    private Reol reol;
    private ArrayList<Fad> fade = new ArrayList<>();
    private boolean erLedig;

    public Hylde(String hyldeNummer, Reol reol) {
        this.hyldeNummer = hyldeNummer;
        this.reol = reol;
    }

    public void addFad(Fad fad) {
        fade.add(fad);
    }

    public void setErLedig(boolean erLedig) {
        this.erLedig = erLedig;
    }

    public boolean isErLedig() {
        return erLedig;
    }

    @Override
    public String toString() {
        return reol + "-" + hyldeNummer;
    }
}