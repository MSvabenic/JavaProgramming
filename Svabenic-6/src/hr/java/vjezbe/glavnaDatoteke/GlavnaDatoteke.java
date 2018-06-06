package hr.java.vjezbe.glavnaDatoteke;

import hr.java.vjezbe.entitet.*;
import hr.java.vjezbe.glavna.SharedIspis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlavnaDatoteke {
    private static MjernePostaje<MjernaPostaja> postaje = new MjernePostaje<>();
    private static Map<Mjesto, List<Senzor>> mjestoSenzori = new HashMap<>();

    public static void main(String[] args){

        UnosPodataka.dohvatiPodatkeZaIspis();

        for (MjernaPostaja mjernaPostaja : UnosPodataka.dohvatiPostaje().values()) {
            postaje.add(mjernaPostaja);
        }

        IspisPodataka.ispisiPodatkeMjernePostaje(postaje);
        IspisPodataka.sortirajIspisiZupanije(postaje);
        IspisPodataka.ispisiMjestaISenzore(UnosPodataka.dohvatiMjestaISenzore());
    }
}
