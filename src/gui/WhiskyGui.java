package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

        Label whiskyLbl = new Label("Whiskys");
        Label lagringerLbl = new Label("Lagringer over 3 år");

        whiskyListView.getItems().setAll(Controller.getAlleWhisky());

        HBox buttonsHbox = new HBox(10, btnTilføjDellagring, btnTilføjVand, btnOpretWhisky);
        VBox lagringVbox = new VBox(10, lagringerLbl, lagringListView);
        VBox whiskyVbox = new VBox(10, whiskyLbl, whiskyListView, buttonsHbox);

        HBox samletHbox = new HBox(10, lagringVbox, whiskyVbox);

        pane.add(samletHbox, 0, 0);

        btnTilføjDellagring.disableProperty().bind(lagringListView.getSelectionModel().selectedItemProperty().isNull());
        btnTilføjDellagring.setOnAction(event -> tilføjDellagring());
        btnTilføjVand.setOnAction(event -> tilføjVand());
        btnOpretWhisky.setOnAction(event -> opretWhisky());
    }

    public void tilføjDellagring() {

    }

    public void tilføjVand() {

    }

    public void opretWhisky() {

    }
}
