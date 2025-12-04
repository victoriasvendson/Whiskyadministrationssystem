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
import model.Fad;
import model.Leverandør;
import model.TidligereIndhold;

import java.util.ArrayList;
import java.util.List;

public class FadGui extends GridPane {
    private final ListView<Fad> fadListView = new ListView<>();
    private final ListView<Leverandør> leverandørListView = new ListView<>();
    private final Button btnOpretFad = new Button("Opret fad");
    private final Button btnOpretLeverandør = new Button("Opret leverandør");

    public FadGui() {
        initContent(this);
    }

    public void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        // Leverandør

        pane.add(btnOpretLeverandør, 1, 2);

        // Fade
        Label fadLabel = new Label("Oversigt over fade");
        pane.add(fadLabel, 1, 0);
        fadListView.getItems().setAll(Controller.getFade());
        fadListView.setPrefWidth(400);
        pane.add(fadListView, 1, 1, 2, 1);
        pane.add(btnOpretFad, 2, 2);

        // Knapper
        btnOpretLeverandør.setOnAction(event -> opretLeverandør());
        btnOpretFad.setOnAction(event -> opretFad());
    }

    private void opretFad() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Oprettelse af fad");
        popup.setMinWidth(600);

        ListView<Leverandør> popupListView = new ListView<>();
        popupListView.getItems().addAll(Controller.getLeverandører());
        Label tilføjTidligereIndhold = new Label("Vælg evt. tidligere indhold");
        ListView<TidligereIndhold> tidligereIndholdListView = new ListView<>();
        Label fadIdLabel = new Label("Indtast FadId:");
        TextField FadIdInput = new TextField();
        Label alderLabel = new Label("Indtast alder:");
        TextField alderInput = new TextField();
        Label størrelseLabel = new Label("Indtast størrelse:");
        TextField størrelseInput = new TextField();
        Label landLabel = new Label("Indtast land:");
        TextField landInput = new TextField();
        Label brugbartLabel = new Label("Indtast stand");
        CheckBox brugbarTrueInput = new CheckBox("Brugbart");
        CheckBox brugbartFalseInput = new CheckBox("Ikke brugbart");

        Button btnOk = new Button("Opret");
        Button btnCancel = new Button("Annullér");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(fadIdLabel, 0, 0);
        grid.add(FadIdInput, 1, 0);

        grid.add(alderLabel, 0, 1);
        grid.add(alderInput, 1, 1);

        grid.add(størrelseLabel, 0, 2);
        grid.add(størrelseInput, 1, 2);

        grid.add(landLabel, 0, 3);
        grid.add(landInput, 1, 3);

        grid.add(brugbartLabel, 0, 5);

        grid.add(tilføjTidligereIndhold, 0, 6);

        grid.add(tidligereIndholdListView, 0, 7, 2, 1);
        tidligereIndholdListView.setPrefHeight(150);
        tidligereIndholdListView.getItems().setAll(Controller.getTidligereIndhold());
        HBox box = new HBox(10);
        box.getChildren().addAll(brugbarTrueInput, brugbartFalseInput);
        grid.add(box, 1, 5);

        tidligereIndholdListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        brugbarTrueInput.setOnAction(e -> {
            brugbartFalseInput.setDisable(brugbarTrueInput.isSelected());
        });

        brugbartFalseInput.setOnAction(e -> {
            brugbarTrueInput.setDisable(brugbartFalseInput.isSelected());
        });

        HBox buttonBox = new HBox(10, btnOk, btnCancel);
        grid.add(buttonBox, 1, 8);
        btnOk.disableProperty().bind(popupListView.getSelectionModel().selectedItemProperty().isNull());

        btnOk.setOnAction(e -> {
            int fadId = Integer.parseInt(FadIdInput.getText().trim());
            int alder = Integer.parseInt(alderInput.getText().trim());
            int størrelse = Integer.parseInt(størrelseInput.getText().trim());
            String land = landInput.getText().trim();

            if (fadId > 0 && alder >= 0 && størrelse > 0) {

                if (brugbarTrueInput.isSelected()) {
                    Fad fad = Controller.opretFad(
                            fadId, alder, størrelse, land, true,
                            popupListView.getSelectionModel().getSelectedItem()
                    );
                    if (tidligereIndholdListView.getSelectionModel().getSelectedItems() != null) {
                        List<TidligereIndhold> tidligereIndholdList = new ArrayList<>(List.copyOf(tidligereIndholdListView.
                                getSelectionModel().getSelectedItems()));
                        for (TidligereIndhold tidligereIndhold : tidligereIndholdList) {
                            Controller.addTidligereIndholdTilFad(fad, tidligereIndhold);
                        }
                    }
                }

                if (brugbartFalseInput.isSelected()) {
                   Fad fad = Controller.opretFad(
                            fadId, alder, størrelse, land, false,
                            popupListView.getSelectionModel().getSelectedItem()
                    );
                    if (tidligereIndholdListView.getSelectionModel().getSelectedItems() != null) {
                        List<TidligereIndhold> tidligereIndholdList = new ArrayList<>(List.copyOf(tidligereIndholdListView.
                                getSelectionModel().getSelectedItems()));
                        for (TidligereIndhold tidligereIndhold : tidligereIndholdList) {
                            Controller.addTidligereIndholdTilFad(fad, tidligereIndhold);
                        }
                    }
                }

                fadListView.getItems().setAll(Controller.getFade());
                popup.close();
            }
        });

        btnCancel.setOnAction(e -> popup.close());

        popupListView.setPrefWidth(300);
        HBox layout = new HBox(20, popupListView, grid);
        layout.setPadding(new Insets(10));

        popup.setScene(new Scene(layout));
        popup.showAndWait();
    }

    private void opretLeverandør() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Oprettelse af leverandør");
        popup.setMinWidth(300);

        Label navnLabel = new Label("Indtast navn:");
        TextField navnInput = new TextField();
        Label emailLabel = new Label("Indtast email:");
        TextField emailInput = new TextField();
        Label tlfNrLabel = new Label("Indtast tlf nr:");
        TextField tlfNrInput = new TextField();
        Label adresseLabel = new Label("Indtast adresse:");
        TextField adresseInput = new TextField();


        Button btnOk = new Button("Opret");
        btnOk.setOnAction(e -> {
            String navn = navnInput.getText().trim();
            String email = emailInput.getText().trim();
            String tlfNr = tlfNrInput.getText().trim();
            String adresse = adresseInput.getText().trim();


            if (!navn.isEmpty() || !email.isEmpty() || !tlfNr.isEmpty() || !adresse.isEmpty()) {
                Controller.opretLeverandør(navn, email, tlfNr, adresse);
                leverandørListView.getItems().setAll(Controller.getLeverandører());
                popup.close();
            }
        });

        Button btnCancel = new Button("Annullér");
        btnCancel.setOnAction(e -> popup.close());

        VBox layout = new VBox(10,
                navnLabel, navnInput,
                emailLabel, emailInput,
                tlfNrLabel, tlfNrInput,
                adresseLabel, adresseInput,
                btnOk, btnCancel
        );
        layout.setPadding(new Insets(10));

        popup.setScene(new Scene(layout));
        popup.showAndWait();
    }

    private void opretTidligereIndhold() {
        Button btnTilføjTidligereIndhold = new Button("Tilføj tidligere indhold til fad");
    }
}