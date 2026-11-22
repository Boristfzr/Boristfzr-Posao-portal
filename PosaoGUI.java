package posao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class PosaoGUI extends JFrame {

    private Portal portal = new Portal();
    private Kandidat kandidat;

    private DefaultListModel<Oglas> model = new DefaultListModel<>();
    private JList<Oglas> lista = new JList<>(model);
    private JTextField poljePretrage = new JTextField(20);
    private JLabel status = new JLabel(" ");

    public PosaoGUI(Kandidat kandidat) {
        super("Posao Portal - " + kandidat.getIme());
        this.kandidat = kandidat;
        napuniPrimerima();
        izgradiProzor();
        prikazi(portal.getOglasi());
    }

    private void napuniPrimerima() {
        Poslodavac firma1 = new Poslodavac("Marko Markovic", "marko@techsoft.rs", "TechSoft");
        Poslodavac firma2 = new Poslodavac("Ana Anic", "ana@kafeterija.rs", "Kafeterija");
        portal.dodajOglas(new FullTimeOglas("Java programer", "Beograd", 1800, firma1));
        portal.dodajOglas(new PartTimeOglas("Konobar", "Novi Sad", 700, firma2, 20));
        portal.dodajOglas(new PraksaOglas("Junior tester", "Beograd", 300, firma1, 3));
    }

    private void izgradiProzor() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        // gore: pretraga
        JPanel gore = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton dugmePretrazi = new JButton("Trazi");
        JButton dugmeSvi = new JButton("Prikazi sve");
        gore.add(new JLabel("Pretraga:"));
        gore.add(poljePretrage);
        gore.add(dugmePretrazi);
        gore.add(dugmeSvi);
        add(gore, BorderLayout.NORTH);

        // sredina: lista oglasa
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(lista), BorderLayout.CENTER);

        // dole: dugmad i status
        JPanel dole = new JPanel(new BorderLayout());
        JPanel dugmad = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton dugmePrijava = new JButton("Prijavi se na oglas");
        JButton dugmeMoje = new JButton("Moje prijave");
        JButton dugmeObjavi = new JButton("Objavi oglas");
        dugmad.add(dugmePrijava);
        dugmad.add(dugmeMoje);
        dugmad.add(dugmeObjavi);
        dole.add(dugmad, BorderLayout.NORTH);
        dole.add(status, BorderLayout.SOUTH);
        add(dole, BorderLayout.SOUTH);

        dugmePretrazi.addActionListener(e -> prikazi(portal.pretrazi(poljePretrage.getText().trim())));
        dugmeSvi.addActionListener(e -> {
            poljePretrage.setText("");
            prikazi(portal.getOglasi());
        });
        dugmePrijava.addActionListener(e -> prijaviSe());
        dugmeMoje.addActionListener(e -> prikaziMojePrijave());
        dugmeObjavi.addActionListener(e -> objaviOglas());
    }

    private void prikazi(ArrayList<Oglas> oglasi) {
        model.clear();
        for (Oglas o : oglasi) {
            model.addElement(o); // polimorfizam: toString() koristi mesecnaPlata() i tipPosla()
        }
        status.setText("Prikazano oglasa: " + oglasi.size());
    }

    private void prijaviSe() {
        Oglas izabran = lista.getSelectedValue();
        if (izabran == null) {
            JOptionPane.showMessageDialog(this, "Prvo izaberite oglas iz liste.");
            return;
        }
        try {
            Prijava p = portal.prijaviSe(kandidat, izabran);
            status.setText("Prijava poslata: " + p);
        } catch (IllegalStateException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void prikaziMojePrijave() {
        ArrayList<Prijava> moje = portal.prijaveKandidata(kandidat);
        if (moje.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nemate nijednu prijavu.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Prijava p : moje) {
            sb.append(p).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Moje prijave", JOptionPane.INFORMATION_MESSAGE);
    }

    private void objaviOglas() {
        JTextField ime = new JTextField();
        JTextField email = new JTextField();
        JTextField firma = new JTextField();
        JTextField naslov = new JTextField();
        JTextField lokacija = new JTextField();
        JTextField plata = new JTextField();
        JComboBox<String> tip = new JComboBox<>(new String[] { "Puno radno vreme", "Skraceno", "Praksa" });
        JTextField dodatno = new JTextField();

        JPanel forma = new JPanel(new GridLayout(0, 2, 5, 5));
        forma.add(new JLabel("Ime poslodavca:"));
        forma.add(ime);
        forma.add(new JLabel("Email:"));
        forma.add(email);
        forma.add(new JLabel("Firma:"));
        forma.add(firma);
        forma.add(new JLabel("Naslov:"));
        forma.add(naslov);
        forma.add(new JLabel("Lokacija:"));
        forma.add(lokacija);
        forma.add(new JLabel("Plata (EUR):"));
        forma.add(plata);
        forma.add(new JLabel("Tip:"));
        forma.add(tip);
        forma.add(new JLabel("Sati nedeljno / meseci:"));
        forma.add(dodatno);

        int rezultat = JOptionPane.showConfirmDialog(this, forma, "Objavi oglas",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (rezultat != JOptionPane.OK_OPTION) {
            return;
        }

        try {
            Poslodavac pos = new Poslodavac(ime.getText(), email.getText(), firma.getText());
            double p = Double.parseDouble(plata.getText().trim());
            Oglas oglas;
            switch (tip.getSelectedIndex()) {
                case 0:
                    oglas = new FullTimeOglas(naslov.getText(), lokacija.getText(), p, pos);
                    break;
                case 1:
                    oglas = new PartTimeOglas(naslov.getText(), lokacija.getText(), p, pos,
                            Integer.parseInt(dodatno.getText().trim()));
                    break;
                default:
                    oglas = new PraksaOglas(naslov.getText(), lokacija.getText(), p, pos,
                            Integer.parseInt(dodatno.getText().trim()));
            }
            portal.dodajOglas(oglas);
            prikazi(portal.getOglasi());
            status.setText("Oglas objavljen: " + oglas.getNaslov());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Plata i sati/meseci moraju biti brojevi.",
                    "Greska", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static Kandidat prijava() {
        JTextField ime = new JTextField();
        JTextField email = new JTextField();
        Object[] polja = { "Ime:", ime, "Email:", email };
        while (true) {
            int r = JOptionPane.showConfirmDialog(null, polja, "Prijava", JOptionPane.OK_CANCEL_OPTION);
            if (r != JOptionPane.OK_OPTION) {
                return null;
            }
            try {
                return new Kandidat(ime.getText(), email.getText(), 0);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Kandidat k = prijava();
            if (k == null) {
                System.exit(0);
            }
            new PosaoGUI(k).setVisible(true);
        });
    }
}
