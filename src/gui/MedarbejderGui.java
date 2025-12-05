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
import model.Medarbejder;

public class MedarbejderGui extends GridPane {
    private final ListView<Medarbejder> medarbejderListView = new ListView<>();
    private final Button btnOpretMedarbejder = new Button("Opret medarbejder");

    public MedarbejderGui() {
        initContent(this);
    }

    public void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label medarbejderLabel = new Label("Oversigt over medarbejdere");
        pane.add(medarbejderLabel, 0, 0);

        medarbejderListView.getItems().setAll(Controller.getMedarbejdere());
        pane.add(medarbejderListView, 0, 1);
        pane.add(btnOpretMedarbejder, 0, 2);

        btnOpretMedarbejder.setOnAction(event -> opretMedarbejder());
    }

    private void opretMedarbejder() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Opret medarbejder");
        popup.setMinWidth(300);

        Label navnLabel = new Label("Skriv navn på medarbejderen:");
        TextField navnInput = new TextField();

        Label stillingLabel = new Label("Skriv medarbejderens stillingsbetegnelse:");
        TextField stillingInput = new TextField();

        Button btnOk = new Button("Opret");
        btnOk.setOnAction(e -> {
            String navn = navnInput.getText().trim();
            String stilling = stillingInput.getText().trim();

            if (!navn.isEmpty()) {
                Controller.opretMedarbejder(navn, stilling);
                medarbejderListView.getItems().setAll(Controller.getMedarbejdere());
                popup.close();
            }
        });

        Button btnCancel = new Button("Annullér");
        btnCancel.setOnAction(e -> popup.close());

        VBox layout = new VBox(10, navnLabel, navnInput, stillingLabel, stillingInput, btnOk, btnCancel);
        layout.setPadding(new Insets(10));
        popup.setScene(new Scene(layout));
        popup.showAndWait();
    }
}