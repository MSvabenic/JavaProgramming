package hr.java.vjezbe.entitet;

public class SenzorTemperature extends Senzor{

	private String elektronickaKomponenta;

	public SenzorTemperature(String elektronickaKomponenta, String mjernaJedinica, Double preciznost){
		super(mjernaJedinica,preciznost);
		this.elektronickaKomponenta = elektronickaKomponenta;
	}

	public String getElektronickaKomponenta() {
		return elektronickaKomponenta;
	}

	public void setElektronickaKomponenta(String elektronickaKomponenta) {
		this.elektronickaKomponenta = elektronickaKomponenta;
	}

	@Override
	public String dohvatiPodatkeSenzora() {

	    System.out.printf("Naziv elektroničke komponente: %s.%n" , getElektronickaKomponenta());
        System.out.printf("Vrijednost senzora temperature: %f %s.%n",
                getVrijednost(), getMjernaJedinica());

        return getElektronickaKomponenta() + getVrijednost() + getMjernaJedinica();

	}
}
