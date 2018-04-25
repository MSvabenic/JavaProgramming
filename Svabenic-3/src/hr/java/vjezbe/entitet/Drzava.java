package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 *  Predstavlja entitet država opisan atributima naziv i površina
 *  @author Marko
 *  @version 1.0
 */
public class Drzava {
	
	private String naziv;
	private BigDecimal povrsina;

    /**
     * Inicijalizira podatke države
     * @param naziv podatak koji predstavlja naziv države
     * @param povrsina podatak koji predstavlja površinu države
     */
	public Drzava(String naziv, BigDecimal povrsina) {
		super();
		this.naziv = naziv;
		this.povrsina = povrsina;
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
	
}
