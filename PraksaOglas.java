package posao;

public class PraksaOglas extends Oglas {
    private int trajanjeMeseci;

    public PraksaOglas(String naslov, String lokacija, double stipendija, Poslodavac poslodavac, int trajanjeMeseci) {
        super(naslov, lokacija, stipendija, poslodavac);
        setTrajanjeMeseci(trajanjeMeseci);
    }

    public void setTrajanjeMeseci(int meseci) {
        if (meseci <= 0) {
            throw new IllegalArgumentException("Praksa mora trajati bar 1 mesec.");
        }
        this.trajanjeMeseci = meseci;
    }

    @Override
    public double mesecnaPlata() {
        return getPlata(); // stipendija
    }

    @Override
    public String tipPosla() {
        return "Praksa (" + trajanjeMeseci + " mes.)";
    }
}
