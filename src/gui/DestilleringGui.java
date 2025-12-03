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
        pane.add(lblOversigtDestilleringer, 0, 0);
        destilleringListView.getItems().setAll(Controller.getDestilleringer());
        pane.add(destilleringListView, 0, 1);

        Label lblUdfyldNedenstående = new Label("Udfyld nedenstående for at oprette en destillering");
        pane.add(lblUdfyldNedenstående, 1, 0);

        Label lblDestilleringsId = new Label("DestilleringsId:");
        pane.add(lblDestilleringsId, 1, 1);
        pane.add(txtDestilleringsId, 2, 1);

        Label lblMængde = new Label("Mængde:");
        pane.add(lblMængde, 2, 2);
        pane.add(txtMængde, 2, 3);

        Label lblStartDato = new Label("StartDato:");
        pane.add(lblStartDato, 1, 5);
        pane.add(txtStartDato, 1, 6);

        Label lblSlutDato = new Label("SlutDato:");
        pane.add(lblSlutDato, 1, 7);
        pane.add(txtSlutDato, 1, 8);

        Label lblRygemateriale = new Label("Rygemateriale:");
        pane.add(lblRygemateriale, 1, 9);
        pane.add(txtRygemateriale, 1, 10);

        Label lblMalt = new Label("Malt:");
        pane.add(lblMalt, 1, 11);
        pane.add(txtMalt, 1, 12);

        pane.add(btnOpretDestillering, 1, 13);
    }
}