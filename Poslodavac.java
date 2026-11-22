package posao;

public class Poslodavac extends Korisnik {
    private String firma;

    public Poslodavac(String ime, String email, String firma) {
        super(ime, email);
        setFirma(firma);
    }

    public String getFirma() { return firma; }

    public void setFirma(String firma) {
        if (firma == null || firma.trim().isEmpty()) {
            throw new IllegalArgumentException("Naziv firme ne sme biti prazan.");
        }
        this.firma = firma.trim();
    }

    @Override
    public String opisUloge() {
        return "Poslodavac, firma: " + firma;
    }
}
