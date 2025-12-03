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
import model.Destillat;
import model.Fad;
import model.Leverandør;
import model.Reol;

public class FadGui extends GridPane {
    private final ListView<Fad> fadListView = new ListView<>();
    private final ListView<Leverandør> leverandørListView = new ListView<>();
    private final ListView<Destillat> destillatListView = new ListView<>();
    private final Button btnOpretFad = new Button("Opret fad");
    private final Button btnOpretLeverandør = new Button("Opret leverandør");
    private final Button btnPaafyldDestillat = new Button("Påfyld destillat");

    public FadGui() {
        initContent(this);
    }

    public void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        // Leverandør
        Label leverandørLabel = new Label("Leverandører");
        pane.add(leverandørLabel, 0, 0);
        leverandørListView.getItems().setAll(Controller.getLeverandører());
        pane.add(leverandørListView, 0, 1);
        pane.add(btnOpretLeverandør, 0, 2);

        // Fade
        Label fadLabel = new Label("Oversigt over fade");
        pane.add(fadLabel, 1, 0);
        fadListView.getItems().setAll(Controller.getFade());
        pane.add(fadListView, 1, 1);
        pane.add(btnOpretFad, 1, 2);

        // Destillater
        Label destillatLabel = new Label("Fad indhold");
        pane.add(destillatLabel, 2, 0);
        destillatListView.getItems().setAll(Controller.getDestillater());
        pane.add(destillatListView, 2, 1);
        pane.add(btnPaafyldDestillat, 2, 2);

        // Knapper
        btnOpretLeverandør.setOnAction(event -> opretLeverandør());
        btnOpretFad.setOnAction(event -> opretFad());
        btnPaafyldDestillat.setOnAction(event -> paafyldDestillat());
    }

    private void opretFad() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Oprettelse af leverandør");
        popup.setMinWidth(600); // wider, because 2 columns

        ListView<Leverandør> popupListView = new ListView<>();
        popupListView.getItems().addAll(Controller.getLeverandører());

        Label navnLabel = new Label("Indtast navn:");
        TextField navnInput = new TextField();
        Label emailLabel = new Label("Indtast email:");
        TextField emailInput = new TextField();
        Label tlfNrLabel = new Label("Indtast tlf nr:");
        TextField tlfNrInput = new TextField();
        Label adresseLabel = new Label("Indtast adresse:");
        TextField adresseInput = new TextField();

        Button btnOk = new Button("Opret");
        Button btnCancel = new Button("Annullér");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(navnLabel, 0, 0);
        grid.add(navnInput, 1, 0);

        grid.add(emailLabel, 0, 1);
        grid.add(emailInput, 1, 1);

        grid.add(tlfNrLabel, 0, 2);
        grid.add(tlfNrInput, 1, 2);

        grid.add(adresseLabel, 0, 3);
        grid.add(adresseInput, 1, 3);

        HBox buttonBox = new HBox(10, btnOk, btnCancel);
        grid.add(buttonBox, 1, 4);

        btnOk.setOnAction(e -> {
            String navn = navnInput.getText().trim();
            String email = emailInput.getText().trim();
            String tlfNr = tlfNrInput.getText().trim();
            String adresse = adresseInput.getText().trim();

            if (!navn.isEmpty() || !email.isEmpty() || !tlfNr.isEmpty() || !adresse.isEmpty()) {
                Controller.opretLeverandør(navn, email, tlfNr, adresse);
                popupListView.getItems().setAll(Controller.getLeverandører());
                leverandørListView.getItems().setAll(Controller.getLeverandører());
                popup.close();
            }
        });

        btnCancel.setOnAction(e -> popup.close());

        HBox layout = new HBox(20, popupListView, grid);
        layout.setPadding(new Insets(10));

        popup.setScene(new Scene(layout));
        popup.showAndWait();
    }

    private void paafyldDestillat() {

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


            if (!navn.isEmpty() || !email.isEmpty() || !tlfNr.isEmpty() || !adresse.isEmpty() ) {
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
    }