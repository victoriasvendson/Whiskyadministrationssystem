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

        ListView<Deldestillat> deldestillatListView = new ListView<>();
        deldestillatListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        deldestillatListView.getItems().setAll(Controller.getDeldestillater());
        ListView<Fad> fadListView = new ListView<>();
        fadListView.getItems().setAll(Controller.findLedigeFade());

        Label startDatoLabel = new Label("Indtast startdato:");
        TextField startDatoInput = new TextField();

        Label aftappetMængdeLabel = new Label("Indtast aftappet mængde:");
        TextField aftappetMængdeInput = new TextField();

        Button btnOk = new Button("Opret");
        btnOk.disableProperty().bind(deldestillatListView.getSelectionModel().selectedItemProperty().isNull());
        btnOk.disableProperty().bind(fadListView.getSelectionModel().selectedItemProperty().isNull());
        btnOk.setOnAction(e -> {
            LocalDate startDato = LocalDate.parse(startDatoInput.getText().trim());
            double aftappetMængde = Double.parseDouble(aftappetMængdeInput.getText().trim());

            if (aftappetMængde > 0) {
                Controller.opretLagring(startDato, aftappetMængde,
                        deldestillatListView.getSelectionModel().getSelectedItems(),
                        fadListView.getSelectionModel().getSelectedItem());
                        lagringListView.getItems().setAll(Controller.getLagringer());
                popup.close();
            }
        });


        Button btnCancel = new Button("Annullér");
        btnCancel.setOnAction(e -> popup.close());

        VBox maltBox = new VBox(5, new Label("Vælg deldestillat"), deldestillatListView);
        VBox medarbejderBox = new VBox(5, new Label("Vælg fad"), fadListView);
        HBox listViews = new HBox(10, maltBox, medarbejderBox);
        HBox okAnnuler = new HBox(10, btnOk, btnCancel);
        VBox right = new VBox(10,
                startDatoLabel, startDatoInput,
                aftappetMængdeLabel, aftappetMængdeInput,
                okAnnuler
        );

        HBox layout = new HBox(20, right, listViews);
        layout.setPadding(new Insets(10));

        popup.setScene(new Scene(layout));
        popup.showAndWait();
    }
}