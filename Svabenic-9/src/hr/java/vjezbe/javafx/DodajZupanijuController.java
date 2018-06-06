package hr.java.vjezbe.javafx;

import hr.java.vjezbe.bazaPodataka.BazaPodataka;
import hr.java.vjezbe.entitet.Drzava;
import hr.java.vjezbe.entitet.Mjesto;
import hr.java.vjezbe.entitet.VrstaMjesta;
import hr.java.vjezbe.entitet.Zupanija;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DodajZupanijuController {

    private static final Logger logger = LoggerFactory.getLogger(DodajMjestoController.class);
    private List<Mjesto> listaMjesta = Main.dohvatiMjesta();
    private static List<Drzava> listaDrzava;
    private List<Zupanija> listaZupanija = Main.dohvatiZupanije();

    private static List<Drzava> listaDrzava(){
        try {
            listaDrzava = BazaPodataka.dohvatiDrzave();
        } catch (IOException | SQLException e){
            e.printStackTrace();
        }
        return listaDrzava;
    }

    @FXML
    private TextField nazivTextField;
    @FXML
    private ComboBox<Drzava> drzavaComboBox;
    @FXML
    private Button spremiButton;


    public void initialize(){
        listaDrzava();
        drzavaComboBox.setValue(listaDrzava.get(0));
    }

    public int getZadnjiId() {
        return listaZupanija.size();
    }

    @FXML
    public void prikaziDrzaveComboBox() {
        listaDrzava();
        ObservableList<Drzava> listaDrzavaCombo = FXCollections.observableArrayList(listaDrzava);
        drzavaComboBox.setItems(listaDrzavaCombo);
    }

    public void dodajZupaniju(){
        Boolean ispravniPodaci = true;
        String porukaKorisniku = "";

        String naziv = nazivTextField.getText();
        Drzava drzava = drzavaComboBox.getValue();
        File zupanijeFile = new File("resources/zupanije.txt");
        int noviId = getZadnjiId() + 1;

        if(isStringEmpty(naziv)) {
            ispravniPodaci = false;
            porukaKorisniku += "Niste unijeli naziv zupanije!";
        }

        if(ispravniPodaci){
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Uspješno spremanje zupanije!");
                alert.setHeaderText("Uspješno spremanje zupanije!");
                alert.setContentText("Uneseni podaci za zupaniju su uspješno spremljeni.");
                alert.showAndWait();
                Stage stage = (Stage) spremiButton.getScene().getWindow();
                stage.close();
                Zupanija zupanija = new Zupanija(noviId, naziv, drzava);
                BazaPodataka.spremiZupaniju(zupanija);
            } catch (IOException | SQLException e) {
                logger.error("Pogreška kod spremanja podataka!", e);
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Dodavanje zupanije");
            alert.setHeaderText("Pogreške u podacima");
            alert.setContentText(porukaKorisniku);

            alert.showAndWait();
        }


    }

    private boolean isStringEmpty(String tekst){
        Boolean isEmpty = false;

        if (tekst == null || "".equals(tekst)) {
            isEmpty = true;
        }

        return isEmpty;
    }
}