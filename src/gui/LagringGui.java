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
    private final Button addDestillatTilLagring = new Button("Tilføj destillat til lagring");
    private final TextArea destillaterPaaLagringTxa = new TextArea("Tryk på en lagring for at se destillater");

    public LagringGui() {
        initContent(this);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(5);

        Label lagringLabel = new Label("Oversigt over igangværende lagringer:");
        pane.add(lagringLabel, 0, 0);

        pane.add(lagringListView, 0, 1, 2, 1);
        lagringListView.getItems().setAll(Controller.getLagringer());
        lagringListView.setPrefSize(300, 600);

        lagringListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateTxtArea());

        opretLagringButton.setOnAction(event -> opretLagring());

        Label destillaterPaaLagringLbl = new Label("Destillater på lagring");
        pane.add(destillaterPaaLagringLbl, 3, 0);
        pane.add(destillaterPaaLagringTxa, 3, 1, 3, 2);

        HBox buttons = new HBox(10, opretLagringButton, addDestillatTilLagring);
        pane.add(buttons, 0, 5);

        addDestillatTilLagring.setOnAction(event -> addDestillatTilLagringMetode());
        addDestillatTilLagring.disableProperty().bind(lagringListView.getSelectionModel().selectedItemProperty().isNull());

        destillaterPaaLagringTxa.setEditable(false);
    }

    public void updateTxtArea() {
        Lagring selected = lagringListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(selected.getIndhold());
            destillaterPaaLagringTxa.setText(sb.toString());
        }
    }

    private void addDestillatTilLagringMetode() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Tilføj deldestillat til lagring");
        popup.setMinWidth(300);

        Label alleDestillater = new Label("Alle destillater");
        ListView<Destillat> destillatListView = new ListView<>();
        destillatListView.getItems().setAll(Controller.getDestillater());
        Label destillatMængdeLbl = new Label("Indtast destillat mængde");
        TextField destillatMængdeTxf = new TextField();

        VBox destillaterVbox = new VBox(10, alleDestillater, destillatListView);
        VBox mængdeVbox = new VBox(10, destillatMængdeLbl, destillatMængdeTxf);

        Button btnOk = new Button("Opret");
        btnOk.disableProperty().bind(destillatListView.getSelectionModel().selectedItemProperty().isNull());

        btnOk.setOnAction(event -> {
            Lagring lagring = lagringListView.getSelectionModel().getSelectedItem();
            Destillat destillat = destillatListView.getSelectionModel().getSelectedItem();
            int destillatMængde = Integer.parseInt(destillatMængdeTxf.getText().trim());

            Controller.addDeldestillatTilLagring(lagring, destillat, destillatMængde);
            lagringListView.getItems().setAll(Controller.getLagringer());
            popup.close();
        });

        Button btnCancel = new Button("Annullér");
        btnCancel.setOnAction(e -> popup.close());

        HBox buttons = new HBox(10, btnOk, btnCancel);
        VBox mængdePlusButtons = new VBox(10, mængdeVbox, buttons);

        HBox layout = new HBox(20, destillaterVbox, mængdePlusButtons);
        layout.setPadding(new Insets(10));

        popup.setScene(new Scene(layout));
        popup.showAndWait();
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
        fadListView.getItems().setAll(Controller.getFade());

        Label startDatoLabel = new Label("Indtast startdato:");
        DatePicker startDatoInput = new DatePicker();

        Label aftappetMængdeLabel = new Label("Indtast mængde af destillat:");
        TextField destillatMængdeInput = new TextField();

        Label errorLbl = new Label("Der er ikke nok destillat");

        errorLbl.setStyle("-fx-text-fill: red;");
        errorLbl.setVisible(false);

        Button btnOk = new Button("Opret");
        btnOk.disableProperty().bind(destillatListView.getSelectionModel().selectedItemProperty().isNull());
        btnOk.disableProperty().bind(fadListView.getSelectionModel().selectedItemProperty().isNull());
        btnOk.setOnAction(e -> {
            LocalDate startDato = startDatoInput.getValue();
            double destillatMængde = Double.parseDouble(destillatMængdeInput.getText().trim());
            double destillatStørrelse = destillatListView.getSelectionModel().getSelectedItem().getVolumen();
            if (destillatMængde > destillatStørrelse) {
                errorLbl.setVisible(true);
            }

            if (destillatMængde > 0) {
                Lagring lagring = Controller.opretLagring(startDato, fadListView.getSelectionModel().getSelectedItem());
                Controller.addDeldestillatTilLagring(lagring, destillatListView.getSelectionModel().getSelectedItem(), destillatMængde);

                lagringListView.getItems().setAll(Controller.getLagringer());
                popup.close();
            }
        });


        Button btnCancel = new Button("Annullér");
        btnCancel.setOnAction(e -> popup.close());

        VBox maltBox = new VBox(5, new Label("Vælg destillat"), destillatListView);
        VBox medarbejderBox = new VBox(5, new Label("Vælg fad"), fadListView);
        HBox listViews = new HBox(10, maltBox, medarbejderBox);
        HBox okAnnuler = new HBox(10, btnOk, btnCancel);
        VBox right = new VBox(10, startDatoLabel, startDatoInput, aftappetMængdeLabel, destillatMængdeInput, okAnnuler, errorLbl);

        HBox layout = new HBox(20, right, listViews);
        layout.setPadding(new Insets(10));

        popup.setScene(new Scene(layout));
        popup.showAndWait();
    }
}