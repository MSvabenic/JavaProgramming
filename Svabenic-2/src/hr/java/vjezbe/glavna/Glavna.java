package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.MjernaPostaja;

import java.util.Scanner;

public class Glavna {
    private static final int BROJ_MJERNIH_POSTAJA = 2;
    private static final int BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA = 1;

    public static void main(String[] args) {

		Scanner unos = new Scanner(System.in);
		MjernaPostaja[] arrayMjernaPostaja = new MjernaPostaja[BROJ_MJERNIH_POSTAJA + BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA];

        Shared.unosPodatakaMjernePostaje(unos, arrayMjernaPostaja, BROJ_MJERNIH_POSTAJA, BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA);
        Shared.ispisiPodatkeMjernePostaje(arrayMjernaPostaja);

		unos.close();
	}
}