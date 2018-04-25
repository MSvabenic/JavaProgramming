package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public abstract class Senzor {

	private String mjernaJedinica;
	private Double preciznost;
	private BigDecimal vrijednost;

	public Senzor(String mjernaJedinica, Double preciznost) {
		super();
		this.mjernaJedinica = mjernaJedinica;
		this.preciznost = preciznost;
	}

	public String getMjernaJedinica() {
		return mjernaJedinica;
	}

	public void setMjernaJedinica(String mjernaJedinica) {
		this.mjernaJedinica = mjernaJedinica;
	}

	public Double getPreciznost() {
		return preciznost;
	}

	public void setPreciznost(Double preciznost) {
		this.preciznost = preciznost;
	}

	public BigDecimal getVrijednost() {
		return vrijednost;
	}

	public void setVrijednost(BigDecimal vrijednost) {
		this.vrijednost = vrijednost;
	}
	
	public abstract String dohvatiPodatkeSenzora();

}
