package controller;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static Storage storage;

    public static Destillering opretDestillering(int destilleringsId, double mængde, LocalDate startDato,
                                                 LocalDate slutDato, String rygemateriale,
                                                 Malt malt, Medarbejder medarbejder) {
        Destillering destillering = new Destillering(destilleringsId, mængde, startDato,
                slutDato, rygemateriale, malt, medarbejder);
        storage.storeDestillering(destillering);

        return destillering;
    }

    public static Destillat opretDestillat(int destillatId, double mængde, Destillering destillering, double alkoholProcent) {


        if (storage.getDestillater().isEmpty()) {
            Destillat destillat = new Destillat(destillatId, mængde, destillering, alkoholProcent);
            storage.storeDestillat(destillat);
            destillering.setSlutDato(LocalDate.now());
            return destillat;
        }
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

    public static Deldestillat opretDelDestillat(Destillat destillat, double delMængde) {
        if (destillat.getVolumen() - delMængde >= 0) {
            Deldestillat deldestillat = new Deldestillat(destillat.getDestillatId(), delMængde, destillat.getAlkoholProcent(), destillat);
            storage.storeDelDestillat(deldestillat);
            destillat.setMængde(destillat.getVolumen() - delMængde);
            return deldestillat;
        } else {
            throw new RuntimeException("Der er ikke nok destillat");
        }
    }

    public static DelLagring opretDelLagring(Whisky whisky, Lagring lagring, double mængde) {
        DelLagring delLagring = new DelLagring(mængde, LocalDate.now());
        storage.storeDelLagring(delLagring);
        lagring.aftapMængde(mængde);
        whisky.addDelLagring(delLagring);
        delLagring.setLagring(lagring);
        return delLagring;
    }

    public static Fad opretFad(int fadId, double alder, int størrelse, String land, boolean erBrugbart,
                               boolean iBrug, Leverandør leverandør) {
        for (Fad fad : storage.getFade()) {
            if (fad.getFadId() == fadId) {
                throw new RuntimeException("FadId eksisterer allerede");
            }
        }
        Fad fad = new Fad(fadId, alder, størrelse, land, erBrugbart, iBrug, leverandør);
        storage.storeFad(fad);
        return fad;
    }

    public static Hylde opretHylde(String hyldeNummer, Reol reol) {
        Hylde hylde = new Hylde(hyldeNummer, reol);
        reol.addHylde(hylde);
        hylde.setErLedig(true);
        storage.storeHylde(hylde);
        return hylde;
    }

    public static void addFadTilHylde(Hylde hylde, Fad fad) {
        hylde.addFad(fad);
        fad.setHylde(hylde);
        hylde.setErLedig(false);
    }

    public static Lager opretLager(String adresse, int størrelse) {
        Lager lager = new Lager(adresse, størrelse);
        storage.storeLager(lager);
        return lager;
    }

    public static Lagring opretLagring(LocalDate startDato, Fad fad) {
        Lagring lagring = new Lagring(startDato, fad);
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

    public static Reol opretReol(String rækkeNummer, Lager lager) {
        Reol reol = new Reol(rækkeNummer);
        lager.addReol(reol);
        storage.storeReol(reol);
        return reol;
    }

    public static TidligereIndhold opretTidligereIndhold(String væske) {
        TidligereIndhold tidligereIndhold = new TidligereIndhold(væske);
        storage.storeTidligereIndhold(tidligereIndhold);
        return tidligereIndhold;
    }

    public static Whisky opretWhisky(String navn, double vandMængde) {
        Whisky whisky = new Whisky(navn, vandMængde);
        storage.storeWhisky(whisky);
        return whisky;
    }

    public static DelLagring addDelLagringTilWhisky(Lagring lagring, Whisky whisky, double mængde) {
        DelLagring delLagring = Controller.opretDelLagring(whisky, lagring, mængde);
        return delLagring;
    }

    public static void addVandTilWhisky(Whisky whisky, double mængde) {
        whisky.addVand(mængde);
    }

    public static List<Lagring> lagringerOver3År() {
        List<Lagring> lagringer = new ArrayList<>();

        for (int i = 0; i < getLagringer().size(); i++) {
            Lagring lagring = getLagringer().get(i);
            if (lagring.getAlder() > 36) {
                lagringer.add(lagring);
            }
        }
        return lagringer;
    }

    public static void addTidligereIndholdTilFad(Fad fad, TidligereIndhold tidligereIndhold) {
        fad.addTidligereIndhold(tidligereIndhold);
    }

    public static void addDeldestillatTilLagring(Lagring lagring, Destillat destillat, double mængde) {
        Deldestillat deldestillat = Controller.opretDelDestillat(destillat, mængde);
        deldestillat.setDatoForTilføjelse(LocalDate.now());
        lagring.addDeldestillat(deldestillat);
    }

    public static ArrayList<Hylde> findLedigeHylder() {
        ArrayList<Hylde> ledigeHylder = new ArrayList<>();
        for (Hylde h : storage.getHylder()) {
            if (h.isErLedig()) {
                ledigeHylder.add(h);
            }
        }
        return ledigeHylder;
    }

    public static ArrayList<Fad> findLedigeFade() {
        ArrayList<Fad> ledigeFade = new ArrayList<>();
        for (Fad f : storage.getFade()) {
            if (f.isiBrug()) {
                ledigeFade.add(f);
            }
        }
        return ledigeFade;
    }

    public static void afslutLagring(Lagring lagring) {
        lagring.setSlutDato(LocalDate.now());
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

    public static List<TidligereIndhold> getTidligereIndhold() {
        return storage.getTidligereIndhold();
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

    public static List<Destillat> getDestillater() {
        return storage.getDestillater();
    }

    public static List<Deldestillat> getDeldestillater() {
        return storage.getDelDestillater();
    }

    public static List<Whisky> getAlleWhisky() {
        return storage.getAlleWhisky();
    }

    public static void setStorage(Storage storage) {
        Controller.storage = storage;
    }
}