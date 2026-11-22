package posao;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Portal portal = new Portal();
        Poslodavac firma1 = new Poslodavac("Marko Markovic", "marko@techsoft.rs", "TechSoft");
        Poslodavac firma2 = new Poslodavac("Ana Anic", "ana@kafeterija.rs", "Kafeterija");

        portal.dodajOglas(new FullTimeOglas("Java programer", "Beograd", 1800, firma1));
        portal.dodajOglas(new PartTimeOglas("Konobar", "Novi Sad", 700, firma2, 20));
        portal.dodajOglas(new PraksaOglas("Junior tester", "Beograd", 300, firma1, 3));

        Scanner sc = new Scanner(System.in);
        System.out.print("Unesite ime: ");
        String ime = sc.nextLine();
        System.out.print("Unesite email: ");
        String email = sc.nextLine();

        Kandidat kandidat;
        try {
            kandidat = new Kandidat(ime, email, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Greska: " + e.getMessage());
            return;
        }
        System.out.println("Dobrodosli, " + kandidat);

        int izbor = -1;
        while (izbor != 0) {
            System.out.println("\n1) Svi oglasi  2) Pretraga  3) Prijava na oglas  4) Moje prijave  5) Objavi oglas  0) Izlaz");
            System.out.print("Izbor: ");
            try {
                izbor = Integer.parseInt(sc.nextLine());
                switch (izbor) {
                    case 1:
                        prikazi(portal.getOglasi());
                        break;
                    case 2:
                        System.out.print("Pojam za pretragu: ");
                        prikazi(portal.pretrazi(sc.nextLine()));
                        break;
                    case 3:
                        ArrayList<Oglas> svi = portal.getOglasi();
                        prikazi(svi);
                        System.out.print("Redni broj oglasa: ");
                        int br = Integer.parseInt(sc.nextLine());
                        Prijava p = portal.prijaviSe(kandidat, svi.get(br - 1));
                        System.out.println("Prijava poslata: " + p);
                        break;
                    case 4:
                        for (Prijava pr : portal.prijaveKandidata(kandidat)) {
                            System.out.println(pr);
                        }
                        break;
                    case 5:
                        objaviOglas(sc, portal);
                        break;
                    case 0:
                        System.out.println("Dovidjenja!");
                        break;
                    default:
                        System.out.println("Nepoznata opcija.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Unesite broj.");
                izbor = -1;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Ne postoji oglas sa tim brojem.");
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Greska: " + e.getMessage());
            }
        }
    }

    private static void prikazi(ArrayList<Oglas> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nema rezultata.");
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i)); // polimorfizam
        }
    }

    private static void objaviOglas(Scanner sc, Portal portal) {
        System.out.print("Ime poslodavca: ");
        String ime = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Firma: ");
        Poslodavac pos = new Poslodavac(ime, email, sc.nextLine());

        System.out.print("Naslov: ");
        String naslov = sc.nextLine();
        System.out.print("Lokacija: ");
        String lokacija = sc.nextLine();
        System.out.print("Plata (EUR): ");
        double plata = Double.parseDouble(sc.nextLine());
        System.out.print("Tip (1 = puno, 2 = skraceno, 3 = praksa): ");
        int tip = Integer.parseInt(sc.nextLine());

        Oglas oglas;
        if (tip == 1) {
            oglas = new FullTimeOglas(naslov, lokacija, plata, pos);
        } else if (tip == 2) {
            System.out.print("Sati nedeljno: ");
            oglas = new PartTimeOglas(naslov, lokacija, plata, pos, Integer.parseInt(sc.nextLine()));
        } else {
            System.out.print("Trajanje (meseci): ");
            oglas = new PraksaOglas(naslov, lokacija, plata, pos, Integer.parseInt(sc.nextLine()));
        }
        portal.dodajOglas(oglas);
        System.out.println("Oglas objavljen: " + oglas);
    }
}
