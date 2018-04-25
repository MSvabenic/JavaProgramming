package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;

import java.math.BigDecimal;
import java.util.Scanner;

public class Shared {

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

    private static MjernaPostaja unesiMjernuPostaju(Scanner skener, Integer redniBroj, Mjesto mjesto,
                                                    GeografskaTocka geografskaTocka, Senzor[] senzori) {

        System.out.printf("Unesite naziv %d. mjerne postaje: ", redniBroj + 1);
        String nazivMjernePostaje = skener.nextLine();

        return new MjernaPostaja(nazivMjernePostaje, mjesto, geografskaTocka, senzori);
    }

    private static RadioSondaznaMjernaPostaja unesiRadioSondaznuMjernuPostaju(Scanner skener, Integer redniBroj, Mjesto mjesto,
                                                                              GeografskaTocka geografskaTocka, Senzor[] senzori) {

        System.out.printf("Unesite naziv %d radio sondažne mjerne postaje: ", redniBroj + 1);
        String nazivMjernePostaje = skener.nextLine();

        System.out.printf("Unesite visinu %d radio sondažne mjerne postaje: ", redniBroj + 1);
        Integer visinaMjernePostaje = skener.nextInt();

        return new RadioSondaznaMjernaPostaja(visinaMjernePostaje, nazivMjernePostaje, mjesto, geografskaTocka, senzori);
    }


    private static Drzava unesiDrzavu(Scanner skener, Integer redniBroj) {

        System.out.printf("Unesite naziv drzave %d. mjerne postaje: ", redniBroj + 1);
        String nazivDrzave = skener.nextLine();

        System.out.printf("Unesite povrsinu drzave %d. mjerne postaje: ", redniBroj + 1);
        BigDecimal povrsinaDrzave = skener.nextBigDecimal();

        skener.nextLine();

        return new Drzava(nazivDrzave, povrsinaDrzave);
    }

    private static Zupanija unesiZupaniju(Scanner skener, Integer redniBroj, Drzava drzava) {

        System.out.printf("Unesite naziv zupanije %d. mjerne postaje: ", redniBroj + 1);
        String nazivZupanije = skener.nextLine();

        return new Zupanija(nazivZupanije, drzava);
    }

    private static Mjesto unesiMjesto(Scanner skener, Integer redniBroj, Zupanija zupanija) {

        System.out.printf("Unesite naziv mjesta %d. mjerne postaje: ", redniBroj + 1);
        String nazivMjesta = skener.nextLine();

        return new Mjesto(nazivMjesta, zupanija);
    }

    private static GeografskaTocka unesiGeografskuTocku(Scanner skener, Integer redniBroj) {

        System.out.printf("Unesite x koordinatu geografske tocke %d. mjerne postaje: ", redniBroj + 1);
        BigDecimal x = skener.nextBigDecimal();

        System.out.printf("Unesite y koordinatu geografske tocke %d. mjerne postaje: ", redniBroj + 1);
        BigDecimal y = skener.nextBigDecimal();

        skener.nextLine();

        return new GeografskaTocka(x, y);
    }

    private static SenzorTemperature unesiSenzorTemperature(Scanner skener){
        System.out.print("Unesite šifru elektroničke komponente senzora temperature: ");
        String elektronickaKomponenta = skener.nextLine();

        System.out.print("Odaberite vrijednost jedinice temperature, 1: C, 2: K, 3: F, 4: R :");
        Integer jedinicaSenzoraTemperature = skener.nextInt();
        skener.nextLine();

        while ( jedinicaSenzoraTemperature > 4 || jedinicaSenzoraTemperature <= 0){
            System.out.printf("Ne postoji mjerna jedinica pod brojem: %d%n" +
                    "Molim unesite ponovno mjernu jedinicu temperature, 1: C, 2: K, 3: F, 4: R  :", jedinicaSenzoraTemperature);
            jedinicaSenzoraTemperature = skener.nextInt();
            skener.nextLine();
        }

        System.out.print("Unesite vrijednost senzora temperature: ");
        BigDecimal vrijednostSenzoraTemperature = skener.nextBigDecimal();
        skener.nextLine();

        String jedinicaTemperature = SharedMetodeSenzora.jedinicaTemperature(jedinicaSenzoraTemperature);
        Double preciznostTemperature = SharedMetodeSenzora.preciznostTemperature(jedinicaTemperature);

        SenzorTemperature senzorTemperature = new SenzorTemperature(elektronickaKomponenta, jedinicaTemperature, preciznostTemperature);

        senzorTemperature.setVrijednost(vrijednostSenzoraTemperature);

        return  senzorTemperature;
    }

    private static SenzorVlage unesiSenzorVlage(Scanner skener){

        System.out.print("Unesite vrijednost senzora vlage: ");
        BigDecimal vrijednostSenzoraVlage = skener.nextBigDecimal();
        skener.nextLine();

        String jedinicaVlage = SharedMetodeSenzora.jedinicaVlage(1);
        Double preciznostVlage = SharedMetodeSenzora.preciznostVlage(jedinicaVlage);

        SenzorVlage senzorVlage = new SenzorVlage(jedinicaVlage, preciznostVlage);

        senzorVlage.setVrijednost(vrijednostSenzoraVlage);

        return  senzorVlage;
    }

    private static SenzorGlobalnogZracenja unesiSenzorGlobalnogZracenja(Scanner skener){

        System.out.print("Unesite šifru podsenzora globalnog zračenja: ");
        String sifraSenzoraZracenja = skener.nextLine();

        System.out.print("Odaberite vrijednost jedinice globalnog zracenja, 1: W/m, 2: mV :");
        Integer jedinicaGlobalnogZracenja = skener.nextInt();
        skener.nextLine();

        while (jedinicaGlobalnogZracenja > 2 || jedinicaGlobalnogZracenja <= 0){
            System.out.printf("Ne postoji mjerna jedinica pod brojem: %d%n" +
                    "Molim unesite ponovno mjernu jedinicu globalnog zracenja, , 1: W/m, 2: mV :", jedinicaGlobalnogZracenja);
            jedinicaGlobalnogZracenja = skener.nextInt();
            skener.nextLine();
        }

        System.out.print("Unesite vrijednost senzora globalnog zračenja:");
        BigDecimal vrijednostSenzoraGlobalnog = skener.nextBigDecimal();
        skener.nextLine();

        String jedinicaZracenja = SharedMetodeSenzora.jedinicaGlobalnogZracenja(jedinicaGlobalnogZracenja);
        Double preciznostZracenja= SharedMetodeSenzora.preciznostGlobalnogZracenja(jedinicaZracenja);

        SenzorGlobalnogZracenja senzorGlobalnogZracenja = new SenzorGlobalnogZracenja(sifraSenzoraZracenja, jedinicaZracenja, preciznostZracenja);

        senzorGlobalnogZracenja.setVrijednost(vrijednostSenzoraGlobalnog);

        return  senzorGlobalnogZracenja;
    }

    private static Senzor[] popuniArraySenzora(Scanner unos){

        SenzorTemperature senzorTemperature = unesiSenzorTemperature(unos);

        SenzorVlage senzorVlage = unesiSenzorVlage(unos);

        SenzorGlobalnogZracenja senzorGlobalnogZracenja = unesiSenzorGlobalnogZracenja(unos);

        return new Senzor[] {senzorTemperature, senzorVlage, senzorGlobalnogZracenja};
    }
}