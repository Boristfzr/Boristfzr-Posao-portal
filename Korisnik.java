package posao;

public abstract class Korisnik {
    private String ime;
    private String email;

    public Korisnik(String ime, String email) {
        setIme(ime);
        setEmail(email);
    }

    public String getIme() { return ime; }
    public String getEmail() { return email; }

    public void setIme(String ime) {
        if (ime == null || ime.trim().isEmpty()) {
            throw new IllegalArgumentException("Ime ne sme biti prazno.");
        }
        this.ime = ime.trim();
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email nije ispravan.");
        }
        this.email = email.trim();
    }

    public abstract String opisUloge();

    @Override
    public String toString() {
        return ime + " (" + opisUloge() + ")";
    }
}
