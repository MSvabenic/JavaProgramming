package hr.java.vjezbe.javafx;

import hr.java.vjezbe.bazaPodataka.BazaPodataka;
import hr.java.vjezbe.entitet.*;
import hr.java.vjezbe.niti.SenzoriNit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    private static BorderPane root;
    private Stage primaryStage;
        @Override
        public void start(Stage stage) {
            primaryStage = stage;
            try { root = (BorderPane) FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/Senzori.fxml"));
                Scene scene = new Scene(root,800,600);
                scene.getStylesheets().add(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/application.css").toExternalForm());
                primaryStage.setScene(scene);
                primaryStage.show();
                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                SenzoriNit senzoriNit = new SenzoriNit();
                scheduler.scheduleAtFixedRate(senzoriNit, 0, 5, TimeUnit.SECONDS);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        public static void setCenterPane(BorderPane centerPane) {
            root.setCenter(centerPane);
        }

    public static void main(String[] args) {
        launch(args);
    }

    private static final String fileDrzave = "resources/drzave.txt";
    private static final String fileZupanije = "resources/zupanije.txt";
    private static final String fileMjesta = "resources/mjesto.txt";
    private static final String fileTocke = "resources/geografskaTocka.txt";
    private static final String fileSenzori = "resources/senzori.txt";
    private static final String filePostaje = "resources/mjernaPostaja.txt";

    private static Map<Integer, Drzava> drzavaDatoteka = new HashMap<>();
    private static Map<Integer, Zupanija> zupanijaDatoteka = new HashMap<>();
    private static Map<Integer, Mjesto> mjestoDatoteka = new HashMap<>();
    private static Map<Integer, GeografskaTocka> tockaDatoteka = new HashMap<>();
    private static Map<Integer, Senzor> senzorDatoteka = new HashMap<>();
    private static Map<Integer, MjernaPostaja> postajaDatoteka = new HashMap<>();


    public static List<Drzava> dohvatiDrzave(){
        List<String> lista = new ArrayList<>();
        Integer id = 0;
        String naziv = "";

        citanjeDatoteke(lista, fileDrzave);

        int brojRedovaZaDrzavu = 3;
        for(int i = 0; i < lista.size(); i++) {
            String red = lista.get(i);
            switch (i % brojRedovaZaDrzavu) {
                case 0:  id = Integer.parseInt(red);
                    break;
                case 1:  naziv = red;
                    break;
                case 2: {
                    BigDecimal povrsina = new BigDecimal(red);
                    drzavaDatoteka.put(id, new Drzava(id, naziv, povrsina));
                }
                break;
            }
        }
        List<Drzava> listaDrzava = new ArrayList<>(drzavaDatoteka.values());
        return listaDrzava;
    }

    public static List<Zupanija> dohvatiZupanije(){
        dohvatiDrzave();
        List<String> lista = new ArrayList<>();
        Integer id = 0;
        String naziv = "";
        Drzava drzava = null;

        citanjeDatoteke(lista, fileZupanije);

        int brojRedovaZaZupaniju = 3;
        for(int i = 0; i < lista.size(); i++) {
            String red = lista.get(i);
            switch (i % brojRedovaZaZupaniju) {
                case 0:  id = Integer.parseInt(red);
                    break;
                case 1:  naziv = red;
                    break;
                case 2: {
                    drzava = drzavaQuery(red);
                    zupanijaDatoteka.put(id, new Zupanija(id, naziv, drzava));
                   drzava.getZupanije().add(zupanijaDatoteka.get(id));

                }
                break;
            }
        }
        List<Zupanija> listaZupanija = new ArrayList<>(zupanijaDatoteka.values());
        return listaZupanija;
    }


    public static List<Mjesto> dohvatiMjesta(){
        dohvatiZupanije();
        List<String> lista = new ArrayList<>();
        Integer id = 0;
        String naziv = "";
        Zupanija zupanija = null;
        String vrstaMjesta;

        citanjeDatoteke(lista, fileMjesta);

        int brojRedovaZaMjesto = 4;
        for(int i = 0; i < lista.size(); i++) {
            String red = lista.get(i);
            switch (i % brojRedovaZaMjesto) {
                case 0:  id = Integer.parseInt(red);
                    break;
                case 1:  naziv = red;
                    break;
                case 2: zupanija = zupanijaQuery(red);
                    break;
                case 3: {
                    vrstaMjesta = red;
                    mjestoDatoteka.put(id, new Mjesto(id, naziv, zupanija));
                    mjestoDatoteka.get(id).setVrstaMjesta(VrstaMjesta.valueOf(vrstaMjesta));
                    zupanija.getMjesta().add(mjestoDatoteka.get(id));

                }
                break;
            }
        }
        List<Mjesto> listaMjesta = new ArrayList<>(mjestoDatoteka.values());
        return listaMjesta;
    }

    public static List<GeografskaTocka> dohvatiTocke(){
        List<String> lista = new ArrayList<>();
        Integer id = 0;
        BigDecimal x = new BigDecimal("0");
        BigDecimal y = new BigDecimal("0");

        citanjeDatoteke(lista, fileTocke);

        int brojRedovaZaTocke = 3;
        for(int i = 0; i < lista.size(); i++) {
            String red = lista.get(i);
            switch (i % brojRedovaZaTocke) {
                case 0:  id = Integer.parseInt(red);
                    break;
                case 1:  x = new BigDecimal(red);
                    break;
                case 2: {
                    y = new BigDecimal(red);
                    tockaDatoteka.put(id, new GeografskaTocka(id, x, y));
                }
                break;
            }
        }
        List<GeografskaTocka> listaTocaka = new ArrayList<>(tockaDatoteka.values());
        return listaTocaka;
    }

    public static List<Senzor> dohvatiSenzore(){
        List<String> lista = new ArrayList<>();
        Integer id = 0;
        String sifra = "";
        String mjernaJedinica = "";
        Double preciznost = 0.0;
        BigDecimal vrijednost = null;
        String nacinRada;
        MjernaPostaja mjernaPostaja = dohvatiPostaje().get(0);

        citanjeDatoteke(lista, fileSenzori);

        int brojRedovaZaSenzor = 6;
        for(int i = 0; i < lista.size(); i++) {
            String red = lista.get(i);
            switch (i % brojRedovaZaSenzor) {
                case 0:  id = Integer.parseInt(red);
                    break;
                case 1:  sifra = red;
                    break;
                case 2: mjernaJedinica = red;
                    break;
                case 3: preciznost = Double.parseDouble(red);
                    break;
                case 4: vrijednost = new BigDecimal(red);
                    break;
                case 5: {
                    nacinRada = red;
                    if(id == 1) {
                        senzorDatoteka.put(id, new SenzorGlobalnogZracenja(sifra, mjernaJedinica, preciznost, mjernaPostaja));
                        senzorDatoteka.get(id).setId(id);
                        senzorDatoteka.get(id).setVrijednost(vrijednost);
                        senzorDatoteka.get(id).setRadSenzora(RadSenzora.valueOf(nacinRada));
                    }
                    else if(id == 2){
                        senzorDatoteka.put(id, new SenzorTemperature(sifra, mjernaJedinica, preciznost, mjernaPostaja));
                        senzorDatoteka.get(id).setId(id);
                        senzorDatoteka.get(id).setVrijednost(vrijednost);
                        senzorDatoteka.get(id).setRadSenzora(RadSenzora.valueOf(nacinRada));
                    }
                    else if(id != 1 && id != 2) {
                        senzorDatoteka.put(id, new SenzorVlage(mjernaJedinica, preciznost, mjernaPostaja));
                        senzorDatoteka.get(id).setId(id);
                        senzorDatoteka.get(id).setVrijednost(vrijednost);
                        senzorDatoteka.get(id).setRadSenzora(RadSenzora.valueOf(nacinRada));
                    }
                }
                break;
            }
        }
        List<Senzor> listaSenzora = new ArrayList<>(senzorDatoteka.values());
        return listaSenzora;
    }

    public static List<MjernaPostaja> dohvatiPostaje(){
        dohvatiDrzave();
        dohvatiZupanije();
        dohvatiMjesta();

        List<String> lista = new ArrayList<>();
        Integer id = 0;
        String naziv = "";
        Mjesto mjesto = null;
        BigDecimal x = null;
        BigDecimal y = null;
        Integer visina = 0;
        List<Senzor> senzor = null;

        citanjeDatoteke(lista, filePostaje);

        int brojRedovaZaPostaje = 7;
        for(int i = 0; i < lista.size(); i++) {
            GeografskaTocka geografskaTocka = new GeografskaTocka(x,y);
            String red = lista.get(i);
            switch (i % brojRedovaZaPostaje) {
                case 0:  id = Integer.parseInt(red);
                    break;
                case 1:  naziv = red;
                    break;
                case 2: mjesto = mjestoQuery(red);
                    break;
                case 3: x = new BigDecimal(red);
                    geografskaTocka.setX(x);
                    break;
                case 4: y = new BigDecimal(red);
                    geografskaTocka.setY(y);
                    break;
                case 5: senzor = new ArrayList<Senzor>(senzorDatoteka.values());
                    break;
                case 6: {
                    postajaDatoteka.put(id, new MjernaPostaja(naziv, mjesto, geografskaTocka, senzor));
                    if(id == 3)
                    {
                        visina = Integer.parseInt(red);
                        postajaDatoteka.put(id, new RadioSondaznaMjernaPostaja(visina, naziv, mjesto, geografskaTocka, senzor));
                    }
                    mjesto.getMjernePostaje().add(postajaDatoteka.get(id));

                }
                break;
            }
        }
        List<MjernaPostaja> listaPostaja = new ArrayList<>(postajaDatoteka.values());
        return listaPostaja;
    }

    public static void citanjeDatoteke(List<String> listaEntiteta, String file){
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = in.readLine()) != null) {
                listaEntiteta.add(line);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    //hackovi za override dodavanja, pogledati pripadajuće metode
    public static Zupanija zupanijaQuery(String naziv)
    {
        Zupanija zupanija = null;
        for (Zupanija zupanije:dohvatiZupanije()) {
            if(naziv.equals(zupanije.getNaziv()))
            {
                zupanija = zupanije;
            }
        }
        return zupanija;
    }

    public static Drzava drzavaQuery(String naziv)
    {
        Drzava drzava = null;
        for (Drzava drzave:dohvatiDrzave()) {
            if(naziv.equals(drzave.getNaziv()))
            {
                drzava = drzave;
            }
        }
        return drzava;
    }

    public static Mjesto mjestoQuery(String naziv)
    {
        Mjesto mjesto = null;
        for (Mjesto mjesta:dohvatiMjesta()) {
            if(naziv.equals(mjesta.getNaziv()))
            {
                mjesto = mjesta;
            }
        }
        return mjesto;
    }
}
