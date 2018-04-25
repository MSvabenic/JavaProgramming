package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Sadrži metode za provjeru formata podataka i unos podataka za entitet mjerne postaje
 *
 * @author Marko
 * @version 1.0
 */
public class SharedUnosMjernePostaje {

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
                                                   GeografskaTocka geografskaTocka, Senzor[] senzori) {

        System.out.printf("Unesite naziv %d. mjerne postaje: ", redniBroj + 1);
        String nazivMjernePostaje = skener.nextLine();

        return new MjernaPostaja(nazivMjernePostaje, mjesto, geografskaTocka, senzori);
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
                                                                             GeografskaTocka geografskaTocka, Senzor[] senzori) {

        System.out.printf("Unesite naziv %d radio sondažne mjerne postaje: ", redniBroj + 1);
        String nazivMjernePostaje = skener.nextLine();

        System.out.printf("Unesite visinu %d radio sondažne mjerne postaje: ", redniBroj + 1);
        Integer visinaMjernePostaje = ValidacijaUnos.provjeraIntegera(skener);

        return new RadioSondaznaMjernaPostaja(visinaMjernePostaje, nazivMjernePostaje, mjesto, geografskaTocka, senzori);
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
        BigDecimal povrsinaDrzave = ValidacijaUnos.provjeraBigDecimala(skener);

        return new Drzava(nazivDrzave, povrsinaDrzave);
    }

    /**
     * Predstavlja unos podataka za entitet županija
     * @param skener podatak koji predstavlja klasu Scanner
     * @param redniBroj podatak koji predstavlja redni broj mjerne postaje
     * @return objekt županije
     */
    public static Zupanija unesiZupaniju(Scanner skener, Integer redniBroj, Drzava drzava) {

        System.out.printf("Unesite naziv zupanije %d. mjerne postaje: ", redniBroj + 1);
        String nazivZupanije = skener.nextLine();

        return new Zupanija(nazivZupanije, drzava);
    }

    public static Mjesto unesiMjesto(Scanner skener, Integer redniBroj, Zupanija zupanija) {

        System.out.printf("Unesite naziv mjesta %d. mjerne postaje: ", redniBroj + 1);
        String nazivMjesta = skener.nextLine();

        return new Mjesto(nazivMjesta, zupanija);
    }

    /**
     * Predstavlja unos podataka za entitet geografska točka
     * @param skener podatak koji predstavlja klasu Scanner
     * @param redniBroj podatak koji predstavlja redni broj mjerne postaje
     * @return objekt geografske točke
     */
    public static GeografskaTocka unesiGeografskuTocku(Scanner skener, Integer redniBroj) {

                System.out.printf("Unesite x koordinatu geografske tocke %d. mjerne postaje: ", redniBroj + 1);
                BigDecimal x = ValidacijaUnos.provjeraBigDecimala(skener);

                System.out.printf("Unesite y koordinatu geografske tocke %d. mjerne postaje: ", redniBroj + 1);
                BigDecimal y = ValidacijaUnos.provjeraBigDecimala(skener);

        return new GeografskaTocka(x, y);
    }
}