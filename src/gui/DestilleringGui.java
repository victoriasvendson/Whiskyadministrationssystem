package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Destillering;

public class DestilleringGui extends GridPane {
    private final ListView<Destillering> destilleringListView = new ListView<>();
    private final Button btnOpretDestillering = new Button("Opret destillering");
    private final TextField txtDestilleringsId = new TextField();
    private final TextField txtMængde = new TextField();
    private final TextField txtStartDato = new TextField();
    private final TextField txtSlutDato = new TextField();
    private final TextField txtRygemateriale = new TextField();
    private final TextField txtMalt = new TextField();

    public DestilleringGui() {
        initContent(this);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblOversigtDestilleringer = new Label("Oversigt over igangværende destilleringer");
        pane.add(lblOversigtDestilleringer, 0, 0, 1, 1);
        destilleringListView.getItems().setAll(Controller.getDestilleringer());
        pane.add(destilleringListView, 0, 1);
        destilleringListView.setPrefWidth(250);
        destilleringListView.setMaxWidth(250);
        destilleringListView.setPrefHeight(200);
        destilleringListView.setMaxHeight(200);

        Label lblUdfyldNedenstående = new Label("Udfyld nedenstående for at oprette en destillering");
        pane.add(lblUdfyldNedenstående, 1, 0);

        Label lblDestilleringsId = new Label("DestilleringsId:");
        pane.add(lblDestilleringsId, 1, 1);
        pane.add(txtDestilleringsId, 2, 1);

        Label lblMængde = new Label("Mængde:");
        pane.add(lblMængde, 1, 2);
        pane.add(txtMængde, 2, 2);

        Label lblStartDato = new Label("StartDato:");
        pane.add(lblStartDato, 1, 3);
        pane.add(txtStartDato, 2, 3);

        Label lblSlutDato = new Label("SlutDato:");
        pane.add(lblSlutDato, 1, 4);
        pane.add(txtSlutDato, 2, 4);

        Label lblRygemateriale = new Label("Rygemateriale:");
        pane.add(lblRygemateriale, 1, 5);
        pane.add(txtRygemateriale, 2, 5);

        Label lblMalt = new Label("Malt:");
        pane.add(lblMalt, 1, 6);
        pane.add(txtMalt, 2, 6);

        pane.add(btnOpretDestillering, 1, 7);
    }
}