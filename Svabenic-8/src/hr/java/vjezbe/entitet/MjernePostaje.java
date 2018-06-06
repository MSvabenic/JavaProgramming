package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MjernePostaje<T extends MjernaPostaja> {

    private List<T> listaMjernihPostaja;

    public MjernePostaje() {
        listaMjernihPostaja = new ArrayList<>();
    }

    public T get(Integer i) {

        T mjernaPostaja = null;

        for (i = 0; i < listaMjernihPostaja.size(); i++) {
           mjernaPostaja = listaMjernihPostaja.get(i);
        }
        return mjernaPostaja;
    }

    public void add(T mjernaPostaja){
        listaMjernihPostaja.add(mjernaPostaja);
    }

    public List<T> getSortedList(){
        listaMjernihPostaja.sort(Comparator.comparing(T::getNaziv));
        return listaMjernihPostaja;
    }
}
