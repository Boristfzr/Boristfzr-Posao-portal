package posao;

import java.util.ArrayList;

public class Kandidat extends Korisnik {
    private int godineIskustva;
    private ArrayList<String> vestine = new ArrayList<>();

    public Kandidat(String ime, String email, int godineIskustva) {
        super(ime, email);
        setGodineIskustva(godineIskustva);
    }

    public int getGodineIskustva() { return godineIskustva; }

    public void setGodineIskustva(int godine) {
        if (godine < 0 || godine > 60) {
            throw new IllegalArgumentException("Godine iskustva moraju biti izmedju 0 i 60.");
        }
        this.godineIskustva = godine;
    }

    public void dodajVestinu(String vestina) {
        if (vestina == null || vestina.trim().isEmpty()) {
            throw new IllegalArgumentException("Vestina ne sme biti prazna.");
        }
        vestine.add(vestina.trim());
    }

    public ArrayList<String> getVestine() { return new ArrayList<>(vestine); }

    @Override
    public String opisUloge() {
        return "Kandidat, iskustvo: " + godineIskustva + " god.";
    }
}
