package hr.java.vjezbe.glavna;

public class SharedMetodeSenzora {

    //switch petlja za odabir mjerne jedinice temperature
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

    //switch petlja za odabir mjerenja preciznosti temperature
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

    //switch petlja za odabir mjerne jedinice globalnog zracenja
    public static String jedinicaGlobalnogZracenja(Integer redniBroj){
        String mjernaJedinica = "";

        switch(redniBroj){
            case 1:
                mjernaJedinica = "W/m";
                break;

            case 2:
                mjernaJedinica = "mV";
                break;
        }
        return mjernaJedinica;
    }

    //switch petlja za odabir preciznosti mjerenja globalnog zracenja
    public static Double preciznostGlobalnogZracenja(String mjernaJedinica){
        Double preciznost = 0.0;

        switch(mjernaJedinica){
            case "W/m":
                preciznost = 5.0;
                break;

            case "mV":
                preciznost = 0.5;
                break;
        }
        return preciznost;
    }


    //switch petlja za odabir mjerne jedinice vlage
    public static String jedinicaVlage(Integer redniBroj){
        String mjernaJedinica = "";

        switch(redniBroj){
            case 1:
                mjernaJedinica = "%";
                break;
        }
        return mjernaJedinica;
    }

    //switch petlja za odabir preciznosti mjerenja vlage
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
