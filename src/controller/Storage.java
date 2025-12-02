package controller;

import model.*;
import java.util.List;

public interface Storage {

    List<Fad> getFade();

    List<Hylde> getHylder();

    List<Lager> getLagre();

    List<Leverandør> getLeverandører();

    List<Malt> getAlleMalt();

    List<Medarbejder> getMedarbejdere();

    List<Reol> getReoler();

    List<TidligereIndhold> getTidligereIndhold();

    void storeFad(Fad fad);

    void storeHylde(Hylde hylde);

    void storeLager(Lager lager);

    void storeLeverandør(Leverandør leverandør);

    void storeMalt(Malt malt);

    void storeMedarbejder(Medarbejder medarbejder);

    void storeReol(Reol reol);

    void storeTidligereIndhold(TidligereIndhold tidligereIndhold);
}