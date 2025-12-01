package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Fad implements Serializable {
    private int fadId;
    private double alder;
    private int størrelse;
    private ArrayList<TidligereIndhold> tidligereIndholdIFad;
    private String land;
    private boolean erBrugbart;
    private Hylde hylde;
    private Leverandør leverandør;

    public Fad(int fadId, double alder, int størrelse, ArrayList<TidligereIndhold> tidligereIndholdIFad,
               String land, boolean erBrugbart, Leverandør leverandør) {
        this.fadId = fadId;
        this.alder = alder;
        this.størrelse = størrelse;
        this.tidligereIndholdIFad = tidligereIndholdIFad;
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

    public ArrayList<TidligereIndhold> getTidligereIndhold() {
        return tidligereIndholdIFad;
    }

    public String getLand() {
        return land;
    }

    public boolean erBrugbart() {
        return erBrugbart;
    }

    public void addTidligereIndhold (TidligereIndhold tidligereIndhold) {
        tidligereIndholdIFad.add(tidligereIndhold);

    }
}