package hr.java.vjezbe.javafx;

import hr.java.vjezbe.entitet.Senzor;
import hr.java.vjezbe.entitet.Zupanija;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SenzoriController {


    private static List<Senzor> listaSenzora;

    @FXML
    private TextField postajeFilterTextField;
    @FXML
    private TableView<Senzor> senzorTableView;
    /*@FXML
    private TableColumn<Senzor, String> sifraColumn;*/
    @FXML
    private TableColumn<Senzor, String> jedinicaColumn;
    @FXML
    private TableColumn<Senzor, String> odstupanjeColumn;
    @FXML
    private TableColumn<Senzor, String> vrijednostColumn;
    @FXML
    private TableColumn<Senzor, String> nacinRadaColumn;
    @FXML
    private Button pretraziButton;


    @FXML
    public void initialize() {
/*
        sifraColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Senzor, String>, ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call( TableColumn.CellDataFeatures<Senzor, String> param) {
                return new ReadOnlyObjectWrapper<String>
                        (param.getValue());
            }});*/
        jedinicaColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Senzor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call( TableColumn.CellDataFeatures<Senzor, String> param) {
                return new ReadOnlyObjectWrapper<String>
                        (param.getValue().getMjernaJedinica());
            }});

        odstupanjeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Senzor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call( TableColumn.CellDataFeatures<Senzor, String> param) {
                return new ReadOnlyObjectWrapper<String>
                        (param.getValue().getPreciznost().toString());
            }});

        vrijednostColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Senzor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call( TableColumn.CellDataFeatures<Senzor, String> param) {
                return new ReadOnlyObjectWrapper<String>
                        (param.getValue().getVrijednost().toString());
            }});

        nacinRadaColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Senzor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call( TableColumn.CellDataFeatures<Senzor, String> param) {
                return new ReadOnlyObjectWrapper<String>
                        (param.getValue().getRadSenzora().toString());
            }});
    }

    @FXML
    public void prikaziSenzore() {
        listaSenzora = Main.dohvatiSenzore();
        List<Senzor> filtriraniSenzori = new ArrayList<Senzor>();
        if (postajeFilterTextField.getText().isEmpty() == false) {
            filtriraniSenzori = listaSenzora.stream().filter(m ->
                    m.getMjernaJedinica().contains(postajeFilterTextField.getText()))
                    .collect(Collectors.toList());
        } else {
            filtriraniSenzori = listaSenzora;
        }
        ObservableList<Senzor> listaSenzora = FXCollections.observableArrayList(filtriraniSenzori);
        senzorTableView.setItems(listaSenzora);
    }

    @FXML
    public void prikaziEkranSenzora() {
        try {
            BorderPane senzorPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/Senzori.fxml"));
            Main.setCenterPane(senzorPane);
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
    public void prikaziEkranPostaje() {
        try {
            BorderPane postajaPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/MjernePostaje.fxml"));
            Main.setCenterPane(postajaPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void prikaziEkranMjesta() {
        try {
            BorderPane mjestaPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/PocetniEkran.fxml"));
            Main.setCenterPane(mjestaPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void dodajNoviSenzor(Senzor noviSenzor) {
        listaSenzora = Main.dohvatiSenzore();
        listaSenzora.add(noviSenzor);
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
