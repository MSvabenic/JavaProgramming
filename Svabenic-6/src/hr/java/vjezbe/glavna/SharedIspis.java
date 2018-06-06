package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;
import hr.java.vjezbe.iznimke.NiskaTemperaturaException;
import hr.java.vjezbe.iznimke.VisokaTemperaturaException;
import hr.java.vjezbe.sortiranje.ZupanijaSorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class SharedIspis {
    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

    /**
     * Sadrži logiku za ispisivanje podataka mjerne postaje
     *
     * @param postaje podatak koji predstavlja polje mjernih postaja
     */
    public static void ispisiPodatkeMjernePostaje(MjernePostaje<MjernaPostaja> postaje) {

        List<MjernaPostaja> mjernePostaje = postaje.getSortedList();
        mjernePostaje.forEach(mjernaPostaja -> {
            System.out.println("******************************************");
            System.out.print("PODACI MJERNE POSTAJE \n");
            System.out.printf("Naziv mjerne postaje: %s. %n", mjernaPostaja.getNaziv());
            System.out.printf("Postaja se nalazi u mjestu: %s, zupaniji: %s, drzavi: %s. %n",
                    mjernaPostaja.getMjesto().getZupanija().getNaziv(),
                    mjernaPostaja.getMjesto().getZupanija().getDrzava().getNaziv(),
                    mjernaPostaja.getMjesto().getNaziv());

            System.out.printf("Vrsta mjesta: %s%n", mjernaPostaja.getMjesto().getVrstaMjesta());

            System.out.printf("Kooridnate postaje su: %nX:%f %nY:%f%n",
                    mjernaPostaja.getGeografskaTocka().getX(),
                    mjernaPostaja.getGeografskaTocka().getY());

            if(mjernaPostaja instanceof RadioSondaznaMjernaPostaja){
                RadioSondaznaMjernaPostaja radioSondaznaMjernaPostaja = (RadioSondaznaMjernaPostaja) mjernaPostaja;
                System.out.printf("Visina postaje je: %d%n",
                        radioSondaznaMjernaPostaja.dohvatiVisinuPostaje());

                radioSondaznaMjernaPostaja.podesiVisinuPostaje(radioSondaznaMjernaPostaja.dohvatiVisinuPostaje());
            }
            
            mjernaPostaja.dohvatiSenzore().forEach(Senzor::dohvatiPodatkeSenzora);
        });
    }


    public static void kreirajSenzorTemperature(MjernePostaje<MjernaPostaja> postaje){
        List<MjernaPostaja> mjernePostaje = postaje.getSortedList();
        for (MjernaPostaja mjernaPostaja : mjernePostaje) {
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
    public static void sortirajIspisiZupanije(MjernePostaje<MjernaPostaja> postaje){

        List<MjernaPostaja> mjernePostaje = postaje.getSortedList();

        SortedSet<Zupanija> zupanije = new TreeSet<>(new ZupanijaSorter());
        for(MjernaPostaja mjernaPostaja : mjernePostaje){
            zupanije.add(mjernaPostaja.getMjesto().getZupanija());
        }

        System.out.println("Sve županije, sortirano: ");
        for (Zupanija zupanija : zupanije) {
            System.out.println(zupanija.getNaziv());
        }
    }

    public static void ispisiMjestaISenzore(Map<Mjesto, List<Senzor>> mjestaSenzori){
        for (Mjesto key : mjestaSenzori.keySet()) {

            System.out.println(key + " ima slijedeće senzore: \n" + mjestaSenzori.get(key).toString().replace("[", "").replace("]", ""));
        }
    }
}