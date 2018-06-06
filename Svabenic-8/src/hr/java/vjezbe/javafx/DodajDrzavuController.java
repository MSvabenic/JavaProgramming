package hr.java.vjezbe.javafx;

import hr.java.vjezbe.entitet.Drzava;
import hr.java.vjezbe.entitet.Mjesto;
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
import java.math.BigDecimal;
import java.util.List;

public class DodajDrzavuController {

    private static final Logger logger = LoggerFactory.getLogger(DodajMjestoController.class);
    private List<Mjesto> listaMjesta = Main.dohvatiMjesta();
    private List<Drzava> listaDrzava = Main.dohvatiDrzave();

    @FXML
    private TextField nazivTextField;
    @FXML
    private TextField povrsinaTextField;
    @FXML
    private Button spremiButton;


    public int getZadnjiId() {
        return listaDrzava.size();
    }


    public void dodajDrzavu(){
        Boolean ispravniPodaci = true;
        String porukaKorisniku = "";

        String naziv = nazivTextField.getText();
        String povrsina = povrsinaTextField.getText();
        File drzaveFile = new File("resources/drzave.txt");
        int noviId = getZadnjiId() + 1;

        if(isStringEmpty(naziv)) {
            ispravniPodaci = false;
            porukaKorisniku += "Niste unijeli naziv drzave!";
        }

        if(isStringEmpty(povrsina)) {
            ispravniPodaci = false;
            porukaKorisniku += "Niste unijeli povrsinu drzave!";
        }

        if(isDigitsOnly(povrsina)){
            ispravniPodaci = false;
            porukaKorisniku += "Vrijednost površine mora biti brojčana!";
        }

        if(ispravniPodaci){
            try (FileWriter writer = new FileWriter(drzaveFile, true)) {
                writer.write("\n" + noviId + "\n");
                writer.write(naziv + "\n");
                writer.write(povrsina);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Uspješno spremanje države!");
                alert.setHeaderText("Uspješno spremanje države!");
                alert.setContentText("Uneseni podaci za državu su uspješno spremljeni.");
                alert.showAndWait();
                Stage stage = (Stage) spremiButton.getScene().getWindow();
                stage.close();
                Drzava drzava = new Drzava(noviId, naziv, new BigDecimal(povrsina));
                DrzaveController.dodajNovuDrzavu(drzava);
            } catch (IOException e) {
                logger.error("Pogreška kod spremanja podataka!", e);
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Dodavanje države");
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
