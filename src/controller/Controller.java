package controller;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static Storage storage;

    public static Fad opretFad(int fadId, double alder, int størrelse, ArrayList<TidligereIndhold>
                                       tidligereIndhold, String land, boolean erBrugbart,
                               Leverandør leverandør) {
        Fad fad = new Fad(fadId, alder, størrelse, tidligereIndhold, land, erBrugbart, leverandør);
        storage.storeFad(fad);
        return fad;
    }

    public static Hylde opretHylde(String hyldeNummer) {
        Hylde hylde = new Hylde(hyldeNummer);
        storage.storeHylde(hylde);
        return hylde;
    }

    public static Lager opretLager(String adresse, int størrelse) {
        Lager lager = new Lager(adresse, størrelse);
        storage.storeLager(lager);
        return lager;
    }

    public static Leverandør opretLeverandør(String navn, String email, String telefonnummer,
                                             String adresse) {
        Leverandør leverandør = new Leverandør(navn, email, telefonnummer, adresse);
        storage.storeLeverandør(leverandør);
        return leverandør;
    }

    public static Reol opretReol(String rækkeNummer) {
        Reol reol = new Reol(rækkeNummer);
        storage.storeReol(reol);
        return reol;
    }

    public static List<Fad> getFade() {
        return storage.getFade();
    }

    public static List<Hylde> getHylder() {
        return storage.getHylder();
    }

    public static List<Lager> getLagre() {
        return storage.getLagre();
    }

    public static List<Leverandør> getLeverandører() {
        return storage.getLeverandører();
    }

    public static List<Reol> getReoler() {
        return storage.getReoler();
    }

    public static void setStorage(Storage storage) {
        Controller.storage = storage;
    }
}