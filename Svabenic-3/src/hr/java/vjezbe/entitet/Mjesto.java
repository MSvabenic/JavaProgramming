package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet mjesto opisan atributima naziv i županija
 *
 * @author Marko
 * @version 1.0
 */
public class Mjesto {

	private String naziv;
	private Zupanija zupanija;

    /**
     * Inicijalizira podatke mjesta
     * @param naziv podatak koji predstavlja naziv mjesta
     * @param zupanija podatak koji predstavlja županiju mjesta
     */
	public Mjesto(String naziv, Zupanija zupanija) {
		super();
		this.naziv = naziv;
		this.zupanija = zupanija;
	}

    /**
     * Dohvaća naziv mjesta
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
     * Dohvaća županiju mjesta
     */
	public Zupanija getZupanija() {
		return zupanija;
	}

    /**
     * Postavlja vrijednost županije u lokalnu varijablu
     */
	public void setZupanija(Zupanija zupanija) {
		this.zupanija = zupanija;
	}

}
