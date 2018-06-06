package hr.java.vjezbe.javafx;

import hr.java.vjezbe.entitet.Drzava;
import hr.java.vjezbe.entitet.Mjesto;
import hr.java.vjezbe.javafx.Main;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DrzaveController {
    private List<Drzava> listaDrzava;

    @FXML
    private TextField drzaveFilterTextField;
    @FXML
    private TableView<Drzava> drzavaTableView;
    @FXML
    private TableColumn<Drzava, String> nazivColumn;
    @FXML
    private TableColumn<Drzava, String> povrsinaColumn;
    @FXML
    private Button pretraziButton;
    @FXML
    private MenuItem zupanije;


    @FXML
    public void initialize() {

        nazivColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Drzava, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(
                    TableColumn.CellDataFeatures<Drzava, String> param) {
                return new ReadOnlyObjectWrapper<String>
                        (param.getValue().getNaziv().toString());
            }});

        povrsinaColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Drzava, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(
                    TableColumn.CellDataFeatures<Drzava, String> param) {
                return new ReadOnlyObjectWrapper<String>
                        (param.getValue().getPovrsina().toString());
            }});
    }

    @FXML
    public void prikaziDrzave() {
        listaDrzava = Main.dohvatiDrzave();
        List<Drzava> filtriraneDrzave = new ArrayList<Drzava>();
        if (drzaveFilterTextField.getText().isEmpty() == false) {
            filtriraneDrzave = listaDrzava.stream().filter(m ->
                    m.getNaziv().contains(drzaveFilterTextField.getText()))
                    .collect(Collectors.toList());
        } else {
            filtriraneDrzave = listaDrzava;
        }
        ObservableList<Drzava> listaDrzava = FXCollections.observableArrayList(filtriraneDrzave);
        drzavaTableView.setItems(listaDrzava);
    }

    @FXML
    public void prikaziEkranDrzave() {
        try {
            BorderPane drzavaPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/Drzave.fxml"));
            Main.setCenterPane(drzavaPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void prikaziEkranMjesta() {
        try {
            BorderPane drzavePane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/PocetniEkran.fxml"));
            Main.setCenterPane(drzavePane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void prikaziEkranZupanije() {
        try {
            BorderPane zupanijaPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/Zupanije.fxml"));
            Main.setCenterPane(zupanijaPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void prikaziEkranPostaje() {
        try {
            BorderPane postajaPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/MjernePostaje.fxml"));
            Main.setCenterPane(postajaPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void prikaziEkranSenzora() {
        try {
            BorderPane postajaPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/Senzori.fxml"));
            Main.setCenterPane(postajaPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
