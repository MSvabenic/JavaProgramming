package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja entitet županija opisan atributima naziv i država
 *
 * @author Marko
 * @version 1.0
 */
public class Zupanija extends BazniEntitet{

	private String naziv;
	private Drzava drzava;
	private List<Mjesto> mjesta;

    /**
     * Inicijalizira podatke županije
     * @param naziv podatak koji predstavlja naziv županije
     * @param drzava podatak koji predstavlja državu županije
     */
	public Zupanija(String naziv, Drzava drzava) {
		super();
		this.naziv = naziv;
		this.drzava = drzava;
		mjesta = new ArrayList<>();
	}

	public Zupanija(Integer id, String naziv, Drzava drzava) {
		super(id);
		this.naziv = naziv;
		this.drzava = drzava;
		mjesta = new ArrayList<>();
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

	public List<Mjesto> getMjesta() {
		return mjesta;
	}

	@Override
	public String toString() {
		return getNaziv();
	}
}
