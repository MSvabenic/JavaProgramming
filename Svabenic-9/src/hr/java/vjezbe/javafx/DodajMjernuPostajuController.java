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

public class DodajMjernuPostajuController {

    private static final Logger logger = LoggerFactory.getLogger(DodajMjestoController.class);
    private static List<Mjesto> listaMjesta;
    private List<MjernaPostaja> listaPostaja = Main.dohvatiPostaje();

    public static List<Mjesto> listaMjesta()
    {
        try{
            listaMjesta = BazaPodataka.dohvatiMjesta();
        }catch (IOException | SQLException e){
            e.printStackTrace();
        }
        return listaMjesta;
    }


    @FXML
    private TextField nazivTextField;
    @FXML
    private TextField tockaXTextField;
    @FXML
    private TextField tockaYTextField;
    @FXML
    private ComboBox<Mjesto> mjestoComboBox;
    @FXML
    private Button spremiButton;


    public int getZadnjiId() {
        return listaPostaja.size();
    }

    public void initialize(){
        listaMjesta();
        mjestoComboBox.setValue(listaMjesta.get(0));
    }

    @FXML
    public void prikaziMjestoComboBox() {
        listaMjesta();
        ObservableList<Mjesto> listaMjestaCombo = FXCollections.observableArrayList(listaMjesta);
        mjestoComboBox.setItems(listaMjestaCombo);
    }

    public void dodajMjernuPostaju(){
        Boolean ispravniPodaci = true;
        String porukaKorisniku = "";

        String naziv = nazivTextField.getText();
        String tockaX = tockaXTextField.getText();
        String tockaY = tockaYTextField.getText();
        Mjesto mjesto = mjestoComboBox.getValue();
        File mjestaFile = new File("resources/mjernaPostaja.txt");
        int noviId = getZadnjiId() + 1;

        if(isStringEmpty(naziv)) {
            ispravniPodaci = false;
            porukaKorisniku += "Niste unijeli naziv mjerne postaje!";
        }

        if(isStringEmpty(tockaX)) {
            ispravniPodaci = false;
            porukaKorisniku += "Niste unijeli X tocku mjerne postaje!";
        }

        if(isStringEmpty(tockaY)) {
            ispravniPodaci = false;
            porukaKorisniku += "Niste unijeli Y tocku mjerne postaje!";
        }

        if(ispravniPodaci){
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Uspješno spremanje mjerne postaje!");
                alert.setHeaderText("Uspješno spremanje mjerne postaje!");
                alert.setContentText("Uneseni podaci za mjernu postaju su uspješno spremljeni.");
                alert.showAndWait();
                Stage stage = (Stage) spremiButton.getScene().getWindow();
                stage.close();
                GeografskaTocka geografskaTocka = new GeografskaTocka(new BigDecimal(tockaX), new BigDecimal(tockaY));
                MjernaPostaja mjernaPostaja = new MjernaPostaja(naziv, mjesto, geografskaTocka, null);
                BazaPodataka.spremiPostaju(mjernaPostaja);
            } catch (IOException |SQLException e) {
                logger.error("Pogreška kod spremanja podataka!", e);
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Dodavanje mjerne postaje");
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
