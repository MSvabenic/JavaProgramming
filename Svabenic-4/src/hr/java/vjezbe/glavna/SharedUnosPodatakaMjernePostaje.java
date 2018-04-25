package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Sadrži metode za provjeru formata podataka i unos podataka za entitet mjerne postaje
 *
 * @author Marko
 * @version 1.0
 */
public class SharedUnosPodatakaMjernePostaje {
    /**
     * Predstavlja unos podataka za entitet mjerna postaja
     * @param skener podatak koji predstavlja klasu Scanner
     * @param redniBroj podatak koji predstavlja redni broj mjerne postaje
     * @param mjesto podatak koji predstavlja mjesto mjerne postaje
     * @param geografskaTocka podatak koji predstavlja geografksu točku mjerne postaje
     * @param senzori podatak koji predstavlja polje senzora mjerne postaje
     * @return objekt mjerne postaje
     */
    public static MjernaPostaja unesiMjernuPostaju(Scanner skener, Integer redniBroj, Mjesto mjesto,
                                                   GeografskaTocka geografskaTocka, List<Senzor> senzori, List<MjernaPostaja> mjernePostaje ) {

        MjernaPostaja mjernaPostaja;

        System.out.printf("Unesite naziv %d. mjerne postaje: ", redniBroj + 1);
        String nazivMjernePostaje = skener.nextLine();

        Optional<MjernaPostaja> postaja = mjernePostaje.stream().filter(p ->
                p.getMjesto().getMjernePostaje().equals(mjesto.getMjernePostaje())).findFirst();

        if(postaja.isPresent()){
            Mjesto postojeceMjesto = postaja.get().getMjesto();
            mjernaPostaja = new MjernaPostaja(nazivMjernePostaje, postojeceMjesto, geografskaTocka, senzori);
            postojeceMjesto.getMjernePostaje().add(mjernaPostaja);
        }
        else{
            mjernaPostaja = new MjernaPostaja(nazivMjernePostaje, mjesto, geografskaTocka, senzori);
            mjesto.getMjernePostaje().add(mjernaPostaja);
        }

        return mjernaPostaja;
    }

    /**
     * Predstavlja unos podataka za entitet radiosondažna mjerna postaja
     * @param skener podatak koji predstavlja klasu Scanner
     * @param redniBroj podatak koji predstavlja redni broj mjerne postaje
     * @param mjesto podatak koji predstavlja mjesto mjerne postaje
     * @param geografskaTocka podatak koji predstavlja geografksu točku mjerne postaje
     * @param senzori podatak koji predstavlja polje senzora mjerne postaje
     * @return objekt radiosondažne mjerne postaje
     */
    public static RadioSondaznaMjernaPostaja unesiRadioSondaznuMjernuPostaju(Scanner skener, Integer redniBroj, Mjesto mjesto,
                                                                             GeografskaTocka geografskaTocka, List<Senzor> senzori, List<MjernaPostaja> mjernePostaje) {

        RadioSondaznaMjernaPostaja radioSondaznaMjernaPostaja;

        System.out.printf("Unesite naziv %d radio sondažne mjerne postaje: ", redniBroj + 1);
        String nazivMjernePostaje = skener.nextLine();

        System.out.printf("Unesite visinu %d radio sondažne mjerne postaje: ", redniBroj + 1);
        Integer visinaMjernePostaje = SharedValidacijaUnosa.provjeraIntegera(skener);

        Optional<MjernaPostaja> postaja = mjernePostaje.stream().filter(p ->
                p.getMjesto().getMjernePostaje().equals(mjesto.getMjernePostaje())).findFirst();

        if(postaja.isPresent()){
            Mjesto postojeceMjesto = postaja.get().getMjesto();
            radioSondaznaMjernaPostaja = new RadioSondaznaMjernaPostaja(visinaMjernePostaje, nazivMjernePostaje, postojeceMjesto, geografskaTocka, senzori);
            postojeceMjesto.getMjernePostaje().add(radioSondaznaMjernaPostaja);
        }
        else{
            radioSondaznaMjernaPostaja = new RadioSondaznaMjernaPostaja(visinaMjernePostaje, nazivMjernePostaje, mjesto, geografskaTocka, senzori);
            mjesto.getMjernePostaje().add(radioSondaznaMjernaPostaja);
        }

        return radioSondaznaMjernaPostaja;
    }

    /**
     * Predstavlja unos podataka za entitet država
     * @param skener podatak koji predstavlja klasu Scanner
     * @param redniBroj podatak koji predstavlja redni broj mjerne postaje
     * @return objekt države
     */
    public static Drzava unesiDrzavu(Scanner skener, Integer redniBroj) {

        System.out.printf("Unesite naziv drzave %d. mjerne postaje: ", redniBroj + 1);
        String nazivDrzave = skener.nextLine();

        System.out.printf("Unesite povrsinu drzave %d. mjerne postaje: ", redniBroj + 1);
        BigDecimal povrsinaDrzave = SharedValidacijaUnosa.provjeraBigDecimala(skener);

        return new Drzava(nazivDrzave, povrsinaDrzave);
    }

    /**
     * Predstavlja unos podataka za entitet županija
     * @param skener podatak koji predstavlja klasu Scanner
     * @param redniBroj podatak koji predstavlja redni broj mjerne postaje
     * @return objekt županije
     */
    public static Zupanija unesiZupaniju(Scanner skener, Integer redniBroj, Drzava drzava, List<MjernaPostaja> mjernePostaje) {

        Zupanija zupanija;

        System.out.printf("Unesite naziv zupanije %d. mjerne postaje: ", redniBroj + 1);
        String nazivZupanije = skener.nextLine();

        Optional<MjernaPostaja> postaja = mjernePostaje.stream().filter(p ->
                p.getMjesto().getZupanija().getDrzava().getNaziv().equals(drzava.getNaziv())).findFirst();

        if(postaja.isPresent()){
            Drzava postojecaDrzava = postaja.get().getMjesto().getZupanija().getDrzava();
            zupanija = new Zupanija(nazivZupanije, postojecaDrzava);
            postojecaDrzava.getZupanije().add(zupanija);
        }
        else{
            zupanija = new Zupanija(nazivZupanije, drzava);
            drzava.getZupanije().add(zupanija);
        }

        return zupanija;
    }

    public static Mjesto unesiMjesto(Scanner skener, Integer redniBroj, Zupanija zupanija, List<MjernaPostaja> mjernePostaje) {

        Mjesto mjesto;

        System.out.printf("Unesite naziv mjesta %d. mjerne postaje: ", redniBroj + 1);
        String nazivMjesta = skener.nextLine();

        Optional<MjernaPostaja> postaja = mjernePostaje.stream().filter(p ->
                p.getMjesto().getZupanija().getNaziv().equals(zupanija.getNaziv())).findFirst();

        if(postaja.isPresent()){
            Zupanija postojecaZupanija = postaja.get().getMjesto().getZupanija();
            mjesto = new Mjesto(nazivMjesta, postojecaZupanija);
            postojecaZupanija.getMjesta().add(mjesto);
        }
        else{
            mjesto = new Mjesto(nazivMjesta, zupanija);
            zupanija.getMjesta().add(mjesto);
        }

        mjesto.setVrstaMjesta(SharedUnosEnumeracija.unosVrstaMjesta(skener));
        return mjesto;
    }

    /**
     * Predstavlja unos podataka za entitet geografska točka
     * @param skener podatak koji predstavlja klasu Scanner
     * @param redniBroj podatak koji predstavlja redni broj mjerne postaje
     * @return objekt geografske točke
     */
    public static GeografskaTocka unesiGeografskuTocku(Scanner skener, Integer redniBroj) {

                System.out.printf("Unesite x koordinatu geografske tocke %d. mjerne postaje: ", redniBroj + 1);
                BigDecimal x = SharedValidacijaUnosa.provjeraBigDecimala(skener);

                System.out.printf("Unesite y koordinatu geografske tocke %d. mjerne postaje: ", redniBroj + 1);
                BigDecimal y = SharedValidacijaUnosa.provjeraBigDecimala(skener);

        return new GeografskaTocka(x, y);
    }
}