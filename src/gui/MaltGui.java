package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import model.Malt;

public class MaltGui extends GridPane {
    private final ListView<Malt> maltListView = new ListView<>();
    private final Button btnOpretMalt = new Button("Opret malt");

    public MaltGui() {
        initContent(this);
    }

    public void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label maltLabel = new Label("Vælg malt:");
        pane.add(maltLabel, 0, 0);

        maltListView.getItems().setAll(Controller.getAlleMalt());
        pane.add(maltListView, 0, 1);
        pane.add(btnOpretMalt, 0, 2);


    }
}