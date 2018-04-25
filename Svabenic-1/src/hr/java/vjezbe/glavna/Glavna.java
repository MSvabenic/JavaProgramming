package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.util.Scanner;

import hr.java.vjezbe.entitet.Drzava;
import hr.java.vjezbe.entitet.GeografskaTocka;
import hr.java.vjezbe.entitet.MjernaPostaja;
import hr.java.vjezbe.entitet.Mjesto;
import hr.java.vjezbe.entitet.Zupanija;

public class Glavna {

	private static final int BROJ_MJERNIH_POSTAJA = 1;

	public static void main(String[] args) {

		Scanner unos = new Scanner(System.in);

		MjernaPostaja[] arrayMjernaPostaja = new MjernaPostaja[BROJ_MJERNIH_POSTAJA];

		for (int i = 0; i < BROJ_MJERNIH_POSTAJA; i++) {

			Drzava drzava = unesiDrzavu(unos, i);

			Zupanija zupanija = unesiZupaniju(unos, i, drzava);

			Mjesto mjesto = unesiMjesto(unos, i, zupanija);

			GeografskaTocka geografskaTocka = unesiGeografskuTocku(unos, i);

			MjernaPostaja mjernaPostaja = unesiMjernuPostaju(unos, i, mjesto, geografskaTocka);

			arrayMjernaPostaja[i] = mjernaPostaja;

		}
		ispisiPodatke(arrayMjernaPostaja);

		unos.close();
	}

	private static void ispisiPodatke(MjernaPostaja[] mjernaPostaja) {

		for (int j = 0; j < BROJ_MJERNIH_POSTAJA; j++) {
			System.out.println("******************************************");
			System.out.printf("PODACI MJERNE POSTAJE %d:%n", j + 1);
			System.out.printf("Naziv mjerne postaje: %s. %n", mjernaPostaja[j].getNaziv());
			System.out.printf("Postaja se nalazi u mjestu: %s, zupaniji: %s, drzavi: %s. %n",
					mjernaPostaja[j].getMjesto().getNaziv(), 
					mjernaPostaja[j].getMjesto().getZupanija().getNaziv(),
					mjernaPostaja[j].getMjesto().getZupanija().getDrzava().getNaziv());

			System.out.printf("Kooridnate postaje su: %nX:%f %nY:%f",
					mjernaPostaja[j].getGeografskaTocka().getX(),
					mjernaPostaja[j].getGeografskaTocka().getY());
		}
	}

	private static MjernaPostaja unesiMjernuPostaju(Scanner skener, Integer redniBroj, Mjesto mjesto,
			GeografskaTocka geografskaTocka) {

		System.out.printf("Unesite naziv %d. mjerne postaje: ", redniBroj + 1);
		String nazivMjernePostaje = skener.nextLine();

		return new MjernaPostaja(nazivMjernePostaje, mjesto, geografskaTocka);
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

}