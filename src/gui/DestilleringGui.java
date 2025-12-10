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
import model.Destillering;
import model.Malt;
import model.Medarbejder;

import java.time.LocalDate;

public class DestilleringGui extends GridPane {
    private final ListView<Destillering> destilleringListView = new ListView<>();
    private final ListView<Destillat> destillatListView = new ListView<>();
    private final Button btnOpretDestillering = new Button("Opret destillering");
    private final Button btnFærdiggørDestillering = new Button("Færdiggør destillering");

    public DestilleringGui() {
        initContent(this);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(5);

        Label lblOversigtDestilleringer = new Label("Oversigt over igangværende destilleringer:");
        pane.add(lblOversigtDestilleringer, 0, 0, 1, 1);
        destilleringListView.getItems().setAll(Controller.getDestilleringer());
        pane.add(destilleringListView, 0, 1);
        destilleringListView.setPrefSize(300, 600);

        Label lblUdfyldNedenstående = new Label("Destillater:");
        pane.add(lblUdfyldNedenstående, 1, 0);
        destillatListView.getItems().setAll(Controller.getDestillater());
        pane.add(destillatListView, 1, 1);

        pane.add(btnOpretDestillering, 0, 5);
        pane.add(btnFærdiggørDestillering, 0, 6);

        btnOpretDestillering.setOnAction(event -> opretDestillering());
        btnFærdiggørDestillering.setOnAction(event -> færdiggørDestillering());
        btnFærdiggørDestillering.disableProperty().bind(destilleringListView.getSelectionModel().selectedItemProperty().isNull());
    }

    private void opretDestillering() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Oprettelse af destillering");
        popup.setMinWidth(300);

        ListView<Malt> alleMalt = new ListView<>();
        alleMalt.getItems().setAll(Controller.getAlleMalt());
        ListView<Medarbejder> alleMedarbejdere = new ListView<>();
        alleMedarbejdere.getItems().setAll(Controller.getMedarbejdere());

        Label IdLabel = new Label("Indtast destilleringsId:");
        TextField IdInput = new TextField();

        Label mængdeLabel = new Label("Indtast mængde:");
        TextField mængdeInput = new TextField();

        Label startDatolbl = new Label("Indtast startdato:");
        TextField startDatoInput = new TextField();

        Label rygemateriellbl = new Label("Indtast evt. rygemateriale");
        TextField rygematerielInput = new TextField();

        Button btnOk = new Button("Opret");
        btnOk.disableProperty().bind(alleMalt.getSelectionModel().selectedItemProperty().isNull());
        btnOk.disableProperty().bind(alleMedarbejdere.getSelectionModel().selectedItemProperty().isNull());

        btnOk.setOnAction(e -> {
            int id = Integer.parseInt(IdInput.getText().trim());
            double mængde = Double.parseDouble(mængdeInput.getText().trim());
            LocalDate startDato = LocalDate.parse(startDatoInput.getText().trim());
            String rygemateriel = rygematerielInput.getText().trim();

            if (id > 0 && mængde > 0) {
                Controller.opretDestillering(id, mængde, startDato, null, rygemateriel, alleMalt
                        .getSelectionModel().getSelectedItem(),
                        alleMedarbejdere.getSelectionModel().getSelectedItem());
                destilleringListView.getItems().setAll(Controller.getDestilleringer());
                popup.close();
            }
        });

        Button btnCancel = new Button("Annullér");
        btnCancel.setOnAction(e -> popup.close());

        VBox maltBox = new VBox(5, new Label("Vælg malt"), alleMalt);
        VBox medarbejderBox = new VBox(5, new Label("Vælg medarbejder"), alleMedarbejdere);
        HBox listViews = new HBox(10, maltBox, medarbejderBox);
        HBox okAnnuler = new HBox(10, btnOk, btnCancel);
        VBox right = new VBox(10,
                IdLabel, IdInput,
                mængdeLabel, mængdeInput,
                startDatolbl, startDatoInput,
                rygemateriellbl, rygematerielInput,
                okAnnuler
        );

        HBox layout = new HBox(20, right, listViews);
        layout.setPadding(new Insets(10));

        popup.setScene(new Scene(layout));
        popup.showAndWait();
    }

    private void færdiggørDestillering() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Færdiggørelse af destillering");
        popup.setMinWidth(300);

        Label mængdeLbl = new Label("Indtast destillat mængde:");
        TextField txfMængde = new TextField();
        Label alkoholprocentLbl = new Label("Indtast alkoholprocent:");
        TextField txfAlkoholprocent = new TextField();
        Button btnFærdiggør = new Button("Færdiggør");


        btnFærdiggør.setOnAction(event -> {
            double mængde = Double.parseDouble(txfMængde.getText().trim());
            double alkoholprocent = Double.parseDouble(txfAlkoholprocent.getText().trim());
            Destillering destillering = destilleringListView.getSelectionModel().getSelectedItem();
            Controller.opretDestillat(destillering.getDestilleringsId(), mængde, destillering, alkoholprocent);
            destillatListView.getItems().setAll(Controller.getDestillater());
            destilleringListView.getItems().setAll(Controller.getDestilleringer());
            popup.close();
        });
        VBox layout = new VBox(10, mængdeLbl, txfMængde,
                alkoholprocentLbl, txfAlkoholprocent,
                btnFærdiggør);
        layout.setPadding(new Insets(10));
        popup.setScene(new Scene(layout));
        popup.showAndWait();

    }
}