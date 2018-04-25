package hr.java.vjezbe.entitet;

/**
 * Predstavlja bazni entitet radiosondažnih mjernih postaja
 *
 * @author Marko
 * @version 1.0
 */
public interface RadioSondazna {
    /**
     * Podešava visinu radiosondažne postaje prema vrijednosti koju primi
     */
    void podesiVisinuPostaje(Integer trenutnaVisinaPostaje);

    /**
     * Dohvaća visinu radiosondažne postaje
     */
    Integer dohvatiVisinuPostaje();

    /**
     * Provjerava visinu radiosondažne postaje prema vrijednosti koju primi
     * Postavlja visinu prema if uvjetima
     * @param trenutnaVisinaPostaje podatak koji predstavlja trenutnu visinu postaje
     */
    private Integer provjeriVisinu(Integer trenutnaVisinaPostaje){
        if(dohvatiVisinuPostaje() > 1000)
        {
            trenutnaVisinaPostaje = 1000;
            return trenutnaVisinaPostaje;
        }
        return trenutnaVisinaPostaje + 1;
    }

    /**
     * Povećava visinu postaje uz pomoć metode provjeriVisinu
     * @param trenutnaVisinaPostaje podatak koji predstavlja trenutnu visinu postaje
     */
    default void povecajVisinu(Integer trenutnaVisinaPostaje){
        podesiVisinuPostaje(provjeriVisinu(trenutnaVisinaPostaje));
    }

}