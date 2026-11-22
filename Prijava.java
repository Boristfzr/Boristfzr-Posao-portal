package posao;

public class Prijava {
    private Kandidat kandidat;
    private Oglas oglas;
    private String status = "Na cekanju";

    public Prijava(Kandidat kandidat, Oglas oglas) {
        if (kandidat == null || oglas == null) {
            throw new IllegalArgumentException("Kandidat i oglas su obavezni.");
        }
        this.kandidat = kandidat;
        this.oglas = oglas;
    }

    public Kandidat getKandidat() { return kandidat; }
    public Oglas getOglas() { return oglas; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        if (!status.equals("Na cekanju") && !status.equals("Prihvacena") && !status.equals("Odbijena")) {
            throw new IllegalArgumentException("Nepoznat status: " + status);
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return kandidat.getIme() + " -> " + oglas.getNaslov() + " (" + status + ")";
    }
}
