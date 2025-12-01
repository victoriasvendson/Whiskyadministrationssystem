package controller;

import model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static Storage storage;

    public static Fad opretFad(int fadId, double alder, int størrelse, ArrayList<String>
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

    public static Lagring opretLagring(LocalDate startDato, LocalDate slutDato, double startMængde,
                                       double aftappetMængde) {
        Lagring lagring = new Lagring(startDato, slutDato, startMængde, aftappetMængde);
        storage.storeLagring(lagring);
        return lagring;
    }

    public static Leverandør opretLeverandør(String navn, String email, String telefonnummer,
                                             String adresse) {
        Leverandør leverandør = new Leverandør(navn, email, telefonnummer, adresse);
        storage.storeLeverandør(leverandør);
        return leverandør;
    }

    public static Malt opretMalt(String mark, String kornsort) {
        Malt malt = new Malt(mark, kornsort);
        storage.storeMalt(malt);
        return malt;
    }

    public static Medarbejder opretMedarbejder(String navn, String stillingsbetegnelse) {
        Medarbejder medarbejder = new Medarbejder(navn, stillingsbetegnelse);
        storage.storeMedarbejder(medarbejder);
        return medarbejder;
    }

    public static Reol opretReol(String rækkeNummer) {
        Reol reol = new Reol(rækkeNummer);
        storage.storeReol(reol);
        return reol;
    }

    public static Whisky opretWhisky(String navn, double whiskyMængde, double vandMængde,
                                     ArrayList<Lagring> lagringer) {
        Whisky whisky = new Whisky(navn, whiskyMængde, vandMængde, lagringer);
        storage.storeWhisky(whisky);
        return whisky;
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

    public static List<Lagring> getLagringer() {
        return storage.getLagringer();
    }

    public static List<Leverandør> getLeverandører() {
        return storage.getLeverandører();
    }

    public static List<Malt> getAlleMalt() {
        return storage.getAlleMalt();
    }

    public static List<Medarbejder> getMedarbejdere() {
        return storage.getMedarbejdere();
    }

    public static List<Reol> getReoler() {
        return storage.getReoler();
    }

    public static List<Whisky> getAlleWhisky() {
        return storage.getAlleWhisky();
    }

    public static void setStorage(Storage storage) {
        Controller.storage = storage;
    }
}