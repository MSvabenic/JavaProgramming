package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Sadrži metode za provjeru formata podataka i unos podataka za entitete senzora
 *
 * @author Marko
 * @version 1.0
 */
public class SharedUnosSenzora {

    /**
     * Radi unos svih senzora u jedinstveno polje
     * @param skener podatak predstavlja klasu Scanner
     * @return objekt polja senzora
     */
    public static List<Senzor> popuniArraySenzora(Scanner skener){

        List<Senzor> senzori  = new ArrayList<>();

        SenzorTemperature senzorTemperature = unesiSenzorTemperature(skener);

        SenzorVlage senzorVlage = unesiSenzorVlage(skener);

        SenzorGlobalnogZracenja senzorGlobalnogZracenja = unesiSenzorGlobalnogZracenja(skener);

        senzori.add(senzorTemperature);
        senzori.add(senzorVlage);
        senzori.add(senzorGlobalnogZracenja);

        return senzori;
    }

    /**
     * Radi unos podataka i provjeru podataka za entitet senzor temperature
     * @param skener podatak koji predstavlja klasu Scanner
     * @return objekt senzora temperature
     */
    private static SenzorTemperature unesiSenzorTemperature(Scanner skener){

        System.out.print("Unesite šifru elektroničke komponente senzora temperature: ");
        String elektronickaKomponenta = skener.nextLine();

        System.out.print("Odaberite vrijednost jedinice temperature, 1: C, 2: K, 3: F, 4: R :");
        Integer jedinicaTemperature = SharedValidacijaUnosa.provjeraIntegera(skener);

        while (jedinicaTemperature > 4 || jedinicaTemperature <= 0){
            System.out.printf("Ne postoji mjerna jedinica pod brojem: %d%n" +
                    "Molim unesite ponovno mjernu jedinicu temperature, 1: C, 2: K, 3: F, 4: R  :", jedinicaTemperature);
            jedinicaTemperature = skener.nextInt();
            skener.nextLine();
        }

        System.out.print("Unesite vrijednost senzora temperature: ");
        BigDecimal vrijednostTemperature = SharedValidacijaUnosa.provjeraBigDecimala(skener);

        String jedinicaSenzoraTemperature = SharedMetodeSenzora.jedinicaTemperature(jedinicaTemperature);
        Double preciznostTemperature = SharedMetodeSenzora.preciznostTemperature(jedinicaSenzoraTemperature);

        SenzorTemperature senzorTemperature = new SenzorTemperature(elektronickaKomponenta, jedinicaSenzoraTemperature, preciznostTemperature);

        senzorTemperature.setVrijednost(vrijednostTemperature);
        senzorTemperature.setRadSenzora(SharedUnosEnumeracija.unosRadaSenzora(skener));
        return  senzorTemperature;
    }

    /**
     * Radi unos podataka i provjeru podataka za entitet senzor vlage
     * @param skener podatak koji predstavlja klasu Scanner
     * @return objekt senzor vlage
     */
    private static SenzorVlage unesiSenzorVlage(Scanner skener){

        System.out.print("Unesite vrijednost senzora vlage: ");
        BigDecimal vrijednostSenzoraVlage = SharedValidacijaUnosa.provjeraBigDecimala(skener);


        String jedinicaVlage = SharedMetodeSenzora.jedinicaVlage(1);
        Double preciznostVlage = SharedMetodeSenzora.preciznostVlage(jedinicaVlage);

        SenzorVlage senzorVlage = new SenzorVlage(jedinicaVlage, preciznostVlage);

        senzorVlage.setVrijednost(vrijednostSenzoraVlage);
        senzorVlage.setRadSenzora(SharedUnosEnumeracija.unosRadaSenzora(skener));
        return  senzorVlage;
    }

    /**
     * Radi unos podataka i provjeru podataka za entitet senzor globanog zračenja
     * @param skener podatak koji predstavlja klasu Scanner
     * @return objekt senzor globalnog zračenja
     */
    private static SenzorGlobalnogZracenja unesiSenzorGlobalnogZracenja(Scanner skener){

        System.out.print("Unesite šifru podsenzora globalnog zračenja: ");
        String sifraSenzoraZracenja = skener.nextLine();

        System.out.print("Odaberite vrijednost jedinice globalnog zracenja, 1: W/m, 2: mV :");
        Integer jedinicaGlobalnogZracenja = SharedValidacijaUnosa.provjeraIntegera(skener);


        while (jedinicaGlobalnogZracenja > 2 || jedinicaGlobalnogZracenja <= 0){
            System.out.printf("Ne postoji mjerna jedinica pod brojem: %d%n" +
                    "Molim unesite ponovno mjernu jedinicu globalnog zracenja, , 1: W/m, 2: mV :", jedinicaGlobalnogZracenja);
            jedinicaGlobalnogZracenja = skener.nextInt();
            skener.nextLine();
        }

        System.out.print("Unesite vrijednost senzora globalnog zračenja:");
        BigDecimal vrijednostSenzoraGlobalnog = SharedValidacijaUnosa.provjeraBigDecimala(skener);

        String jedinicaZracenja = SharedMetodeSenzora.jedinicaGlobalnogZracenja(jedinicaGlobalnogZracenja);
        Double preciznostZracenja = SharedMetodeSenzora.preciznostGlobalnogZracenja(jedinicaZracenja);

        SenzorGlobalnogZracenja senzorGlobalnogZracenja = new SenzorGlobalnogZracenja(sifraSenzoraZracenja, jedinicaZracenja, preciznostZracenja);

        senzorGlobalnogZracenja.setVrijednost(vrijednostSenzoraGlobalnog);
        senzorGlobalnogZracenja.setRadSenzora(SharedUnosEnumeracija.unosRadaSenzora(skener));

        return  senzorGlobalnogZracenja;
    }
}