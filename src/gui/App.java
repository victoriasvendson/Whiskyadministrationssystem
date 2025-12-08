package gui;

import controller.Controller;
import controller.Storage;
import javafx.application.Application;
import model.*;
import storage.ListStorage;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;

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

        Application.launch(TabGui.class);
        ListStorage.saveStorage(fileName, storage);


    }

    public static void initStorage() {
        // Objekter af vores lagerstyring
        Lager hammelLager = Controller.opretLager("Vestagervej 1, 8450 Hammel", 135);
        Lager randersLager = Controller.opretLager("Metervej 6, 8940 Randers SV", 300);

        Reol reolA1 = Controller.opretReol("A1", hammelLager);
        Reol reolA2 = Controller.opretReol("A2", hammelLager);

        Hylde hyldeA11 = Controller.opretHylde("1", reolA1);
        Hylde hyldeA12 = Controller.opretHylde("2", reolA1);
        Hylde hyldeA13 = Controller.opretHylde("3", reolA1);
        Hylde hyldeA21 = Controller.opretHylde("1", reolA2);
        Hylde hyldeA22 = Controller.opretHylde("2", reolA2);
        Hylde hyldeA23 = Controller.opretHylde("3", reolA2);

        // Objekter af leverandører og fade
        Leverandør glenfiddich = Controller.opretLeverandør("Glenfiddich", "glenfiddich@gmail.com",
                "23435654", "Castle Road, Dufttown, Moray, AB55 4DH, United Kingdom");
        Leverandør macallan = Controller.opretLeverandør("Macallan", "macallan@gmail.com",
                "12865744", "The Macallan Distillery, Easter Elchies, Craigllachie, AB38 9RX, Scotland");
        Leverandør faryLochan = Controller.opretLeverandør("Fary Lochan", "farylochan@hotmail.com",
                "27290295", "Ågade 41, 7323 Give, Denmark");

        TidligereIndhold Whisky = Controller.opretTidligereIndhold("Whisky");
        TidligereIndhold Sherry = Controller.opretTidligereIndhold("Sherry");
        TidligereIndhold Bourbon = Controller.opretTidligereIndhold("Bourbon");
        TidligereIndhold Portvin = Controller.opretTidligereIndhold("Portvin");
        TidligereIndhold Rødvin = Controller.opretTidligereIndhold("Rødvin");
        TidligereIndhold Hvidvin = Controller.opretTidligereIndhold("Hvidvin");
        TidligereIndhold Rom = Controller.opretTidligereIndhold("Rom");
        TidligereIndhold Cognac = Controller.opretTidligereIndhold("Cognac");
        TidligereIndhold Mezcal = Controller.opretTidligereIndhold("Mezcal");


        // Objekter af fade
        Fad bourbon = Controller.opretFad(1, 13, 200, "United States", true, glenfiddich);
        Fad sherry1 = Controller.opretFad(2, 4, 40, "Italien", true, macallan);
        Fad sherry2 = Controller.opretFad(3, 4, 150, "Italien", true, macallan);
        Fad sherry3 = Controller.opretFad(4, 4, 150, "Italien", false, faryLochan);
        Fad sherry4 = Controller.opretFad(5, 4, 200, "Italien", false, faryLochan);
        Fad portBarrel = Controller.opretFad(6, 14, 200, "Irland", true, glenfiddich);
        Fad mezcal = Controller.opretFad(7, 8, 100, "Mexico", true, glenfiddich);
        
        bourbon.addTidligereIndhold(Bourbon);
        sherry1.addTidligereIndhold(Sherry);
        sherry2.addTidligereIndhold(Sherry);
        sherry3.addTidligereIndhold(Sherry);
        sherry4.addTidligereIndhold(Sherry);
        portBarrel.addTidligereIndhold(Portvin);
        mezcal.addTidligereIndhold(Mezcal);


        // Objekter af malt

        Malt laureate = Controller.opretMalt("Kvolbæk", "Laureate");
        Malt planet = Controller.opretMalt("Kvolbæk", "Planet");
        Malt evergreen = Controller.opretMalt("Stadsgård", "Evergreen");
        Malt kwsIrina = Controller.opretMalt("Stadsgård", "KWS Irina");

        Medarbejder drotner = Controller.opretMedarbejder("Drotner", "DestilleringsDrot");



        //Tilføjelse af fad til hylder
        Controller.addFadTilHylde(hyldeA11, bourbon);

        Destillering destillering1 = Controller.opretDestillering(1, 200, LocalDate.of(2025, 12, 3), null, null, laureate, drotner);


    }



}