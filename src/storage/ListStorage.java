package storage;

import controller.Storage;
import model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListStorage implements Storage, Serializable {
    private final ArrayList<Lager> lagre = new ArrayList<>();
    private final ArrayList<Reol> reoler = new ArrayList<>();
    private final ArrayList<Hylde> hylder = new ArrayList<>();
    private final ArrayList<Fad> fade = new ArrayList<>();
    private final ArrayList<Leverandør> leverandører = new ArrayList<>();
    private final ArrayList<TidligereIndhold> tidligereIndholds = new ArrayList<>();
    private final ArrayList<Medarbejder> medarbejdere = new ArrayList<>();
    private final ArrayList<Malt> alleMalt = new ArrayList<>();
    private final ArrayList<Destillering> destilleringer = new ArrayList<>();
    private final ArrayList<Destillat> destillater = new ArrayList<>();
    private final ArrayList<Deldestillat> deldestillater = new ArrayList<>();
    private final ArrayList<Lagring> lagringer = new ArrayList<>();
    private final ArrayList<DelLagring> delLagringer = new ArrayList<>();
    private final ArrayList<Whisky> alleWhisky = new ArrayList<>();




    @Override
    public List<Lager> getLagre() {
        return new ArrayList<>(lagre);
    }

    @Override
    public List<Reol> getReoler() {
        return new ArrayList<>(reoler);
    }

    @Override
    public List<Hylde> getHylder() {
        return new ArrayList<>(hylder);
    }

    @Override
    public List<Fad> getFade() {
        return new ArrayList<>(fade);
    }

    @Override
    public List<Leverandør> getLeverandører() {
        return new ArrayList<>(leverandører);
    }

    @Override
    public List<TidligereIndhold> getTidligereIndhold() {
        return new ArrayList<>(tidligereIndholds);
    }

    @Override
    public List<Medarbejder> getMedarbejdere() {
        return new ArrayList<>(medarbejdere);
    }

    @Override
    public List<Malt> getAlleMalt() {
        return new ArrayList<>(alleMalt);
    }

    @Override
    public List<Destillering> getDestilleringer() {
        return new ArrayList<>(destilleringer);
    }

    @Override
    public  List<Destillat> getDestillater() { return new ArrayList<>(destillater); }

    @Override
    public List<Deldestillat> getDelDestillater() { return new ArrayList<>(deldestillater); }

    @Override
    public List<Lagring> getLagringer() {
        return new ArrayList<>(lagringer);
    }

    @Override
    public List<DelLagring> getDelLagringer() { return new ArrayList<>(delLagringer);}

    @Override
    public List<Whisky> getAlleWhisky() { return new ArrayList<>(alleWhisky); }

    //---------------------------------------------------------------------//

    @Override
    public void storeLager(Lager lager) {
        lagre.add(lager);
    }

    @Override
    public void storeReol(Reol reol) {
        reoler.add(reol);
    }

    @Override
    public void storeHylde(Hylde hylde) {
        hylder.add(hylde);
    }

    @Override
    public void storeFad(Fad fad) {
        fade.add(fad);
    }

    @Override
    public void storeLeverandør(Leverandør leverandør) {
        leverandører.add(leverandør);
    }

    @Override
    public void storeTidligereIndhold(TidligereIndhold tidligereIndhold) { tidligereIndholds.add(tidligereIndhold);}

    @Override
    public void storeMedarbejder(Medarbejder medarbejder) {
        medarbejdere.add(medarbejder);
    }

    @Override
    public void storeMalt(Malt malt) {
        alleMalt.add(malt);
    }

    @Override
    public void storeDestillering(Destillering destillering) {
        destilleringer.add(destillering);
    }

    @Override
    public void storeDestillat (Destillat destillat) { destillater.add(destillat);}

    @Override
    public void storeDelDestillat (Deldestillat deldestillat) { deldestillater.add(deldestillat);}

    @Override
    public void storeLagring(Lagring lagring) {
        lagringer.add(lagring);
    }

    @Override
    public void storeDelLagring (DelLagring delLagring) { delLagringer.add(delLagring);}

    @Override
    public void storeWhisky (Whisky whisky) { alleWhisky.add(whisky);}



    public static ListStorage loadStorage(String fileName) {
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objIn = new ObjectInputStream(fileIn)
        ) {
            Object obj = objIn.readObject();
            ListStorage storage = (ListStorage) obj;
            System.out.println("Storage loaded from file " + fileName);
            return storage;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error deserializing storage");
            System.out.println(ex);
            return null;
        }
    }

    public static void saveStorage(String fileName, Storage storage) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objOut = new ObjectOutputStream(fileOut)
        ) {
            objOut.writeObject(storage);
            System.out.println("Storage saved in file " + fileName);
        } catch (IOException ex) {
            System.out.println("Error serializing storage");
            System.out.println(ex);
            throw new RuntimeException();
        }
    }
}