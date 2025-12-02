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
import model.Hylde;
import model.Lager;
import model.Reol;

public class LagerGui extends GridPane {
    private final ListView<Lager> lagerListView = new ListView<>();
    private final ListView<Reol> reolListView = new ListView<>();
    private final ListView<Hylde> hyldeListView = new ListView<>();
    private final Button btnOpretLager = new Button("Opret lager");
    private final Button btnOpretReol = new Button("Opret reol");
    private final Button btnOpretHylde = new Button("Opret hylde");

    public LagerGui() {
        initContent(this);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lagerLabel = new Label("På hvilket lager befinder du dig?");
        pane.add(lagerLabel, 0, 0);


        //LAGER DEL
        lagerListView.getItems().setAll(Controller.getLagre());
        pane.add(lagerListView, 0, 1);

        lagerListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> updateReoler()
        );

        pane.add(btnOpretLager, 0, 2);

        btnOpretLager.setOnAction(event -> opretLager());


        //REOL DEL
        Label reolLabel = new Label("Reoler:");
        pane.add(reolLabel, 1, 0);
        pane.add(reolListView, 1, 1);

        reolListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> updateHylder()
        );

        pane.add(btnOpretReol, 1, 2);
        btnOpretReol.disableProperty().bind(
                lagerListView.getSelectionModel().selectedItemProperty().isNull()
        );
        btnOpretReol.setOnAction(event -> opretReol());

        //HYLDE DEL
        Label hyldeLabel = new Label("Hylder:");
        pane.add(hyldeLabel, 2, 0);
        pane.add(hyldeListView, 2, 1);

        pane.add(btnOpretHylde, 2, 2);
        btnOpretHylde.disableProperty().bind(
                reolListView.getSelectionModel().selectedItemProperty().isNull()
        );
            btnOpretHylde.setOnAction(event -> opretHylde());

    }


    //METODER
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


    //Oprettelse af nyt lager
    private void opretLager() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Opret Lager");
        popup.setMinWidth(300);

        Label nameLabel = new Label("Lager navn:");
        TextField nameInput = new TextField();

        Label addressLabel = new Label("Størrelse:");
        TextField addressInput = new TextField();

        Button btnOk = new Button("Opret");
        btnOk.setOnAction(e -> {
            String navn = nameInput.getText().trim();
            int størrelse = Integer.parseInt(addressInput.getText());

            if (!navn.isEmpty()) {
                Controller.opretLager(navn, størrelse);
                lagerListView.getItems().setAll(Controller.getLagre());
                popup.close();
            }
        });

        Button btnCancel = new Button("Annullér");
        btnCancel.setOnAction(e -> popup.close());

        VBox layout = new VBox(10,
                nameLabel, nameInput,
                addressLabel, addressInput,
                btnOk, btnCancel
        );
        layout.setPadding(new Insets(10));

        popup.setScene(new Scene(layout));
        popup.showAndWait();
    }

    private void opretReol() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Opret Reol");
        popup.setMinWidth(300);

        Label nameLabel = new Label("Reol navn:");
        TextField reolInput = new TextField();

        Button btnOk = new Button("Opret");
        btnOk.setOnAction(e -> {
            String reol = reolInput.getText().trim();
            Lager lager = lagerListView.getSelectionModel().getSelectedItem();


            if (!reol.isEmpty()) {
                Controller.opretReol(reol, lager);
                updateReoler();
                popup.close();
            }
        });

        Button btnCancel = new Button("Annullér");
        btnCancel.setOnAction(e -> popup.close());

        VBox layout = new VBox(10,
                nameLabel, reolInput,
                btnOk, btnCancel
        );
        layout.setPadding(new Insets(10));

        popup.setScene(new Scene(layout));
        popup.showAndWait();
    }

    private void opretHylde() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Opret Hylde");
        popup.setMinWidth(300);

        Label nameLabel = new Label("Hylde navn:");
        TextField hyldeInput = new TextField();

        Button btnOk = new Button("Opret");
        btnOk.setOnAction(e -> {
            String hylde = hyldeInput.getText().trim();
            Reol reol = reolListView.getSelectionModel().getSelectedItem();


            if (!hylde.isEmpty()) {
                Controller.opretHylde(hylde, reol);
                updateHylder();
                popup.close();
            }
        });

        Button btnCancel = new Button("Annullér");
        btnCancel.setOnAction(e -> popup.close());

        VBox layout = new VBox(10,
                nameLabel, hyldeInput,
                btnOk, btnCancel
        );
        layout.setPadding(new Insets(10));

        popup.setScene(new Scene(layout));
        popup.showAndWait();
    }
}