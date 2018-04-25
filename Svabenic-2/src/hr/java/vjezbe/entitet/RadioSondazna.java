package hr.java.vjezbe.entitet;

public interface RadioSondazna {

    void podesiVisinuPostaje(Integer trenutnaVisinaPostaje);

    Integer dohvatiVisinuPostaje();

    private Integer provjeriVisinu(Integer trenutnaVisinaPostaje){
        if(dohvatiVisinuPostaje() > 1000)
        {
            trenutnaVisinaPostaje = 1000;
            return trenutnaVisinaPostaje;
        }
        return trenutnaVisinaPostaje + 1;
    }

    default void povecajVisinu(Integer trenutnaVisinaPostaje){
        podesiVisinuPostaje(provjeriVisinu(trenutnaVisinaPostaje));
    }

}