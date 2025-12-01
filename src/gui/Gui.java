package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Gui extends Application {

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();

        Tab lagerstyring = new Tab("Lagerstyring");
        lagerstyring.setClosable(false);
        lagerstyring.setContent(new LagerGui());

        tabPane.getTabs().add(lagerstyring);

        tabPane.getSelectionModel().select(lagerstyring);

        Scene scene = new Scene(tabPane, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lagerstyring");
        primaryStage.show();
    }
}