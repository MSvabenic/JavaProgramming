package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja entitet mjesto opisan atributima naziv i županija
 *
 * @author Marko
 * @version 1.0
 */
public class Mjesto extends BazniEntitet{

	private String naziv;
	private Zupanija zupanija;
	private List<MjernaPostaja> mjernePostaje;
	private VrstaMjesta vrstaMjesta;

    /**
     * Inicijalizira podatke mjesta
     * @param naziv podatak koji predstavlja naziv mjesta
     * @param zupanija podatak koji predstavlja županiju mjesta
     */
	public Mjesto(String naziv, Zupanija zupanija) {
		super();
		this.naziv = naziv;
		this.zupanija = zupanija;
		mjernePostaje = new ArrayList<>();
	}

	public Mjesto(Integer id, String naziv, Zupanija zupanija) {
		super(id);
		this.naziv = naziv;
		this.zupanija = zupanija;
		mjernePostaje = new ArrayList<>();
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

	public VrstaMjesta getVrstaMjesta() {
		return vrstaMjesta;
	}

	public void setVrstaMjesta(VrstaMjesta vrstaMjesta) {
		this.vrstaMjesta = vrstaMjesta;
	}

	public List<MjernaPostaja> getMjernePostaje() {
		return mjernePostaje;
	}

	@Override
	public String toString() {
		return "Mjesto " + getNaziv();
	}

}
