package controller;

import model.*;
import java.util.List;

public interface Storage {
    List<Fad> getFade();

    List<Hylde> getHylder();

    List<Lager> getLagre();

    List<Lagring> getLagringer();

    List<Leverandør> getLeverandører();

    List<Malt> getAlleMalt();

    List<Medarbejder> getMedarbejdere();

    List<Reol> getReoler();

    List<Whisky> getAlleWhisky();

    void storeFad(Fad fad);

    void storeHylde(Hylde hylde);

    void storeLager(Lager lager);

    void storeLagring(Lagring lagring);

    void storeLeverandør(Leverandør leverandør);

    void storeMalt(Malt malt);

    void storeMedarbejder(Medarbejder medarbejder);

    void storeReol(Reol reol);

    void storeWhisky(Whisky whisky);
}