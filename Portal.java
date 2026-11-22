package posao;

import java.util.ArrayList;

public class Portal {
    private ArrayList<Oglas> oglasi = new ArrayList<>();
    private ArrayList<Prijava> prijave = new ArrayList<>();

    public void dodajOglas(Oglas oglas) {
        oglasi.add(oglas);
    }

    public ArrayList<Oglas> getOglasi() {
        return new ArrayList<>(oglasi);
    }

    public ArrayList<Oglas> pretrazi(String upit) {
        ArrayList<Oglas> rezultat = new ArrayList<>();
        for (Oglas o : oglasi) {
            if (o.odgovaraUpitu(upit)) {
                rezultat.add(o);
            }
        }
        return rezultat;
    }

    public Prijava prijaviSe(Kandidat kandidat, Oglas oglas) {
        for (Prijava p : prijave) {
            if (p.getKandidat() == kandidat && p.getOglas() == oglas) {
                throw new IllegalStateException("Vec ste se prijavili na ovaj oglas.");
            }
        }
        Prijava nova = new Prijava(kandidat, oglas);
        prijave.add(nova);
        return nova;
    }

    public ArrayList<Prijava> prijaveKandidata(Kandidat kandidat) {
        ArrayList<Prijava> rezultat = new ArrayList<>();
        for (Prijava p : prijave) {
            if (p.getKandidat() == kandidat) {
                rezultat.add(p);
            }
        }
        return rezultat;
    }
}
