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

    private List<Mjesto> listaMjesta;
    private List<Zupanija> listaZupanija;

    @FXML
    private TextField nazivTextField;
    @FXML
    private ComboBox<Zupanija> zupanijaComboBox;
    @FXML
    private ComboBox<VrstaMjesta> vrstaMjestaComboBox;
    @FXML
    private Button spremiButton;


    public int getZadnjiId() {
        listaMjesta = Main.dohvatiMjesta();
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
        String naziv = nazivTextField.getText();
        Zupanija zupanija = zupanijaComboBox.getValue();
        VrstaMjesta vrstaMjesta = vrstaMjestaComboBox.getValue();
        File mjestaFile = new File("resources/mjesto.txt");
        int noviId = getZadnjiId() + 1;
        try (FileWriter writer = new FileWriter(mjestaFile, true)) {
            writer.write(noviId + "\n");
            writer.write(naziv + "\n");
            writer.write(vrstaMjesta.toString() + "\n");
            writer.write(zupanija.getNaziv() + "\n");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uspješno spremanje mjesta!");
            alert.setHeaderText("Uspješno spremanje mjesta!");
            alert.setContentText("Uneseni podaci za mjesto su uspješno spremljeni.");
                    Stage stage = (Stage) spremiButton.getScene().getWindow();
            stage.close();
            Mjesto mjesto = new Mjesto(noviId,naziv, zupanija);
            mjesto.setVrstaMjesta(vrstaMjesta);
            PocetniEkranController.dodajNovoMjesto(mjesto);
        } catch (IOException e) {
            logger.error("Pogreška kod spremanja podataka!", e);
            e.printStackTrace();
        }
    }
}
