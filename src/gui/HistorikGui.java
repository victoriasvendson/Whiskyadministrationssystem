package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import model.*;

import java.util.ArrayList;
import java.util.List;


public class HistorikGui extends GridPane {
    private static final ListView<Whisky> whiskyListView = new ListView<>();
    private final TextArea whiskyHistorikTxa = new TextArea();

    public HistorikGui() {
        initContent(this);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        //1
        Label whiskysLbl = new Label("Alle whiskys");
        pane.add(whiskysLbl, 0, 0);
        pane.add(whiskyListView, 0, 1);
        whiskyListView.setPrefSize(500, 400);

        //2
        Label historieLbl = new Label("Whiskyens historie");
        pane.add(historieLbl, 1, 0);
        pane.add(whiskyHistorikTxa, 1, 1);

        whiskyListView.getItems().setAll(Controller.getAlleWhisky());

        //Metode kald
        whiskyListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateTxtArea());
        whiskyHistorikTxa.setEditable(false);
        whiskyHistorikTxa.setText("Klik på en whisky for at vise dens historie");
    }

    public void updateTxtArea() {
        Whisky whisky = whiskyListView.getSelectionModel().getSelectedItem();

        StringBuilder sb = new StringBuilder();

        sb.append("Whiskyens historie:\n\n");

        sb.append("Navn: ").append(whisky.getNavn()).append("\n");
        sb.append("Vandmængde: ").append(whisky.getVandMængde()).append(" L\n");
        sb.append("Totalt volumen (inkl. vand): ").append(whisky.getTotalMængde()).append(" L\n");
        sb.append("Nuværende alkoholprocent: ").append(String.format("%.2f", whisky.getAlkoholProcent())).append(" %\n\n");

        sb.append("Alle lagringer i whiskyen:\n");
        sb.append(whisky.getDelLagringer()).append("\n\n");

        whiskyHistorikTxa.setText(sb.toString());
    }

    public static void refreshWhiskyList() {
        whiskyListView.getItems().setAll(Controller.getAlleWhisky());
    }
}