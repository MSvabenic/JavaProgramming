package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet županija opisan atributima naziv i država
 *
 * @author Marko
 * @version 1.0
 */
public class Zupanija {

	private String naziv;
	private Drzava drzava;

    /**
     * Inicijalizira podatke županije
     * @param naziv podatak koji predstavlja naziv županije
     * @param drzava podatak koji predstavlja državu županije
     */
	public Zupanija(String naziv, Drzava drzava) {
		super();
		this.naziv = naziv;
		this.drzava = drzava;
	}

    /**
     * Dohvaća naziv županije
     */
	public String getNaziv() {
		return naziv;
	}

    /**
     * Postavlja vrijednost naziva u lokalnu varijablu
     */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

    /**
     * Dohvaća državu županije
     */
	public Drzava getDrzava() {
		return drzava;
	}

    /**
     * Postavlja vrijenost države u lokalnu varijablu
     */
	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}
}
