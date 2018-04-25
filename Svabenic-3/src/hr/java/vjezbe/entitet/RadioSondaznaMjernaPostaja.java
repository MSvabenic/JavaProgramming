package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet radiosondažna mjerna postaja, opisan atributima nadklase
 * i atributom visina
 *
 * @author Marko
 * @version 1.0
 */
public class RadioSondaznaMjernaPostaja extends MjernaPostaja implements RadioSondazna{

    private Integer visinaPostaje;

    /**
     * Inicijalizira podatke radiosondažne mjerne postaje, nadklasi šalje podatke naziv, mjesto i geografska točka
     * @param visinaPostaje podatak koji predstavlja visinu postaje
     * @param naziv podatak koji predstavlja naziv postaje
     * @param mjesto podatak koji predstavlja mjesto postaje
     * @param geografskaTocka podatak koji predstavlja geografsku točku postaje
     * @param senzori podatak koji predstavlja polje senzora postaje
     */
    public RadioSondaznaMjernaPostaja(Integer visinaPostaje, String naziv, Mjesto mjesto, GeografskaTocka geografskaTocka, Senzor[] senzori) {
        super(naziv, mjesto, geografskaTocka, senzori);
        this.visinaPostaje = visinaPostaje;
    }

    /**
     * Postavlja vrijednost visine postaje u lokalnu varijablu
     */
    @Override
    public void podesiVisinuPostaje(Integer visinaPostaje) {
        this.visinaPostaje = visinaPostaje;
    }

    /**
     * Dohvaća visinu postaje
     */
    @Override
    public Integer dohvatiVisinuPostaje() {
        return visinaPostaje;
    }

}