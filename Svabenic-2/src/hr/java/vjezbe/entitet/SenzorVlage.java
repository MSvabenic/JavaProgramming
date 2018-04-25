package hr.java.vjezbe.entitet;

public class SenzorVlage extends Senzor {

	public SenzorVlage(String mjernaJedinica, Double preciznost) {
		super(mjernaJedinica,preciznost);
	}

	@Override
	public String dohvatiPodatkeSenzora() {

		System.out.printf("Vrijednost senzora vlage: %f %s.%n",
				getVrijednost(), getMjernaJedinica());

		return getVrijednost() + getMjernaJedinica();

	}

}
