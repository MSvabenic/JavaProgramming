package hr.java.vjezbe.entitet;

public class RadioSondaznaMjernaPostaja extends MjernaPostaja implements RadioSondazna{

    private Integer visinaPostaje;

    public RadioSondaznaMjernaPostaja(Integer visinaPostaje, String naziv, Mjesto mjesto, GeografskaTocka geografskaTocka, Senzor[] senzori) {
        super(naziv, mjesto, geografskaTocka, senzori);
        this.visinaPostaje = visinaPostaje;
    }

    @Override
    public void podesiVisinuPostaje(Integer visinaPostaje) {
        this.visinaPostaje = visinaPostaje;
    }

    @Override
    public Integer dohvatiVisinuPostaje() {
        return visinaPostaje;
    }

}