package hr.java.vjezbe.glavna;

/**
 * Sadrži logiku za dodjeljivanje mjernih jedinica i preciznosti pojedinom senzoru prema unosu korisnika
 *
 * @author Marko
 * @version 1.0
 */
public class SharedMetodeSenzora {
    /**
     * Sadrži logiku za odabir mjerne jedinice senzora temperature prema unosu korisnika
     * @param redniBroj podatak koji predstavlja uvjet po kojem će izvršavati switch petlja
     * @return mjerna jedinica senzora temperature
     */
    public static String jedinicaTemperature(Integer redniBroj){
        String mjernaJedinica = "";

        switch(redniBroj){
            case 1:
                mjernaJedinica = "C";
                break;

            case 2:
                mjernaJedinica = "K";
                break;

            case 3:
                mjernaJedinica = "F";
                break;

            case 4:
                mjernaJedinica = "R";
                break;
        }
        return mjernaJedinica;
    }

    /**
     * Sadrži logiku za odabir preciznosti senzora temperature prema rezultatu metode jedinicaTemperature
     * @param mjernaJedinica podatak koji predstavlja uvjet po kojem će se izvršavati switch petlja
     * @return preciznost senzora temperature
     */
    public static Double preciznostTemperature(String mjernaJedinica){
        Double preciznost = null;

        switch(mjernaJedinica){
            case "C":
                preciznost = 2.0;
                break;

            case "K":
                preciznost = 275.15;
                break;

            case "F":
                preciznost = 35.6;
                break;

            case "R":
                preciznost = 495.27;
                break;
        }
        return preciznost;
    }

    /**
     * Sadrži logiku za odabir mjerne jedinice senzora globalnog zračenja prema unosu korisnika
     * @param redniBroj podatak koji predstavlja uvjet po kojem će izvršavati switch petlja
     * @return mjerna jedinica senzora globalnog zračenja
     */
    public static String jedinicaGlobalnogZracenja(Integer redniBroj){
        String mjernaJedinica = "";

        switch(redniBroj){
            case 1:
                mjernaJedinica = "W/m";
                break;

            case 2:
                mjernaJedinica = "mV";
                break;

                default:mjernaJedinica = null;
        }
        return mjernaJedinica;
    }

    /**
     * Sadrži logiku za odabir preciznosti senzora globalnog zračenja prema rezultatu metode jedinicaGlobalnogZračenja
     * @param mjernaJedinica podatak koji predstavlja uvjet po kojem će se izvršavati switch petlja
     * @return preciznost senzora globalnog zračenja
     */
    public static Double preciznostGlobalnogZracenja(String mjernaJedinica){
        Double preciznost = 0.0;

        switch(mjernaJedinica){
            case "W/m":
                preciznost = 5.0;
                break;

            case "mV":
                preciznost = 0.5;
                break;
                default:preciznost = null;
        }
        return preciznost;
    }

    /**
     * Sadrži logiku za odabir mjerne jedinice senzora vlage
     * @param redniBroj podatak koji predstavlja uvjet po kojem će izvršavati switch petlja
     * @return mjerna jedinica senzora vlage
     */    public static String jedinicaVlage(Integer redniBroj){
        String mjernaJedinica = "";

        switch(redniBroj){
            case 1:
                mjernaJedinica = "%";
                break;
        }
        return mjernaJedinica;
    }

    /**
     * Sadrži logiku za odabir preciznosti senzora vlage prema rezultatu metode jedinicaVlage
     * @param mjernaJedinica podatak koji predstavlja uvjet po kojem će se izvršavati switch petlja
     * @return preciznost senzora vlage
     */
    public static Double preciznostVlage(String mjernaJedinica){
        Double preciznost = null;

        switch(mjernaJedinica){
            case "%":
                preciznost = 2.0;
                break;
        }
        return preciznost;
    }
}
