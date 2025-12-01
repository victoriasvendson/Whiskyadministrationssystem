package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import model.Hylde;
import model.Lager;
import model.Reol;

public class LagerGui extends GridPane {
    private final ListView<Lager> lagerListView = new ListView<>();
    private final ListView<Reol> reolListView = new ListView<>();
    private final ListView<Hylde> hyldeListView = new ListView<>();

    public LagerGui() {
        initContent(this);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lagerLabel = new Label("På hvilket lager befinder du dig?");
        pane.add(lagerLabel, 0, 0);

        lagerListView.getItems().setAll(Controller.getLagre());
        pane.add(lagerListView, 0, 1);

        lagerListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> updateReoler()
        );

        Label reolLabel = new Label("Reoler:");
        pane.add(reolLabel, 1, 0);
        pane.add(reolListView, 1, 1);

        reolListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> updateHylder()
        );

        Label hyldeLabel = new Label("Hylder:");
        pane.add(hyldeLabel, 2, 0);
        pane.add(hyldeListView, 2, 1);
    }


    private void updateReoler() {
        Lager selected = lagerListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            reolListView.getItems().setAll(selected.getReoler());
            hyldeListView.getItems().clear();
        }
    }

    private void updateHylder() {
        Reol selected = reolListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            hyldeListView.getItems().setAll(selected.getHylder());
        }
    }
}