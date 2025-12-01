package gui;

import controller.Controller;
import controller.Storage;
import javafx.application.Application;
import model.*;
import storage.ListStorage;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String fileName = "src/storage.ser";

        Storage storage = ListStorage.loadStorage(fileName);

        if (storage == null) {
            storage = new ListStorage();
            System.out.println("Empty ListStorage is created");
            Controller.setStorage(storage);

            initStorage();
            System.out.println("Storage is initialized");

            ListStorage.saveStorage(fileName, storage);
        } else {
            Controller.setStorage(storage);
            System.out.println("Storage loaded from file");
        }

        Application.launch(Gui.class);
    }

    public static void initStorage() {
        // Objekter af vores lagerstyring
        Hylde hyldeA10001 = Controller.opretHylde("00-01");
        Hylde hyldeA10002 = Controller.opretHylde("00-02");
        Hylde hyldeA10003 = Controller.opretHylde("00-03");
        Hylde hyldeA11001 = Controller.opretHylde("10-01");
        Hylde hyldeA11002 = Controller.opretHylde("10-02");
        Hylde hyldeA11003 = Controller.opretHylde("10-03");
        Hylde hyldeA20001 = Controller.opretHylde("00-01");
        Hylde hyldeA20002 = Controller.opretHylde("00-02");
        Hylde hyldeA20003 = Controller.opretHylde("00-03");

        Lager hammelLager = Controller.opretLager("Vestagervej 1, 8450 Hammel", 135);
        Lager randersLager = Controller.opretLager("Metervej 6, 8940 Randers SV", 300);

        Reol reolA1 = Controller.opretReol("A1");
        Reol reolA2 = Controller.opretReol("A2");

        reolA1.addHylde(hyldeA10001);
        reolA1.addHylde(hyldeA10002);
        reolA1.addHylde(hyldeA10003);
        reolA1.addHylde(hyldeA11001);
        reolA1.addHylde(hyldeA11002);
        reolA1.addHylde(hyldeA11003);
        reolA2.addHylde(hyldeA20001);
        reolA2.addHylde(hyldeA20002);
        reolA2.addHylde(hyldeA20003);

        hammelLager.addReol(reolA1);
        hammelLager.addReol(reolA2);


        // Objekter af leverandører og fade
        Leverandør glenfiddich = Controller.opretLeverandør("Glenfiddich", "glenfiddich@gmail.com",
                "23435654", "Castle Road, Dufttown, Moray, AB55 4DH, United Kingdom");
        Leverandør macallan = Controller.opretLeverandør("Macallan", "macallan@gmail.com",
                "12865744", "The Macallan Distillery, Easter Elchies, Craigllachie, AB38 9RX, Scotland");
        Leverandør faryLochan = Controller.opretLeverandør("Fary Lochan", "farylochan@hotmail.com",
                "27290295", "Ågade 41, 7323 Give, Denmark");

        ArrayList<String> tidligereIndhold1 = new ArrayList<>(List.of("Bourbon", "Sherry"));

        Fad bourbon = Controller.opretFad(1, 13, 200, tidligereIndhold1, "United States", true, glenfiddich);
        Fad sherry1 = Controller.opretFad(2, 4, 40, tidligereIndhold1, "Italien1", true, macallan);
        Fad sherry2 = Controller.opretFad(3, 4, 150, tidligereIndhold1, "Italien", true, macallan);
        Fad sherry3 = Controller.opretFad(4, 4, 150, tidligereIndhold1, "Italien", false, faryLochan);
        Fad sherry4 = Controller.opretFad(5, 4, 200, tidligereIndhold1, "Italien", false, faryLochan);
        Fad portBarrel = Controller.opretFad(6, 14, 200, tidligereIndhold1, "Irland", true, glenfiddich);
        Fad mezcal = Controller.opretFad(7, 8, 100, tidligereIndhold1, "Mexico", true, glenfiddich);
        //hh
        //hh
    }
}