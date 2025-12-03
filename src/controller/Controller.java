package controller;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static Storage storage;

    public static Destillering opretDestillering(int destilleringsId, double mængde, LocalDate startDato,
                                                 LocalDate slutDato, String rygemateriale,
                                                 Malt malt) {
        Destillering destillering = new Destillering(destilleringsId, mængde, startDato,
                slutDato, rygemateriale, malt);
        storage.storeDestillering(destillering);

        return destillering;
    }

    public static Destillat opretDestillat(int destillatId, double mængde, Destillering destillering, double alkoholProcent) {

        for (int i = 0; i < storage.getDestillater().size(); i++) {
            if (destillatId != storage.getDestillater().get(i).getDestillatId()) {
                Destillat destillat = new Destillat(destillatId, mængde, destillering, alkoholProcent);
                storage.storeDestillat(destillat);
                destillering.setSlutDato(LocalDate.now());
                return destillat;


            } else {
                throw new RuntimeException(" destillatID findes allerede");
            }
        }
        return null;

    }

    public static Deldestillat opretDelDestillat (int destillatId,
                                                  double destillatMængde,
                                                  Destillering destillering,
                                                  double delMængde,
                                                  double alkoholProcent,
                                                    Destillat destillat) {

        if (destillat.getVolumen() - delMængde >= 0) {
            Deldestillat deldestillat = new Deldestillat(destillatId, destillatMængde, destillering, delMængde, alkoholProcent, destillat);
            storage.storeDelDestillat(deldestillat);
            destillat.setMængde(destillat.getVolumen() - delMængde);

            return deldestillat;


        }

        else {
            throw new RuntimeException("Der er ikke nok destillat");
        }
    }


    public static Fad opretFad(int fadId, double alder, int størrelse, ArrayList<TidligereIndhold>
                                       tidligereIndhold, String land, boolean erBrugbart,
                               Leverandør leverandør) {
        Fad fad = new Fad(fadId, alder, størrelse, tidligereIndhold, land, erBrugbart, leverandør);
        storage.storeFad(fad);
        return fad;
    }

    public static Hylde opretHylde(String hyldeNummer, Reol reol) {
        Hylde hylde = new Hylde(hyldeNummer);
        reol.addHylde(hylde);
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

    public static Reol opretReol(String rækkeNummer, Lager lager) {
        Reol reol = new Reol(rækkeNummer);
        lager.addReol(reol);
        storage.storeReol(reol);
        return reol;
    }

    public static TidligereIndhold opretTidligereIndhold (String væske) {
        TidligereIndhold tidligereIndhold = new TidligereIndhold(væske);
        storage.storeTidligereIndhold(tidligereIndhold);
        return tidligereIndhold;
    }

    public static List<Destillering> getDestilleringer() {
        return storage.getDestilleringer();
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

    public static List<Malt> getAlleMalt() {
        return storage.getAlleMalt();
    }

    public static List<Medarbejder> getMedarbejdere() {
        return storage.getMedarbejdere();
    }

    public static List<Reol> getReoler() {
        return storage.getReoler();
    }

    public static void setStorage(Storage storage) {
        Controller.storage = storage;
    }
}