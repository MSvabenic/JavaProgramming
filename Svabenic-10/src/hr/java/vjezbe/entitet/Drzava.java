package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *  Predstavlja entitet država opisan atributima naziv i površina
 *  @author Marko
 *  @version 1.0
 */
public class Drzava extends BazniEntitet{
	
	private String naziv;
	private BigDecimal povrsina;
    private List<Zupanija> zupanije;

    /**
     * Inicijalizira podatke države
     * @param naziv podatak koji predstavlja naziv države
     * @param povrsina podatak koji predstavlja površinu države
     */
	public Drzava(String naziv, BigDecimal povrsina) {
		super();
		this.naziv = naziv;
		this.povrsina = povrsina;
		zupanije = new ArrayList<>();
	}

	public Drzava(Integer id, String naziv, BigDecimal povrsina) {
		super(id);
		this.naziv = naziv;
		this.povrsina = povrsina;
		zupanije = new ArrayList<>();
	}

    /**
     * Dohvaća naziv države
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
     * Dohvaća površinu države
     */
	public BigDecimal getPovrsina() {
		return povrsina;
	}

    /**
     * Postavlja vrijednost površine u lokalnu varijablu
     */
	public void setPovrsina(BigDecimal povrsina) {
		this.povrsina = povrsina;
	}

    public List<Zupanija> getZupanije() {
        return zupanije;
    }

	@Override
    public String toString(){
		return getNaziv();
	}
}
