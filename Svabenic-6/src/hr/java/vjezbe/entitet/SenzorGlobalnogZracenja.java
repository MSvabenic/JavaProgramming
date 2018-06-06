package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet senzora globalnog zračenja opisanog atributima nadklase i atributom tip podsenzora
 *
 * @author Marko
 * @version 1.0
 */
public class SenzorGlobalnogZracenja extends Senzor{

	private String tipPodSenzora;

    /**
     * Inicijalizira podatke senzora globalnog zračenja, nadklasi šalje mjernu jedinicu i preciznost senzora
     * @param sifraSenzora podatak koji predstavlja sifru podsenzora
     * @param mjernaJedinica podatak koji predstavlja mjernu jedinicu senzora
     * @param preciznost podatak koji predstavlja preciznost senzora
     */
	public SenzorGlobalnogZracenja(String sifraSenzora,String mjernaJedinica, Double preciznost) {
		super(mjernaJedinica,preciznost);
		this.tipPodSenzora = sifraSenzora;
	}

    /**
     * Dohvaća šifru podsenzora
     */
	public String getSifraSenzora() {
		return tipPodSenzora;
	}

    /**
     * Postavlja vrijednost šifre podsenzora u lokalnu varijablu
     */
	public void setSifraSenzora(String sifraSenzora) {
		this.tipPodSenzora = sifraSenzora;
	}

    /**
     * Dohvaća podatke senzora globalnog zračenja, šifra podsenzora, vrijednost i mjerna jedinica
     */
	@Override
	public String dohvatiPodatkeSenzora() {

		System.out.printf("Šifra senzora globalnog zračenja: %s.%n" , getSifraSenzora());
		System.out.printf("Vrijednost senzora globalnog zračenja: %f %s.%n",
                getVrijednost(), getMjernaJedinica());

		System.out.printf("Rad senzora globalnog zračenja: %s.%n",
				getRadSenzora());

		return getSifraSenzora() + getVrijednost() + getMjernaJedinica() + getRadSenzora();
	}

	@Override
	public String toString() {
		return "Senzor globalnog zračenja ";
	}
}
