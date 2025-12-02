package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Fad;

public class FadGui extends GridPane {
    private final ListView<Fad> fadListView = new ListView<>();
    private final Button btnOpretFad = new Button("Opret fad");

    public FadGui() {
        initContent(this);
    }

    public void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label fadLabel = new Label("Vælg fad:");
        pane.add(fadLabel, 0, 0);

        fadListView.getItems().setAll(Controller.getFade());
        pane.add(fadLabel, 0, 1);
        pane.add(btnOpretFad, 0, 2);

        btnOpretFad.setOnAction(event -> opretFad());
    }


    private void opretFad() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Opret fad");
        popup.setMinWidth(300);

        Label fadIdLabel = new Label("Indtast id på fadet:");
        Label alderLabel = new Label("Indtast alder på fadet:");
        Label størrelseLabel = new Label("Indtast størrelse på fadet");
        Label landLabel = new Label("Indtast land på fadet:");
        Label erBrugbartLabel = new Label("Er fadet brugbart:");
    }
}