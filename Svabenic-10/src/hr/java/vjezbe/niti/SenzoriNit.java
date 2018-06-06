package hr.java.vjezbe.niti;

import hr.java.vjezbe.bazaPodataka.BazaPodataka;
import hr.java.vjezbe.javafx.SenzoriController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SenzoriNit implements Runnable {
    public void run() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Integer brojSenzora = 0;
                    try {
                        brojSenzora = BazaPodataka.dohvatiNegativneSenzore();
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }
                    while (SenzoriController.isEnabled) {
                        if (brojSenzora > 0) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Senzor ispod nule!");
                            alert.setHeaderText("Upozorenje!");
                            alert.setContentText("Senzor ispod nule");
                            alert.show();
                            break;
                        }
                    }
                }
            });
        }
    }


