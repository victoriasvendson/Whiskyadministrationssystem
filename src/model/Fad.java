package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Fad implements Serializable {
    private int fadId;
    private double alder;
    private int størrelse;
    private ArrayList<String> tidligereIndhold;
    private String land;
    private boolean erBrugbart;
    private Hylde hylde;
    private Leverandør leverandør;

    public Fad(int fadId, double alder, int størrelse, ArrayList<String> tidligereIndhold,
               String land, boolean erBrugbart, Leverandør leverandør) {
        this.fadId = fadId;
        this.alder = alder;
        this.størrelse = størrelse;
        this.tidligereIndhold = tidligereIndhold;
        this.land = land;
        this.erBrugbart = erBrugbart;
        this.leverandør = leverandør;
    }

    public int getFadId() {
        return fadId;
    }

    public double getAlder() {
        return alder;
    }

    public int getStørrelse() {
        return størrelse;
    }

    public ArrayList<String> getTidligereIndhold() {
        return tidligereIndhold;
    }

    public String getLand() {
        return land;
    }

    public boolean erBrugbart() {
        return erBrugbart;
    }
}