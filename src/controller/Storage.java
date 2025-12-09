package controller;

import model.*;
import java.util.List;

public interface Storage {
    List<Destillering> getDestilleringer();

    List<Fad> getFade();

    List<Hylde> getHylder();

    List<Lager> getLagre();

    List<Lagring> getLagringer();

    List<DelLagring> getDelLagringer();

    List<Leverandør> getLeverandører();

    List<Malt> getAlleMalt();

    List<Medarbejder> getMedarbejdere();

    List<Reol> getReoler();

    List<TidligereIndhold> getTidligereIndhold();

    List<Destillat> getDestillater();

    List<Deldestillat> getDelDestillater();

    List<Whisky> getAlleWhisky();

    void storeDestillering(Destillering destillering);

    void storeFad(Fad fad);

    void storeHylde(Hylde hylde);

    void storeLager(Lager lager);

    void storeLagring(Lagring lagring);

    void storeDelLagring(DelLagring delLagring);

    void storeLeverandør(Leverandør leverandør);

    void storeMalt(Malt malt);

    void storeMedarbejder(Medarbejder medarbejder);

    void storeReol(Reol reol);

    void storeTidligereIndhold(TidligereIndhold tidligereIndhold);

    void storeDestillat (Destillat destillat);

    void storeDelDestillat (Deldestillat deldestillat);

    void storeWhisky (Whisky whisky);
}