package posao;

public abstract class Oglas implements Pretrazivo {
    private String naslov;
    private String lokacija;
    private double plata;
    private Poslodavac poslodavac;

    public Oglas(String naslov, String lokacija, double plata, Poslodavac poslodavac) {
        setNaslov(naslov);
        setLokacija(lokacija);
        setPlata(plata);
        if (poslodavac == null) {
            throw new IllegalArgumentException("Oglas mora imati poslodavca.");
        }
        this.poslodavac = poslodavac;
    }

    public String getNaslov() { return naslov; }
    public String getLokacija() { return lokacija; }
    public double getPlata() { return plata; }
    public Poslodavac getPoslodavac() { return poslodavac; }

    public void setNaslov(String naslov) {
        if (naslov == null || naslov.trim().isEmpty()) {
            throw new IllegalArgumentException("Naslov ne sme biti prazan.");
        }
        this.naslov = naslov.trim();
    }

    public void setLokacija(String lokacija) {
        if (lokacija == null || lokacija.trim().isEmpty()) {
            throw new IllegalArgumentException("Lokacija ne sme biti prazna.");
        }
        this.lokacija = lokacija.trim();
    }

    public void setPlata(double plata) {
        if (plata < 0) {
            throw new IllegalArgumentException("Plata ne moze biti negativna.");
        }
        this.plata = plata;
    }

    public abstract double mesecnaPlata();

    public abstract String tipPosla();

    @Override
    public boolean odgovaraUpitu(String upit) {
        String u = upit.toLowerCase();
        return naslov.toLowerCase().contains(u)
                || lokacija.toLowerCase().contains(u)
                || poslodavac.getFirma().toLowerCase().contains(u);
    }

    @Override
    public String toString() {
        return "[" + tipPosla() + "] " + naslov + " - " + poslodavac.getFirma()
                + ", " + lokacija + ", " + String.format("%.0f", mesecnaPlata()) + " EUR/mes.";
    }
}
