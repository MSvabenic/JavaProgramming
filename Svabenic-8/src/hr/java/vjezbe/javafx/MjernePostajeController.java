package hr.java.vjezbe.javafx;

import hr.java.vjezbe.entitet.MjernaPostaja;
import hr.java.vjezbe.entitet.Mjesto;
import hr.java.vjezbe.entitet.Zupanija;
import hr.java.vjezbe.javafx.Main;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MjernePostajeController {
    private static List<MjernaPostaja> listaPostaja;

    @FXML
    private TextField postajeFilterTextField;
    @FXML
    private TableView<MjernaPostaja> postajaTableView;
    @FXML
    private TableColumn<MjernaPostaja, String> nazivColumn;
    @FXML
    private TableColumn<MjernaPostaja, String> mjestoColumn;
    @FXML
    private TableColumn<MjernaPostaja, String> tockaColumn;
    @FXML
    private Button pretraziButton;



    @FXML
    public void initialize() {

        nazivColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MjernaPostaja, String>, ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call( TableColumn.CellDataFeatures<MjernaPostaja, String> param) {
                return new ReadOnlyObjectWrapper<String>
                        (param.getValue().getNaziv().toString());
            }});

        mjestoColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MjernaPostaja, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call( TableColumn.CellDataFeatures<MjernaPostaja, String> param) {
                return new ReadOnlyObjectWrapper<String>
                        (param.getValue().getMjesto().getNaziv().toString());
            }});

        tockaColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MjernaPostaja, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call( TableColumn.CellDataFeatures<MjernaPostaja, String> param) {
                return new ReadOnlyObjectWrapper<String>
                        (param.getValue().getGeografskaTocka().getX().toString() + " " + param.getValue().getGeografskaTocka().getY().toString());
            }});
    }

    @FXML
    public void prikaziPostaje() {
        listaPostaja = Main.dohvatiPostaje();
        List<MjernaPostaja> filtriranePostaje = new ArrayList<MjernaPostaja>();
        if (postajeFilterTextField.getText().isEmpty() == false) {
            filtriranePostaje = listaPostaja.stream().filter(m ->
                    m.getNaziv().contains(postajeFilterTextField.getText()))
                    .collect(Collectors.toList());
        } else {
            filtriranePostaje = listaPostaja;
        }
        ObservableList<MjernaPostaja> listaPostaja = FXCollections.observableArrayList(filtriranePostaje);
        postajaTableView.setItems(listaPostaja);
    }

    @FXML
    public void prikaziEkranMjernePostaje() {
        try {
            BorderPane postajaPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/MjernePostaje.fxml"));
            Main.setCenterPane(postajaPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void prikaziEkranDrzave() {
        try {
            BorderPane drzavePane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/Drzave.fxml"));
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
    public void prikaziEkranMjesta() {
        try {
            BorderPane postajaPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/PocetniEkran.fxml"));
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

    public static void dodajNovuPostaju(MjernaPostaja novaPostaja) {
        listaPostaja = Main.dohvatiPostaje();
        listaPostaja.add(novaPostaja);
    }

    public void prikaziEkranZaNovoMjesto() {
        try {
            BorderPane novoMjestoPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/DodajMjesto.fxml"));
            Scene scene = new Scene(novoMjestoPane,600,400);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/application.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prikaziEkranZaNovuZupaniju() {
        try {
            BorderPane novaZupanijaPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/DodajZupaniju.fxml"));
            Scene scene = new Scene(novaZupanijaPane,600,400);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/application.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prikaziEkranZaNovuDrzavu() {
        try {
            BorderPane novaDrzavaPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/DodajDrzavu.fxml"));
            Scene scene = new Scene(novaDrzavaPane,600,400);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/application.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prikaziEkranZaNovuPostaju() {
        try {
            BorderPane novaPostajaPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/DodajMjernuPostaju.fxml"));
            Scene scene = new Scene(novaPostajaPane,600,400);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/application.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prikaziEkranZaNoviSenzor() {
        try {
            BorderPane noviSenzorPane= FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/DodajSenzor.fxml"));
            Scene scene = new Scene(noviSenzorPane,600,400);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/application.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
