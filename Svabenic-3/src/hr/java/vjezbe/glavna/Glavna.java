package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.MjernaPostaja;
import hr.java.vjezbe.entitet.Senzor;
import hr.java.vjezbe.entitet.SenzorTemperature;
import hr.java.vjezbe.iznimke.NiskaTemperaturaException;
import hr.java.vjezbe.iznimke.VisokaTemperaturaException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Predstavlja glavnu klasu unutar koje se pozivaju metode potrebne za izvršavanje programa
 *
 * @author Marko
 * @version 1.0
 */
public class Glavna {
    private static final int BROJ_MJERNIH_POSTAJA = 2;
    private static final int BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA = 1;
    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

    public static void main(String[] args){

		Scanner unos = new Scanner(System.in);
		MjernaPostaja[] arrayMjernaPostaja = new MjernaPostaja[BROJ_MJERNIH_POSTAJA + BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA];

        SharedUnosIspis.unosPodatakaMjernePostaje(unos, arrayMjernaPostaja, BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA, BROJ_MJERNIH_POSTAJA);
        SharedUnosIspis.ispisiPodatkeMjernePostaje(arrayMjernaPostaja);

        unos.close();

        for (MjernaPostaja mjernaPostaja : arrayMjernaPostaja) {
            for (Senzor senzor : mjernaPostaja.dohvatiSenzore()) {
                if(senzor instanceof SenzorTemperature){
                    boolean nastaviPetlju;
                    do{
                        try {
                            Thread.sleep(1000);
                            ((SenzorTemperature) senzor).generirajVrijednost();
                            nastaviPetlju = true;
                        }
                        catch (VisokaTemperaturaException | NiskaTemperaturaException | InterruptedException ex){
                            logger.info(" Pogrešna temperatura mjerne postaje " + mjernaPostaja.getNaziv() + ex.getMessage(), ex);
                            nastaviPetlju = false;
                        }
                    }
                    while(nastaviPetlju);
                }
            }
        }
	}
}