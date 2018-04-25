package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.RadSenzora;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Predstavlja metode za validaciju unosa BigDecimal i Integer tipa podatka
 */
public class SharedValidacijaUnosa {
    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

    /**
     * Vrši provjeru unosa korisnika, da li je tipa Integer
     * @param skener podatak koji predstavlja klasu Scanner
     * @return vrijednost broja
     */
    public static Integer provjeraIntegera(Scanner skener)
    {
        Integer broj = null;
        boolean isInteger;
        do {
            try {
                broj = skener.nextInt();
                skener.nextLine();
                isInteger = false;
            }catch (InputMismatchException ex){
                logger.info(ex.getMessage(), ex);
                System.out.println("Potrebno je unijeti brojčanu vrijednost!");
                skener.nextLine();
                isInteger = true;
            }
        }while(isInteger);

        return broj;
    }

    /**
     * Vrši provjeru unosa korisnika, da li je tipa BigDecimal
     * @param skener predstavlja klasu Scanner
     * @return vrijednost broja
     */
    public static BigDecimal provjeraBigDecimala(Scanner skener)
    {
        BigDecimal broj = null;
        boolean isBigDecimal;
        do {
            try {
                broj = skener.nextBigDecimal();
                skener.nextLine();
                isBigDecimal = false;
            }catch (InputMismatchException ex){
                logger.info(ex.getMessage(), ex);
                System.out.println("Potrebno je unijeti brojčanu vrijednost!");
                skener.nextLine();
                isBigDecimal = true;
            }
        }while(isBigDecimal);

        return broj;
    }
}
