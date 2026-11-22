package posao;

public class PartTimeOglas extends Oglas {
    private int satiNedeljno;

    public PartTimeOglas(String naslov, String lokacija, double plata, Poslodavac poslodavac, int satiNedeljno) {
        super(naslov, lokacija, plata, poslodavac);
        setSatiNedeljno(satiNedeljno);
    }

    public void setSatiNedeljno(int sati) {
        if (sati <= 0 || sati > 40) {
            throw new IllegalArgumentException("Sati nedeljno moraju biti izmedju 1 i 40.");
        }
        this.satiNedeljno = sati;
    }

    // plata je navedena za puno radno vreme (40h), pa se srazmerno umanjuje
    @Override
    public double mesecnaPlata() {
        return getPlata() * satiNedeljno / 40.0;
    }

    @Override
    public String tipPosla() {
        return "Skraceno (" + satiNedeljno + "h/ned.)";
    }
}
