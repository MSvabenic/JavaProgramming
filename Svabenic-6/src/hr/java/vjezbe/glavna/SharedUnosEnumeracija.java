package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.RadSenzora;
import hr.java.vjezbe.entitet.VrstaMjesta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SharedUnosEnumeracija {
    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

    public static RadSenzora unosRadaSenzora(Scanner skener){

        Integer brojRadaSenzora = null;
        RadSenzora radSenzora;
        boolean isTrue;

        for (int i = 0; i < RadSenzora.values().length - 1; i++) {
            System.out.println((i + 1) + ". " + RadSenzora.values()[i]);
        }

        do {
            System.out.print("Odaberite rad senzora: ");
            try {
                brojRadaSenzora = skener.nextInt();
                skener.nextLine();
                isTrue = false;
            }catch (InputMismatchException ex) {
                System.out.println("Unos mora biti broj! Ponovite unos!");
                logger.error("Neispravan unos rada senzora.", ex);
                skener.nextLine();
                isTrue = true;
            }
        }while (isTrue);

        if (brojRadaSenzora >= 1 && brojRadaSenzora < RadSenzora.values().length) {
            radSenzora = RadSenzora.values()[brojRadaSenzora - 1];
        } else {
           radSenzora = RadSenzora.OSTALO;
        }

        return radSenzora;
    }

    public static VrstaMjesta unosVrstaMjesta(Scanner skener){

        Integer brojVrstaMjesta = null;
        VrstaMjesta vrstaMjesta;
        boolean isTrue;

        //TO DO -> promjeniti logiku
        for (int i = 0; i < VrstaMjesta.values().length - 1; i++) {
            System.out.println((i + 1) + ". " + VrstaMjesta.values()[i]);
        }

        do {
            System.out.print("Odaberite vrstu mjesta: ");
            try {
                brojVrstaMjesta = skener.nextInt();
                skener.nextLine();
                isTrue = false;
            }catch (InputMismatchException ex) {
                System.out.println("Unos mora biti broj! Ponovite unos!");
                logger.error("Neispravan unos broja vrste mjesta.", ex);
                skener.nextLine();
                isTrue = true;
            }
        }while (isTrue);

        if (brojVrstaMjesta >= 1 && brojVrstaMjesta < RadSenzora.values().length) {
            vrstaMjesta = VrstaMjesta.values()[brojVrstaMjesta - 1];
        } else {
            vrstaMjesta = VrstaMjesta.OSTALO;
        }

        return vrstaMjesta;
    }
}
