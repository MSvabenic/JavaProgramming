package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;
import static hr.java.vjezbe.glavna.SharedUnosSenzora.*;
import static hr.java.vjezbe.glavna.SharedUnosMjernePostaje.*;


import java.util.Scanner;

/**
 * Sadrži logiku za popunjavanje polja tipa mjerna postaja i ispis podataka mjerne postaje
 *
 * @author Marko
 * @version 1.0
 */
public class SharedUnosIspis {
    /**
     * Sadrži logiku za unos podataka mjerne postaje i radiosondažne mjerne postaje
     * @param unos podatak koji predstavlja klasu Scanner
     * @param arrayMjernaPostaja podatak koji predstavlja polje mjernih postaja
     * @param BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA podatak koji predstavlja broj radiosondažnih mjernih postaja u polju
     * @param BROJ_MJERNIH_POSTAJA podatak koji predstavlja broj mjernih postaja u polju
     * @return objekt tipa polje mjernih postaja
     */
    public static MjernaPostaja[] unosPodatakaMjernePostaje(Scanner unos, MjernaPostaja[] arrayMjernaPostaja,
                                                            Integer BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA, Integer BROJ_MJERNIH_POSTAJA){
        for (int i = 0; i < BROJ_MJERNIH_POSTAJA; i++) {

            Drzava drzava = unesiDrzavu(unos, i);

            Zupanija zupanija = unesiZupaniju(unos, i, drzava);

            Mjesto mjesto = unesiMjesto(unos, i, zupanija);

            GeografskaTocka geografskaTocka = unesiGeografskuTocku(unos, i);

            Senzor[] arraysenzori = popuniArraySenzora(unos);

            MjernaPostaja mjernaPostaja = unesiMjernuPostaju(unos, i, mjesto, geografskaTocka, arraysenzori);

            arrayMjernaPostaja[i] = mjernaPostaja;
        }

        for (int i = BROJ_MJERNIH_POSTAJA; i < BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA + BROJ_MJERNIH_POSTAJA; i++) {

            Drzava drzava = unesiDrzavu(unos , BROJ_MJERNIH_POSTAJA);

            Zupanija zupanija = unesiZupaniju(unos, BROJ_MJERNIH_POSTAJA, drzava);

            Mjesto mjesto = unesiMjesto(unos, BROJ_MJERNIH_POSTAJA, zupanija);

            GeografskaTocka geografskaTocka = unesiGeografskuTocku(unos, BROJ_MJERNIH_POSTAJA);

            Senzor[] arraysenzori = popuniArraySenzora(unos);

            RadioSondaznaMjernaPostaja radioSondaznaMjernaPostaja = unesiRadioSondaznuMjernuPostaju(unos, i, mjesto,
                    geografskaTocka, arraysenzori);

            radioSondaznaMjernaPostaja.povecajVisinu(radioSondaznaMjernaPostaja.dohvatiVisinuPostaje());

            arrayMjernaPostaja[i] = radioSondaznaMjernaPostaja;
        }

        return arrayMjernaPostaja;
    }

    /**
     * Sadrži logiku za ispisivanje podataka mjerne postaje
     * @param mjernaPostaja podatak koji predstavlja polje mjernih postaja
     */
    public static void ispisiPodatkeMjernePostaje(MjernaPostaja[] mjernaPostaja) {
        for (int j = 0; j < mjernaPostaja.length; j++) {
            if(mjernaPostaja[j] instanceof RadioSondaznaMjernaPostaja) {

                RadioSondaznaMjernaPostaja radioSondaznaMjernaPostaja = (RadioSondaznaMjernaPostaja) mjernaPostaja[j];

                System.out.println("******************************************");
                System.out.printf("PODACI MJERNE POSTAJE %d:%n", j + 1);
                System.out.printf("Naziv mjerne postaje: %s. %n", mjernaPostaja[j].getNaziv());
                System.out.printf("Postaja se nalazi u mjestu: %s, zupaniji: %s, drzavi: %s. %n",
                        mjernaPostaja[j].getMjesto().getNaziv(),
                        mjernaPostaja[j].getMjesto().getZupanija().getNaziv(),
                        mjernaPostaja[j].getMjesto().getZupanija().getDrzava().getNaziv());

                System.out.printf("Kooridnate postaje su: %nX:%f %nY:%f%n",
                        mjernaPostaja[j].getGeografskaTocka().getX(),
                        mjernaPostaja[j].getGeografskaTocka().getY());

                System.out.printf("Visina postaje je: %d%n",
                        radioSondaznaMjernaPostaja.dohvatiVisinuPostaje());

                for (Senzor senzor : radioSondaznaMjernaPostaja.dohvatiSenzore()) {
                    senzor.dohvatiPodatkeSenzora();
                }
                radioSondaznaMjernaPostaja.podesiVisinuPostaje(radioSondaznaMjernaPostaja.dohvatiVisinuPostaje());

            }
            else {
                MjernaPostaja obicnaMjernaPostaja = mjernaPostaja[j];
                System.out.println("******************************************");
                System.out.printf("PODACI MJERNE POSTAJE %d:%n", j + 1);
                System.out.printf("Naziv mjerne postaje: %s. %n", mjernaPostaja[j].getNaziv());
                System.out.printf("Postaja se nalazi u mjestu: %s, zupaniji: %s, drzavi: %s. %n",
                        mjernaPostaja[j].getMjesto().getNaziv(),
                        mjernaPostaja[j].getMjesto().getZupanija().getNaziv(),
                        mjernaPostaja[j].getMjesto().getZupanija().getDrzava().getNaziv());

                System.out.printf("Kooridnate postaje su: %nX:%f %nY:%f%n",
                        mjernaPostaja[j].getGeografskaTocka().getX(),
                        mjernaPostaja[j].getGeografskaTocka().getY());

                for (Senzor senzor : obicnaMjernaPostaja.dohvatiSenzore()) {
                    senzor.dohvatiPodatkeSenzora();
                }
            }
        }
    }
}