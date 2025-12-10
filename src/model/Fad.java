package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Fad implements Serializable {
    private int fadId;
    private double alder;
    private int størrelse;
    private String land;
    private boolean erBrugbart;
    private boolean iBrug;
    private Hylde hylde;
    private Leverandør leverandør;
    private ArrayList<TidligereIndhold> tidligereIndholdIFad = new ArrayList<>();

    public Fad(int fadId, double alder, int størrelse,
               String land, boolean erBrugbart, boolean iBrug, Leverandør leverandør) {
        this.fadId = fadId;
        this.alder = alder;
        this.størrelse = størrelse;
        this.land = land;
        this.erBrugbart = erBrugbart;
        this.leverandør = leverandør;
        this.iBrug = iBrug;
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

    public boolean isiBrug() {
        return iBrug;
    }

    public Hylde getHylde() {
        return hylde;
    }

    public void setHylde(Hylde hylde) {
        this.hylde = hylde;
    }

    public void setiBrug(boolean iBrug) {
        this.iBrug = iBrug;
    }

    public Leverandør getLeverandør() {
        return leverandør;
    }

    public void addTidligereIndhold(TidligereIndhold tidligereIndhold) {
        tidligereIndholdIFad.add(tidligereIndhold);
    }

    @Override
    public String toString() {
        String stand;
        if (erBrugbart) {
            stand = "Brugbar";
        } else {
            stand = "Ikke brugbar";
        }
        return "Fad: " + fadId + ", " + stand;
    }
}