package hr.java.vjezbe.entitet;

public class SenzorGlobalnogZracenja extends Senzor{

	private String tipPodSenzora;
	
	public SenzorGlobalnogZracenja(String sifraSenzora,String mjernaJedinica, Double preciznost) {
		super(mjernaJedinica,preciznost);
		this.tipPodSenzora = sifraSenzora;
	}

	public String getSifraSenzora() {
		return tipPodSenzora;
	}

	public void setSifraSenzora(String sifraSenzora) {
		this.tipPodSenzora = sifraSenzora;
	}

	@Override
	public String dohvatiPodatkeSenzora() {

		System.out.printf("Šifra senzora globalnog zračenja: %s.%n" , getSifraSenzora());
		System.out.printf("Vrijednost senzora globalnog zračenja: %f %s.%n",
                getVrijednost(), getMjernaJedinica());

		return getSifraSenzora() + getVrijednost() + getMjernaJedinica();
	}
	
}
