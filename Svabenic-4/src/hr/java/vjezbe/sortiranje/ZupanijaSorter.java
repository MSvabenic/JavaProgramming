package hr.java.vjezbe.sortiranje;

import hr.java.vjezbe.entitet.Zupanija;

import java.util.Comparator;

public class ZupanijaSorter implements Comparator<Zupanija>{

    @Override
    public int compare(Zupanija zupanija1, Zupanija zupanija2) {
     return zupanija1.getNaziv().compareTo(zupanija2.getNaziv());
    }
}
