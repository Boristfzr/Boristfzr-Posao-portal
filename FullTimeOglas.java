package posao;

public class FullTimeOglas extends Oglas {

    public FullTimeOglas(String naslov, String lokacija, double plata, Poslodavac poslodavac) {
        super(naslov, lokacija, plata, poslodavac);
    }

    @Override
    public double mesecnaPlata() {
        return getPlata();
    }

    @Override
    public String tipPosla() {
        return "Puno radno vreme";
    }
}
