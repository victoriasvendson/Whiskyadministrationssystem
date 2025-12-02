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

        // Lager tab
        Tab lagerstyring = new Tab("Lagerstyring");
        lagerstyring.setClosable(false);
        lagerstyring.setContent(new LagerGui());

        tabPane.getSelectionModel().select(lagerstyring);

        // Malt tab
        Tab maltTab = new Tab("Opret malt");
        maltTab.setClosable(false);
        maltTab.setContent(new MaltGui());

        // Fad tab
        Tab fadTab = new Tab("Opret fad");
        fadTab.setClosable(false);
        fadTab.setContent(new FadGui());

        tabPane.getTabs().addAll(lagerstyring, maltTab, fadTab);

        Scene scene = new Scene(tabPane, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Whiskyadministrationssystem");
        primaryStage.show();
    }
}