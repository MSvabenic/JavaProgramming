package hr.java.vjezbe.bazaPodataka;

import hr.java.vjezbe.entitet.*;
import hr.java.vjezbe.javafx.DodajDrzavuController;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BazaPodataka {
    private static final String DATABASE_FILE = "bazaPodataka.properties";

    private static Connection spajanjeNaBazuPodataka() throws SQLException, IOException{
        String urlBazePodataka = "";
        String korisnickoIme = "";
        String lozinka = "";

        Properties svojstva = new Properties();
        try{
            svojstva.load(new FileReader(DATABASE_FILE));
            urlBazePodataka = svojstva.getProperty("bazaPodatakaUrl");
            korisnickoIme = svojstva.getProperty("korisnickoIme");
            lozinka = svojstva.getProperty("lozinka");
        } catch (IOException e){
            e.printStackTrace();
        }

        Connection veza = null;
        try {
            veza = DriverManager.getConnection(urlBazePodataka,korisnickoIme,lozinka);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veza;
    }

    private static void  zatvaranjeVezeNaBazuPodataka(Connection veza) {
        String urlBazePodataka = "";
        String korisnickoIme = "";
        String lozinka = "";

        Properties svojstva = new Properties();
        try{
            svojstva.load(new FileReader(DATABASE_FILE));
            urlBazePodataka = svojstva.getProperty("bazaPodatakaUrl");
            korisnickoIme = svojstva.getProperty("korisnickoIme");
            lozinka = svojstva.getProperty("lozinka");
        } catch (IOException e){
            e.printStackTrace();
        }

        try {
            veza = DriverManager.getConnection(urlBazePodataka,korisnickoIme,lozinka);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                veza.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void spremiDrzavu(Drzava drzava) throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        veza.setAutoCommit(true);
        try {
            PreparedStatement insertDrzava = veza.prepareStatement("INSERT INTO POSTAJE.DRZAVA(NAZIV, POVRSINA) VALUES (?, ?);");
            insertDrzava.setString(1, drzava.getNaziv());
            insertDrzava.setBigDecimal(2, drzava.getPovrsina());
            insertDrzava.executeUpdate();
        }
        catch(Throwable ex) {
            veza.rollback();
            throw ex;
        }
        zatvaranjeVezeNaBazuPodataka(veza);
    }

    public static List<Drzava> dohvatiDrzave() throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        Statement statementDrzave = veza.createStatement();
        ResultSet resultSetDrzave =
                statementDrzave.executeQuery("SELECT * FROM POSTAJE.DRZAVA");
        List< Drzava > listaDrzava = new ArrayList<>();
        while (resultSetDrzave.next()) {
            Integer drzavaId = resultSetDrzave.getInt("ID");
            String naziv = resultSetDrzave.getString("NAZIV");
            BigDecimal povrsina = resultSetDrzave.getBigDecimal("POVRSINA");
            Drzava drzava = new Drzava(naziv, povrsina);
            drzava.setId(drzavaId);
            listaDrzava.add(drzava);
        }
        zatvaranjeVezeNaBazuPodataka(veza);
        return listaDrzava;
    }

    public static void spremiZupaniju(Zupanija zupanija) throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        veza.setAutoCommit(true);
        try {
            PreparedStatement insertZupanija = veza.prepareStatement("INSERT INTO POSTAJE.ZUPANIJA(NAZIV, DRZAVA_ID) VALUES (?, ?);");
            insertZupanija.setString(1, zupanija.getNaziv());
            insertZupanija.setInt(2, zupanija.getDrzava().getId());
            insertZupanija.executeUpdate();
        }
        catch(Throwable ex) {
            veza.rollback();
            throw ex;
        }
        zatvaranjeVezeNaBazuPodataka(veza);
    }

    public static List<Zupanija> dohvatiZupanije() throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        Statement statementZupanije = veza.createStatement();
        ResultSet resultSetZupanije = statementZupanije.executeQuery("SELECT zu.ID, zu.NAZIV , dr.NAZIV, dr.POVRSINA  FROM POSTAJE.ZUPANIJA zu INNER JOIN POSTAJE.DRZAVA dr WHERE dr.ID = zu.DRZAVA_ID");
        List< Zupanija > listaZupanija = new ArrayList<>();
        while (resultSetZupanije.next()) {
            Integer zupanijaId = resultSetZupanije.getInt("ZUPANIJA.ID");
            String naziv = resultSetZupanije.getString("ZUPANIJA.NAZIV");
            String nazivDrzave = resultSetZupanije.getString("DRZAVA.NAZIV");
            BigDecimal povrsinaDrzave = resultSetZupanije.getBigDecimal("DRZAVA.POVRSINA");
            Drzava drzava = new Drzava(nazivDrzave, povrsinaDrzave);
            Zupanija zupanija = new Zupanija(naziv, drzava);
            zupanija.setId(zupanijaId);
            listaZupanija.add(zupanija);
        }
        zatvaranjeVezeNaBazuPodataka(veza);
        return listaZupanija;
    }

    public static void spremiMjesto(Mjesto mjesto) throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        veza.setAutoCommit(true);
        try {
            PreparedStatement insertMjesto = veza.prepareStatement("INSERT INTO POSTAJE.MJESTO(NAZIV, VRSTA, ZUPANIJA_ID) VALUES (?, ?, ?);");
            insertMjesto.setString(1, mjesto.getNaziv());
            insertMjesto.setString(2, mjesto.getVrstaMjesta().toString());
            insertMjesto.setInt(3, mjesto.getZupanija().getId());
            insertMjesto.executeUpdate();
        }
        catch(Throwable ex) {
            veza.rollback();
            throw ex;
        }
        zatvaranjeVezeNaBazuPodataka(veza);
    }

    public static List<Mjesto> dohvatiMjesta() throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        Statement statementMjesta= veza.createStatement();
        ResultSet resultSetMjesta=
                statementMjesta.executeQuery("SELECT mj.ID, mj.NAZIV , mj.VRSTA, zu.NAZIV FROM POSTAJE.MJESTO mj INNER JOIN POSTAJE.ZUPANIJA zu WHERE zu.ID = mj.ZUPANIJA_ID");
        List<Mjesto> listaMjesta = new ArrayList<>();
        while (resultSetMjesta.next()) {
            Integer mjestoId = resultSetMjesta.getInt("MJESTO.ID");
            String naziv = resultSetMjesta.getString("MJESTO.NAZIV");
            String vrstaMjesta = resultSetMjesta.getString("MJESTO.VRSTA");
            String nazivZupanije = resultSetMjesta.getString("ZUPANIJA.NAZIV");
            Zupanija zupanija = new Zupanija(nazivZupanije, null);
            Mjesto mjesto = new Mjesto(naziv,zupanija);
            mjesto.setId(mjestoId);
            mjesto.setVrstaMjesta(VrstaMjesta.valueOf(vrstaMjesta));
            listaMjesta.add(mjesto);
        }
        zatvaranjeVezeNaBazuPodataka(veza);
        return listaMjesta;
    }

    public static void spremiPostaju(MjernaPostaja postaja) throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        veza.setAutoCommit(true);
        try {
            PreparedStatement insertPostaja = veza.prepareStatement("INSERT INTO POSTAJE.MJERNA_POSTAJA(NAZIV, MJESTO_ID, LAT, LNG) VALUES (?, ?, ?, ?);");
            insertPostaja.setString(1, postaja.getNaziv());
            insertPostaja.setInt(2, postaja.getMjesto().getId());
            insertPostaja.setBigDecimal(3, postaja.getGeografskaTocka().getX());
            insertPostaja.setBigDecimal(4, postaja.getGeografskaTocka().getY());
            insertPostaja.executeUpdate();
        }
        catch(Throwable ex) {
            veza.rollback();
            throw ex;
        }
        zatvaranjeVezeNaBazuPodataka(veza);
    }

    public static List<MjernaPostaja> dohvatiPostaje() throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        Statement statementPostaja = veza.createStatement();
        ResultSet resultSetPostaja =
                statementPostaja.executeQuery("SELECT mj.NAZIV, mp.ID, mp.NAZIV, mp.LAT, mp.LNG FROM POSTAJE.MJERNA_POSTAJA mp INNER JOIN POSTAJE.MJESTO mj WHERE mj.ID = mp.MJESTO_ID");
        List<MjernaPostaja> listaPostaja = new ArrayList<>();
        while (resultSetPostaja.next()) {
            Integer postajaId = resultSetPostaja.getInt("MJERNA_POSTAJA.ID");
            String naziv = resultSetPostaja.getString("MJERNA_POSTAJA.NAZIV");
            BigDecimal tockaX = resultSetPostaja.getBigDecimal("MJERNA_POSTAJA.LAT");
            BigDecimal tockaY = resultSetPostaja.getBigDecimal("MJERNA_POSTAJA.LNG");
            String nazivMjesta = resultSetPostaja.getString("MJESTO.NAZIV");
            Mjesto mjesto = new Mjesto(nazivMjesta, null);
            MjernaPostaja postaja = new MjernaPostaja(naziv, mjesto, new GeografskaTocka(tockaX,tockaY),null);
            postaja.setId(postajaId);
            listaPostaja.add(postaja);
        }
        zatvaranjeVezeNaBazuPodataka(veza);
        return listaPostaja;
    }

    public static void spremiSenzor(SenzorTemperature senzor) throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        List<Senzor> senzori = dohvatiSenzore();
        veza.setAutoCommit(true);
        try {
            PreparedStatement insertSenzor = veza.prepareStatement("INSERT INTO POSTAJE.SENZOR(MJERNA_JEDINICA, PRECIZNOST, VRIJEDNOST, RAD_SENZORA, MJERNA_POSTAJA_ID) VALUES (?, ?, ?, ?, ?);");
            insertSenzor.setString(1, senzor.getMjernaJedinica());
            insertSenzor.setDouble(2, senzor.getPreciznost());
            insertSenzor.setBigDecimal(3, senzor.getVrijednost());
            insertSenzor.setString(4, senzor.getRadSenzora().toString());
            insertSenzor.setInt(5, senzor.getMjernaPostaja().getId());
            insertSenzor.executeUpdate();

            Statement statement = veza.createStatement();
            ResultSet rezultat = statement.executeQuery("SELECT TOP 1 * FROM POSTAJE.SENZOR ORDER BY ID DESC");
            Integer senzorId = null;
            while (rezultat.next()){
                senzorId = rezultat.getInt("ID");
            }
            PreparedStatement insertSenzorTemp = veza.prepareStatement("INSERT INTO POSTAJE.SENZOR_TEMPERATURE(ID, ELEKTRONICKA_KOMPONENTA) VALUES (?, ?);");
            insertSenzorTemp.setInt(1, senzorId);
            insertSenzorTemp.setString(2, senzor.getElektronickaKomponenta());
            insertSenzorTemp.executeUpdate();
        }
        catch(Throwable ex) {
            veza.rollback();
            throw ex;
        }
        zatvaranjeVezeNaBazuPodataka(veza);
    }

    public static List<Senzor> dohvatiSenzore() throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        Statement statementSenzor = veza.createStatement();
        ResultSet resultSetSenzor =
                statementSenzor.executeQuery("SELECT  mp.NAZIV,  se.ID, se.MJERNA_JEDINICA, se.PRECIZNOST, se.VRIJEDNOST, se.RAD_SENZORA, st.ELEKTRONICKA_KOMPONENTA  FROM POSTAJE.SENZOR se INNER JOIN POSTAJE.MJERNA_POSTAJA  mp ON mp.ID = se.MJERNA_POSTAJA_ID INNER JOIN POSTAJE.SENZOR_TEMPERATURE st ON st.ID = se.ID");
        List<Senzor> listaSenzora = new ArrayList<>();
        while (resultSetSenzor.next()) {
            Integer senzorId = resultSetSenzor.getInt("SENZOR.ID");
            String mjernaJedinica = resultSetSenzor.getString("SENZOR.MJERNA_JEDINICA");
            Double preciznost = resultSetSenzor.getDouble("SENZOR.PRECIZNOST");
            BigDecimal vrijednost = resultSetSenzor.getBigDecimal("SENZOR.VRIJEDNOST");
            String rad = resultSetSenzor.getString("SENZOR.RAD_SENZORA");
            String nazivMjernaPostaja = resultSetSenzor.getString("MJERNA_POSTAJA.NAZIV");
            String komponenta = resultSetSenzor.getString("SENZOR_TEMPERATURE.ELEKTRONICKA_KOMPONENTA");
            /*String podSenzor = resultSetSenzor.getString("SENZOR_ZRACENJA.TIP_PODSENZORA");*/
            MjernaPostaja postaja = new MjernaPostaja(nazivMjernaPostaja, null, new GeografskaTocka(null,null),null);
            Senzor senzor = new SenzorTemperature(komponenta, mjernaJedinica, preciznost, postaja);
            senzor.setVrijednost(vrijednost);
            senzor.setRadSenzora(RadSenzora.valueOf(rad));
            senzor.setId(senzorId);
            listaSenzora.add(senzor);
        }
        zatvaranjeVezeNaBazuPodataka(veza);
        return listaSenzora;
    }

    public static Integer dohvatiNegativneSenzore() throws SQLException, IOException {
        Integer senzorId = 0;
        Connection veza = spajanjeNaBazuPodataka();
        Statement statementSenzor = veza.createStatement();
        ResultSet resultSetSenzor =
                statementSenzor.executeQuery("SELECT COUNT(*) AS broj FROM POSTAJE.SENZOR WHERE VRIJEDNOST < 0 AND AKTIVAN = TRUE;");
        while (resultSetSenzor.next()) {
            senzorId = resultSetSenzor.getInt("broj");
        }
        zatvaranjeVezeNaBazuPodataka(veza);
        return senzorId;
    }
}
