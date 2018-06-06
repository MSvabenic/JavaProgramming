package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet senzora vlage opisanog atributima nadklase
 *
 * @author Marko
 * @version 1.0
 */
public class SenzorVlage extends Senzor {
    /**
     * Inicijalizira podatke senzora vlage
     * @param mjernaJedinica podatak koji predstavlja mjernu jedinicu senzora
     * @param preciznost podatak koji predstavlja preciznost senzora
     */
	public SenzorVlage(String mjernaJedinica, Double preciznost, MjernaPostaja mjernaPostaja) {
		super(mjernaJedinica,preciznost, mjernaPostaja);
	}

    /**
     * Dohvaća podatke senzora vlage, vrijednost i mjernu jedinicu
     */
	@Override
	public String dohvatiPodatkeSenzora() {

		System.out.printf("Vrijednost senzora vlage: %f %s.%n",
				getVrijednost(), getMjernaJedinica());

		System.out.printf("Rad senzora vlage: %s.%n",
				getRadSenzora());

		return getVrijednost() + getMjernaJedinica() + getRadSenzora();

	}

	@Override
	public String toString() {
		return "Senzor vlage ";
	}
}
