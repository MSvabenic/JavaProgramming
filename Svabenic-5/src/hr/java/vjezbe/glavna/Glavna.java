package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.MjernaPostaja;
import hr.java.vjezbe.entitet.MjernePostaje;
import hr.java.vjezbe.entitet.Mjesto;
import hr.java.vjezbe.entitet.Senzor;

import java.util.*;

/**
 * Predstavlja glavnu klasu unutar koje se pozivaju metode potrebne za izvršavanje programa
 *
 * @author Marko
 * @version 1.0
 */
public class Glavna {
    private static final int BROJ_MJERNIH_POSTAJA = 2;
    private static final int BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA = 1;
    private static Map<Mjesto, List<Senzor>> mjestoSenzori = new HashMap<>();

    public static void main(String[] args){

		Scanner unos = new Scanner(System.in);
		/*List<MjernaPostaja> mjernePostaje = new ArrayList<>(BROJ_MJERNIH_POSTAJA + BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA);*/
        MjernePostaje<MjernaPostaja> mjernePostaje = new MjernePostaje<>();

        SharedUnosMjernaPostaja.unosPodatakaMjernePostaje(unos, mjernePostaje, mjestoSenzori, BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA, BROJ_MJERNIH_POSTAJA);
        SharedIspis.ispisiPodatkeMjernePostaje(mjernePostaje);
        SharedIspis.ispisiMjestaISenzore(mjestoSenzori);
        SharedIspis.sortirajIspisiZupanije(mjernePostaje);
        SharedIspis.kreirajSenzorTemperature(mjernePostaje);
        unos.close();
	}
}