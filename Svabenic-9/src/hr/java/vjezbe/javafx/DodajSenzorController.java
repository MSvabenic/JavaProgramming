package hr.java.vjezbe.javafx;

import hr.java.vjezbe.bazaPodataka.BazaPodataka;
import hr.java.vjezbe.entitet.*;
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
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class DodajSenzorController {

    private static final Logger logger = LoggerFactory.getLogger(DodajMjestoController.class);
    private List<Mjesto> listaMjesta = Main.dohvatiMjesta();
    private List<Senzor> listaSenzora = Main.dohvatiSenzore();
    private static List<MjernaPostaja> listaPostaja;

    private static List<MjernaPostaja> listaPostaja(){
        try{
            listaPostaja = BazaPodataka.dohvatiPostaje();
        }catch (IOException | SQLException e){
            e.printStackTrace();
        }
        return listaPostaja;
    }
    @FXML
    private TextField jedinicaTextField;
    @FXML
    private TextField preciznostTextField;
    @FXML
    private TextField vrijednostTextField;
    @FXML
    private ComboBox<RadSenzora> radComboBox;
    @FXML
    private ComboBox<MjernaPostaja> postajaComboBox;
    @FXML
    private TextField komponentaTextField;
    @FXML
    private Button spremiButton;


    public void initialize(){
        listaPostaja();
        radComboBox.setValue(RadSenzora.PING);
        postajaComboBox.setValue(listaPostaja.get(0));
    }

    public int getZadnjiId() {
        return listaSenzora.size();
    }

    @FXML
    public void prikaziRadComboBox() {
        ObservableList<RadSenzora> vrstaRadaSenzora = FXCollections.observableArrayList(RadSenzora.values());
        radComboBox.setItems(vrstaRadaSenzora);
    }

    @FXML
    public void prikaziPostajeComboBox() {
        listaPostaja();
        ObservableList<MjernaPostaja> mjernaPostaja = FXCollections.observableArrayList(listaPostaja);
        postajaComboBox.setItems(mjernaPostaja);
    }

    public void dodajSenzor(){
        Boolean ispravniPodaci = true;
        String porukaKorisniku = "";

        String jedinica = jedinicaTextField.getText();
        String preciznost = preciznostTextField.getText();
        String vrijednost = vrijednostTextField.getText();
        RadSenzora radSenzora = radComboBox.getValue();
        MjernaPostaja mjernaPostaja = postajaComboBox.getValue();
        String komponenta = komponentaTextField.getText();
        int noviId = getZadnjiId() + 1;

        if(isStringEmpty(jedinica)) {
            ispravniPodaci = false;
            porukaKorisniku += "Niste unijeli jedinicu mjerenja!";
        }


        if(isStringEmpty(komponenta)) {
            ispravniPodaci = false;
            porukaKorisniku += "Niste unijeli jedinicu mjerenja!";
        }

        if(isStringEmpty(preciznost)) {
            ispravniPodaci = false;
            porukaKorisniku += "Niste unijeli preciznost mjerenja!";
        }

        if(isStringEmpty(vrijednost)) {
            ispravniPodaci = false;
            porukaKorisniku += "Niste unijeli vrijednost senzora!";
        }

        if(isDigitsOnly(preciznost)){
            ispravniPodaci = false;
            porukaKorisniku += "Smiju se unijeti samo brojčane vrijednosti za preciznost!";
        }

        if(isDigitsOnly(vrijednost)){
            ispravniPodaci = false;
            porukaKorisniku += "Smiju se unijeti samo brojčane vrijednosti za vrijednost!";
        }

        if(ispravniPodaci){
            try  {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Uspješno spremanje senzora!");
                alert.setHeaderText("Uspješno spremanje senzora!");
                alert.setContentText("Uneseni podaci za senzor su uspješno spremljeni.");
                alert.showAndWait();
                Stage stage = (Stage) spremiButton.getScene().getWindow();
                stage.close();
                SenzorTemperature senzor = new SenzorTemperature(komponenta, jedinica, Double.parseDouble(preciznost), mjernaPostaja);
                senzor.setVrijednost(new BigDecimal(vrijednost));
                senzor.setRadSenzora(radSenzora);
                BazaPodataka.spremiSenzor(senzor);
            } catch (IOException | SQLException e) {
                logger.error("Pogreška kod spremanja podataka!", e);
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Dodavanje senzora");
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

    private boolean isDigitsOnly(String tekst){
        Boolean isDigit = false;

        if(tekst.matches("[^0-9]+")){
            isDigit = true;
        }
        return isDigit;
    }
}
