package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class TabGui extends Application {

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();

        // Medarbejder tab
        Tab medarbejdertab = new Tab("Opret medarbejder");
        medarbejdertab.setClosable(false);
        medarbejdertab.setContent(new MedarbejderGui());

        // Lager tab
        Tab lagerstyringTab = new Tab("Lagerstyring");
        lagerstyringTab.setClosable(false);
        lagerstyringTab.setContent(new LagerGui());

        tabPane.getSelectionModel().select(lagerstyringTab);

        // Fad tab
        Tab fadTab = new Tab("Opret fad");
        fadTab.setClosable(false);
        fadTab.setContent(new FadGui());

        // Malt tab
        Tab maltTab = new Tab("Opret malt");
        maltTab.setClosable(false);
        maltTab.setContent(new MaltGui());

        // Destillering tab
        Tab destilleringTab = new Tab("Opret destillering");
        destilleringTab.setClosable(false);
        destilleringTab.setContent(new DestilleringGui());

        // Lagring tab
        Tab lagringTab = new Tab("Opret lagring");
        lagringTab.setClosable(false);
        lagringTab.setContent(new LagringGui());

        // Lagring tab
        Tab whiskyTab = new Tab("Opret whisky");
        whiskyTab.setClosable(false);
        whiskyTab.setContent(new WhiskyGui());

        Tab historikTab = new Tab("Whiskyhistorik");
        historikTab.setClosable(false);
        historikTab.setContent(new HistorikGui());

        tabPane.getTabs().addAll(lagerstyringTab,maltTab, fadTab, destilleringTab,
                lagringTab, whiskyTab, historikTab, medarbejdertab);

        Scene scene = new Scene(tabPane, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Whiskyadministrationssystem");
        primaryStage.show();
    }
}