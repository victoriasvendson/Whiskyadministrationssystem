package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

import java.time.LocalDate;

public class LagringGui extends GridPane {
    private final ListView<Lagring> lagringListView = new ListView<>();
    private final Button opretLagringButton = new Button("Opret lagring");

    public LagringGui() {
        initContent(this);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(5);

        Label lagringLabel = new Label("Oversigt over igangværende lagringer:");
        pane.add(lagringLabel, 0, 0);

        pane.add(lagringListView, 0, 1);
        lagringListView.getItems().setAll(Controller.getLagringer());
        lagringListView.setPrefSize(300, 600);

        pane.add(opretLagringButton, 0, 5);
        opretLagringButton.setOnAction(event -> opretLagring());
    }

    private void opretLagring() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Oprettelse af lagring");
        popup.setMinWidth(300);

        ListView<Destillat> destillatListView = new ListView<>();
        destillatListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        destillatListView.getItems().setAll(Controller.getDestillater());
        ListView<Fad> fadListView = new ListView<>();
        fadListView.getItems().setAll(Controller.findLedigeFade());

        Label startDatoLabel = new Label("Indtast startdato:");
        TextField startDatoInput = new TextField();

        Label aftappetMængdeLabel = new Label("Indtast mængde af destillat:");
        TextField destillatMængdeInput = new TextField();

        Button btnOk = new Button("Opret");
        btnOk.disableProperty().bind(destillatListView.getSelectionModel().selectedItemProperty().isNull());
        btnOk.disableProperty().bind(fadListView.getSelectionModel().selectedItemProperty().isNull());
        btnOk.setOnAction(e -> {
            LocalDate startDato = LocalDate.parse(startDatoInput.getText().trim());
            double destillatMængde = Double.parseDouble(destillatMængdeInput.getText().trim());

            if (destillatMængde > 0) {
                Lagring lagring = Controller.opretLagring(startDato,
                        fadListView.getSelectionModel().getSelectedItem());
                Controller.addDeldestillatTilLagring(lagring,
                        destillatListView.getSelectionModel().getSelectedItem(), destillatMængde);

                lagringListView.getItems().setAll(Controller.getLagringer());
                popup.close();
            }
        });


        Button btnCancel = new Button("Annullér");
        btnCancel.setOnAction(e -> popup.close());

        VBox maltBox = new VBox(5, new Label("Vælg deldestillat"), destillatListView);
        VBox medarbejderBox = new VBox(5, new Label("Vælg fad"), fadListView);
        HBox listViews = new HBox(10, maltBox, medarbejderBox);
        HBox okAnnuler = new HBox(10, btnOk, btnCancel);
        VBox right = new VBox(10,
                startDatoLabel, startDatoInput,
                aftappetMængdeLabel, destillatMængdeInput,
                okAnnuler
        );

        HBox layout = new HBox(20, right, listViews);
        layout.setPadding(new Insets(10));

        popup.setScene(new Scene(layout));
        popup.showAndWait();
    }
}