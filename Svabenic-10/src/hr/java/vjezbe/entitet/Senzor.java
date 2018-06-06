package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * Predstavlja bazni entitet senzora opisan atributima mjerna jedinica, preciznost, vrijednost
 *
 * @author Marko
 * @version 1.0
 */
public abstract class Senzor extends BazniEntitet{

	private String mjernaJedinica;
	private Double preciznost;
	private BigDecimal vrijednost;
	private RadSenzora radSenzora;
	private MjernaPostaja mjernaPostaja;

    /**
     * Inicijalizira podatke senzora
     * @param mjernaJedinica podatak koji predstavlja mjernu jedinicu senzora
     * @param preciznost podatak koji predstavlja preciznost senzora
     */
	public Senzor(String mjernaJedinica, Double preciznost, MjernaPostaja mjernaPostaja) {
		super();
		this.mjernaJedinica = mjernaJedinica;
		this.preciznost = preciznost;
		this.mjernaPostaja = mjernaPostaja;
	}

	public Senzor(Integer id, String mjernaJedinica, Double preciznost, MjernaPostaja mjernaPostaja) {
		super(id);
		this.mjernaJedinica = mjernaJedinica;
		this.preciznost = preciznost;
		this.mjernaPostaja = mjernaPostaja;
	}

	/**
     * Dohvaća mjernu jedinicu senzora
     */
	public String getMjernaJedinica() {
		return mjernaJedinica;
	}

    /**
     * Postavlja vrijednost mjerne jedinice senzora u lokalnu varijablu
     */
	public void setMjernaJedinica(String mjernaJedinica) {
		this.mjernaJedinica = mjernaJedinica;
	}

    /**
     * Dohvaća preciznost senzora
     */
	public Double getPreciznost() {
		return preciznost;
	}

    /**
     * Postavlja vrijednost preciznosti senzora u lokalnu varijablu
     */
	public void setPreciznost(Double preciznost) {
		this.preciznost = preciznost;
	}

    /**
     * Dohvaća vrijednost senzora
     */
	public BigDecimal getVrijednost() {
		return vrijednost;
	}

    /**
     * Postavlja vrijednost senzora u lokalnu varijablu
     */
	public void setVrijednost(BigDecimal vrijednost) {
		this.vrijednost = vrijednost;
	}

	public RadSenzora getRadSenzora() {
		return radSenzora;
	}

	public void setRadSenzora(RadSenzora radSenzora) {
		this.radSenzora = radSenzora;
	}

	public MjernaPostaja getMjernaPostaja() {
		return mjernaPostaja;
	}

	public void setMjernaPostaja(MjernaPostaja mjernaPostaja) {
		this.mjernaPostaja = mjernaPostaja;
	}

	/**
     * Dohvaća sve podatke senzora
     */
	public abstract String dohvatiPodatkeSenzora();
}
