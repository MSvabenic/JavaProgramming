package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;

import static hr.java.vjezbe.glavna.SharedUnosSenzora.*;
import static hr.java.vjezbe.glavna.SharedUnosPodatakaMjernePostaje.*;


import java.util.*;

/**
 * Sadrži logiku za popunjavanje polja tipa mjerna postaja i ispis podataka mjerne postaje
 *
 * @author Marko
 * @version 1.0
 */
public class SharedUnosMjernaPostaja {
    /**
     * Sadrži logiku za unos podataka mjerne postaje i radiosondažne mjerne postaje
     * @param unos podatak koji predstavlja klasu Scanner
     * @param arrayMjernaPostaja podatak koji predstavlja polje mjernih postaja
     * @param BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA podatak koji predstavlja broj radiosondažnih mjernih postaja u polju
     * @param BROJ_MJERNIH_POSTAJA podatak koji predstavlja broj mjernih postaja u polju
     * @return objekt tipa polje mjernih postaja
     */
    public static MjernePostaje<MjernaPostaja> unosPodatakaMjernePostaje(Scanner unos, MjernePostaje<MjernaPostaja> arrayMjernaPostaja, Map<Mjesto, List<Senzor>> mjestaSenzori,
                                                                    Integer BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA, Integer BROJ_MJERNIH_POSTAJA){

        for (int i = 0; i < BROJ_MJERNIH_POSTAJA; i++) {

            Drzava drzava = unesiDrzavu(unos, i);

            Zupanija zupanija = unesiZupaniju(unos, i, drzava, arrayMjernaPostaja);

            Mjesto mjesto = unesiMjesto(unos, i, zupanija, arrayMjernaPostaja);

            GeografskaTocka geografskaTocka = unesiGeografskuTocku(unos, i);

            List<Senzor> arraysenzori = popuniArraySenzora(unos);

            mjestaSenzori.put(mjesto,arraysenzori);

            MjernaPostaja mjernaPostaja = unesiMjernuPostaju(unos, i, mjesto, geografskaTocka, arraysenzori, arrayMjernaPostaja);

            arrayMjernaPostaja.add(mjernaPostaja);
        }

        for (int i = BROJ_MJERNIH_POSTAJA; i < BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA + BROJ_MJERNIH_POSTAJA; i++) {

            Drzava drzava = unesiDrzavu(unos , BROJ_MJERNIH_POSTAJA);

            Zupanija zupanija = unesiZupaniju(unos, BROJ_MJERNIH_POSTAJA, drzava, arrayMjernaPostaja);

            Mjesto mjesto = unesiMjesto(unos, BROJ_MJERNIH_POSTAJA, zupanija, arrayMjernaPostaja);

            GeografskaTocka geografskaTocka = unesiGeografskuTocku(unos, BROJ_MJERNIH_POSTAJA);

            List<Senzor> arraysenzori = popuniArraySenzora(unos);

            mjestaSenzori.put(mjesto,arraysenzori);

            RadioSondaznaMjernaPostaja radioSondaznaMjernaPostaja = unesiRadioSondaznuMjernuPostaju(unos, i, mjesto,
                    geografskaTocka, arraysenzori, arrayMjernaPostaja);

            radioSondaznaMjernaPostaja.povecajVisinu(radioSondaznaMjernaPostaja.dohvatiVisinuPostaje());

            arrayMjernaPostaja.add(radioSondaznaMjernaPostaja);
        }
        return arrayMjernaPostaja;
    }
}