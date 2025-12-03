package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
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

        Label maltLabel = new Label("Oversigt over malt");
        pane.add(maltLabel, 0, 0);

        maltListView.getItems().setAll(Controller.getAlleMalt());
        pane.add(maltListView, 0, 1);
        pane.add(btnOpretMalt, 0, 2);

        btnOpretMalt.setOnAction(event -> opretMalt());
    }

    private void opretMalt() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Opret malt");
        popup.setMinWidth(300);

        Label bygLabel = new Label("Hvilken byg vil du tilføje?");
        TextField bygInput = new TextField();

        Label markLabel = new Label("Hvilken mark kommer byggen fra?");
        TextField markInput = new TextField();

        Button btnOk = new Button("Opret");
        btnOk.setOnAction(e -> {
            String byg = bygInput.getText().trim();
            String mark = markInput.getText().trim();

            if (!byg.isEmpty()) {
                Controller.opretMalt(byg, mark);
                maltListView.getItems().setAll(Controller.getAlleMalt());
                popup.close();
            }
        });

        Button btnCancel = new Button("Annullér");
        btnCancel.setOnAction(e -> popup.close());

        VBox layout = new VBox(10, bygLabel, bygInput, markLabel, markInput, btnOk, btnCancel);
        layout.setPadding(new Insets(10));
        popup.setScene(new Scene(layout));
        popup.showAndWait();
    }
}