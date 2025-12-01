package controller;

import model.*;
import java.util.List;

public interface Storage {
    List<Fad> getFade();

    List<Hylde> getHylder();

    List<Lager> getLagre();

    List<Leverandør> getLeverandører();

    List<Reol> getReoler();

    void storeFad(Fad fad);

    void storeHylde(Hylde hylde);

    void storeLager(Lager lager);

    void storeLeverandør(Leverandør leverandør);

    void storeReol(Reol reol);
}