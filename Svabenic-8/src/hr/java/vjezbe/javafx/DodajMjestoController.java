package hr.java.vjezbe.javafx;

import hr.java.vjezbe.entitet.Mjesto;
import hr.java.vjezbe.entitet.VrstaMjesta;
import hr.java.vjezbe.entitet.Zupanija;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DodajMjestoController {

    private static final Logger logger = LoggerFactory.getLogger(DodajMjestoController.class);
    private List<Mjesto> listaMjesta = Main.dohvatiMjesta();
    private List<Zupanija> listaZupanija = Main.dohvatiZupanije();

    @FXML
    private TextField nazivTextField;
    @FXML
    private ComboBox<Zupanija> zupanijaComboBox;
    @FXML
    private ComboBox<VrstaMjesta> vrstaMjestaComboBox;
    @FXML
    private Button spremiButton;

    public void initialize(){
        zupanijaComboBox.setValue(listaZupanija.get(0));
        vrstaMjestaComboBox.setValue(VrstaMjesta.GRAD);
    }

    public int getZadnjiId() {
        return listaMjesta.size();
    }

    @FXML
    public void prikaziZupanijeComboBox() {
        ObservableList<Zupanija> listaZupanijaCombo = FXCollections.observableArrayList(listaZupanija);
        zupanijaComboBox.setItems(listaZupanijaCombo);
    }

    @FXML
    public void prikaziVrstaMjestaComboBox() {
        ObservableList<VrstaMjesta> listaVrstaMjesta = FXCollections.observableArrayList(VrstaMjesta.values());
        vrstaMjestaComboBox.setItems(listaVrstaMjesta);
    }

    public void dodajMjesto(){
        Boolean ispravniPodaci = true;
        String porukaKorisniku = "";

        String naziv = nazivTextField.getText();
        Zupanija zupanija = zupanijaComboBox.getValue();
        VrstaMjesta vrstaMjesta = vrstaMjestaComboBox.getValue();
        File mjestaFile = new File("resources/mjesto.txt");
        int noviId = getZadnjiId() + 1;

        if(isStringEmpty(naziv)) {
            ispravniPodaci = false;
            porukaKorisniku += "Niste unijeli naziv mjesta!";
        }

        if(ispravniPodaci){
            try (FileWriter writer = new FileWriter(mjestaFile, true)) {
                writer.write("\n" + noviId + "\n");
                writer.write(naziv + "\n");
                writer.write(zupanija.getNaziv() + "\n");
                writer.write(vrstaMjesta.toString());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Uspješno spremanje mjesta!");
                alert.setHeaderText("Uspješno spremanje mjesta!");
                alert.setContentText("Uneseni podaci za mjesto su uspješno spremljeni.");
                alert.showAndWait();
                Stage stage = (Stage) spremiButton.getScene().getWindow();
                stage.close();
                Mjesto mjesto = new Mjesto(noviId, naziv, zupanija);
                mjesto.setVrstaMjesta(vrstaMjesta);
                PocetniEkranController.dodajNovoMjesto(mjesto);
            } catch (IOException e) {
                logger.error("Pogreška kod spremanja podataka!", e);
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Dodavanje mjesta");
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
