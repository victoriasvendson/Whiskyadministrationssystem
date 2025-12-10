package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Lagring;
import model.Whisky;

public class WhiskyGui extends GridPane {

    public WhiskyGui() {
        initContent(this);
    }

    private final ListView<Lagring> lagringListView = new ListView<>();
    private final ListView<Whisky> whiskyListView = new ListView<>();
    private final Button btnOpretWhisky = new Button("Opret whisky");
    private final Button btnTilføjVand = new Button("Tilføj vand");
    private final Button btnTilføjDellagring = new Button("Tilføj lagring");

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label whiskyLbl = new Label("Vælg whiskys");
        Label lagringerLbl = new Label("Vælg lagringer over 3 år");

        whiskyListView.getItems().setAll(Controller.getAlleWhisky());


        lagringListView.getItems().setAll(Controller.lagringerOver3År());

        HBox buttonsHbox = new HBox(10, btnTilføjDellagring, btnTilføjVand, btnOpretWhisky);
        VBox lagringVbox = new VBox(10, lagringerLbl, lagringListView);
        VBox whiskyVbox = new VBox(10, whiskyLbl, whiskyListView, buttonsHbox);

        HBox samletHbox = new HBox(10, lagringVbox, whiskyVbox);

        pane.add(samletHbox, 0, 0);

        btnTilføjVand.disableProperty().bind(whiskyListView.getSelectionModel().selectedItemProperty().isNull());
        btnTilføjDellagring.disableProperty().bind(whiskyListView.getSelectionModel().selectedItemProperty().isNull().or(lagringListView.getSelectionModel().selectedItemProperty().isNull()));

        btnTilføjDellagring.setOnAction(event -> tilføjDellagring());
        btnTilføjVand.setOnAction(event -> tilføjVand());
        btnOpretWhisky.setOnAction(event -> opretWhisky());
    }

    public void tilføjDellagring() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Tilføj lagring til whisky");
        popup.setMinWidth(300);

        Label errorLbl = new Label("Du har indtastet en mængde der er større end den på lagringen");
        errorLbl.setStyle("-fx-text-fill: red;");
        errorLbl.setVisible(false);

        Label destillatMængdeLbl = new Label("Indtast mængde af destillat");
        TextField destillatMængdeTxf = new TextField();
        Button btnOk = new Button("Opret");

        btnOk.setOnAction(event -> {
            double dellagringsMængde = Double.parseDouble(destillatMængdeTxf.getText().trim());
            double valgtLagring = lagringListView.getSelectionModel().getSelectedItem().getVolumen();

            if (dellagringsMængde <= valgtLagring && valgtLagring > 0) {
                Controller.addDelLagringTilWhisky(lagringListView.getSelectionModel().getSelectedItem(), whiskyListView.getSelectionModel().getSelectedItem(), dellagringsMængde);
                whiskyListView.getItems().setAll(Controller.getAlleWhisky());
                lagringListView.getItems().setAll(Controller.lagringerOver3År());
                popup.close();
            } else {
                errorLbl.setVisible(true);
            }
        });

        Button btnCancel = new Button("Annullér");
        btnCancel.setOnAction(e -> popup.close());

        HBox destillatMængdeHbox = new HBox(10, destillatMængdeLbl, destillatMængdeTxf);
        VBox destillatMængdeVbox = new VBox(10, destillatMængdeHbox, errorLbl);
        HBox knapper = new HBox(20, btnOk, btnCancel);
        VBox layout = new VBox(10, destillatMængdeVbox, knapper);
        layout.setPadding(new Insets(10));

        popup.setScene(new Scene(layout));
        popup.showAndWait();

    }

    public void tilføjVand() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Tilføj vand til whisky");
        popup.setMinWidth(300);

        Label errorLbl = new Label("Vandmængde skal være større end 0");
        errorLbl.setStyle("-fx-text-fill: red;");
        errorLbl.setVisible(false);

        Label vandMængdeLbl = new Label("Indtast vandmængde:");
        TextField vandMængdeTxf = new TextField();
        Button btnOk = new Button("Opret");

        btnOk.setOnAction(event -> {
            double vandmængde = Double.parseDouble(vandMængdeTxf.getText().trim());
            if (vandmængde >= 0) {
                Controller.addVandTilWhisky(whiskyListView.getSelectionModel().getSelectedItem(), vandmængde);
                whiskyListView.getItems().setAll(Controller.getAlleWhisky());
                popup.close();
            } else {
                errorLbl.setVisible(true);
            }
        });

        Button btnCancel = new Button("Annullér");
        btnCancel.setOnAction(e -> popup.close());

        HBox vandMængde = new HBox(10, vandMængdeLbl, vandMængdeTxf);
        VBox vandMængdeError = new VBox(10, vandMængde, errorLbl);
        HBox knapper = new HBox(20, btnOk, btnCancel);
        VBox layout = new VBox(10, vandMængdeError, knapper);
        layout.setPadding(new Insets(10));

        popup.setScene(new Scene(layout));
        popup.showAndWait();
    }

    public void opretWhisky() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Opret whisky");
        popup.setMinWidth(300);

        Label errorLbl = new Label("Navnefeltet må ikke være tomt");
        errorLbl.setStyle("-fx-text-fill: red;");
        errorLbl.setVisible(false);

        Label navnLbl = new Label("Indtast navn");
        TextField navnTxf = new TextField();

        Button btnOk = new Button("Opret");

        btnOk.setOnAction(event -> {
            String navn = navnTxf.getText().trim();
            if (!navn.isEmpty()) {
                Controller.opretWhisky(navn, 0);
                whiskyListView.getItems().setAll(Controller.getAlleWhisky());
                popup.close();
            } else {
                errorLbl.setVisible(true);
            }
        });

        Button btnCancel = new Button("Annullér");
        btnCancel.setOnAction(e -> popup.close());

        HBox navn = new HBox(10, navnLbl, navnTxf);
        VBox navnMedError = new VBox(10, navn, errorLbl);
        HBox layout = new HBox(20, navnMedError, btnOk, btnCancel);
        layout.setPadding(new Insets(10));

        popup.setScene(new Scene(layout));
        popup.showAndWait();
    }
}
