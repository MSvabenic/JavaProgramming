package hr.java.vjezbe.javafx;

import hr.java.vjezbe.bazaPodataka.BazaPodataka;
import hr.java.vjezbe.entitet.*;
import hr.java.vjezbe.javafx.Main;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PocetniEkranController {

    private static List<Mjesto> listaMjesta;

    @FXML
    private TextField mjestaFilterTextField;
    @FXML
    private TableView<Mjesto> mjestaTableView;
    @FXML
    private TableColumn<Mjesto, String> nazivColumn;
    @FXML
    private TableColumn<Mjesto, String> tipColumn;
    @FXML
    private TableColumn<Mjesto, String> zupanijaColumn;
    @FXML
    private Button pretraziButton;
    @FXML
    private MenuItem zupanije;


    @FXML
    public void initialize() {

        nazivColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mjesto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(
                    TableColumn.CellDataFeatures<Mjesto, String> param) {
                return new ReadOnlyObjectWrapper<String>
                        (param.getValue().getNaziv().toString());
            }});

        tipColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mjesto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(
                    TableColumn.CellDataFeatures<Mjesto, String> param) {
                return new ReadOnlyObjectWrapper<String>
                        (param.getValue().getVrstaMjesta().toString());
            }});

        zupanijaColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mjesto, String>, ObservableValue<String>>() {
            @Override
        public ObservableValue<String> call(
                TableColumn.CellDataFeatures<Mjesto, String> param) {
                return new ReadOnlyObjectWrapper<String> (param.getValue().getZupanija().getNaziv());}});
    }

    @FXML
    public void prikaziMjesta() {
        List<Mjesto> filtriranaMjesta = new ArrayList<Mjesto>();
        try{
            listaMjesta = BazaPodataka.dohvatiMjesta();
            if (mjestaFilterTextField.getText().isEmpty() == false) {
            filtriranaMjesta = listaMjesta.stream().filter(m ->
                    m.getNaziv().contains(mjestaFilterTextField.getText()))
                    .collect(Collectors.toList());
            } else {
            filtriranaMjesta = listaMjesta;
            }
        } catch (SQLException | IOException e){
            e.printStackTrace();
        }
        ObservableList<Mjesto> listaMjesta = FXCollections.observableArrayList(filtriranaMjesta);
        mjestaTableView.setItems(listaMjesta);
    }


    @FXML
    public void prikaziEkranMjesta() {
        try {
            BorderPane mjestoPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/PocetniEkran.fxml"));
            Main.setCenterPane(mjestoPane);
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
    public void prikaziEkranSenzora() {
        try {
            BorderPane postajaPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/Senzori.fxml"));
            Main.setCenterPane(postajaPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
