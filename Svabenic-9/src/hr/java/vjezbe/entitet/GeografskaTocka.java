package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * Predstavlja entitet geografska točka opisan atributima koordinata x i y
 *
 * @author Marko
 * @version 1.0
 */
public class GeografskaTocka extends BazniEntitet{

	private BigDecimal x;
	private BigDecimal y;

    /**
     * Inicijalizira podatke geografske točke
     * @param x podatak koji predstavlja x koordinatu
     * @param y podatak koji predstavlja y koordinatu
     */
	public GeografskaTocka(BigDecimal x, BigDecimal y) {
		super();
		this.x = x;
		this.y = y;
	}
	public GeografskaTocka(Integer id, BigDecimal x, BigDecimal y) {
		super(id);
		this.x = x;
		this.y = y;
	}


	/**
     * Dohvaća x koordinatu
     */
	public BigDecimal getX() {
		return x;
	}

    /**
     * Postavlja vrijednost x koordinate u lokalnu varijablu
     */
	public void setX(BigDecimal x) {
		this.x = x;
	}

    /**
     * Dohvaća y koordinatu
     */
	public BigDecimal getY() {
		return y;
	}

    /**
     * Postavlja vrijednost y koordinate u lokalnu varijablu
     */
	public void setY(BigDecimal y) {
		this.y = y;
	}
	
}