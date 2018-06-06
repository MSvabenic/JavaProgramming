package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Predstavlja entitet senzora temperature opisanog atributima nadklase i atributom elektronička komponenta
 *
 * @author Marko
 * @version 1.0
 */
public class SenzorTemperature extends Senzor{

	private String elektronickaKomponenta;
	private static final Integer minVrijednost = -50;
	private static final Integer maxVrijednost = 50;
    private static  BigDecimal dozvoljenaMinVrijednost = new BigDecimal(-10);
    private static  BigDecimal dozvoljenaMaxVrijednost = new BigDecimal(40);

    /**
     * Inicijalizira podatke senzora, nadklase šalje podatke mjerna jedinica, preciznost
     * @param elektronickaKomponenta podatak koji predstavlja elektroničku komponentu senzora
     * @param mjernaJedinica podatak koji predstavlja mjernu jedinicu senzora
     * @param preciznost podatak koji predstavlja preciznost senzora
     */
	public SenzorTemperature(String elektronickaKomponenta, String mjernaJedinica, Double preciznost, MjernaPostaja mjernaPostaja){
		super(mjernaJedinica,preciznost, mjernaPostaja);
		this.elektronickaKomponenta = elektronickaKomponenta;
	}

    /**
     * Dohvaća elektronsku komponentu senzora
     */
	public String getElektronickaKomponenta() {
		return elektronickaKomponenta;
	}

    /**
     * Postavlja vrijednost elektroničke komponente u lokalnu varijablu
     */
	public void setElektronickaKomponenta(String elektronickaKomponenta) {
		this.elektronickaKomponenta = elektronickaKomponenta;
	}

    /**
     * Dohvaća podatke senzora temperature, elektroničku komponentu, vrijednost i mjernu jedinicu
     */
	@Override
	public String dohvatiPodatkeSenzora() {

	    System.out.printf("Naziv elektroničke komponente: %s.%n" , getElektronickaKomponenta());
        System.out.printf("Vrijednost senzora temperature: %f %s.%n",
                getVrijednost(), getMjernaJedinica());

		System.out.printf("Rad senzora temperature: %s.%n",
				getRadSenzora());

        return getElektronickaKomponenta() + getVrijednost() + getMjernaJedinica() + getRadSenzora();
	}

/*    *//**
     * Generira nasumičnu vrijednost za senzor temperature
     * @throws VisokaTemperaturaException iznimka koja se baca za previsoku temperaturu
     *//*
	public void generirajVrijednost() throws VisokaTemperaturaException{
		Random random = new Random();
		BigDecimal randomVrijednost = new BigDecimal
				(random.nextInt(maxVrijednost - minVrijednost + 1) + minVrijednost);
        setVrijednost(randomVrijednost);

        if(getVrijednost().compareTo(dozvoljenaMaxVrijednost) > 0){
            throw new VisokaTemperaturaException("Temperatura od " + getVrijednost() + " je previsoka!");
        }

        if(getVrijednost().compareTo(dozvoljenaMinVrijednost) < 0){
            throw new NiskaTemperaturaException("Temperatura od " + getVrijednost() + " je preniska!");
        }
	}*/

	@Override
	public String toString() {
		return "Senzor temperature ";
	}
}