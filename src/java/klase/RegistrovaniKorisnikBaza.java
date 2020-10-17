/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klase;

import baza.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iq skola
 */
public class RegistrovaniKorisnikBaza implements Baza<RegistrovaniKorisnik> {

    @Override
    public RegistrovaniKorisnik save(RegistrovaniKorisnik registrovaniKorisnik) {
        int id = registrovaniKorisnik.getId() > 0 ? update(registrovaniKorisnik) : insert(registrovaniKorisnik);
        return find(id);
    }

    private static int insert(RegistrovaniKorisnik registrovaniKorisnik) {
        String query = "INSERT INTO korisnici (tip,ime,prezime,korisnicko_ime,lozinka) VALUES(?,?,?,?,?)";
        ArrayList<Object> vrednosti = new ArrayList();

        vrednosti.add(registrovaniKorisnik.getTip());
        vrednosti.add(registrovaniKorisnik.getIme());
        vrednosti.add(registrovaniKorisnik.getPrezime());
        vrednosti.add(registrovaniKorisnik.getKorisnickoIme());
        vrednosti.add(registrovaniKorisnik.getLozinka());

        Database db = Database.getInstance();
        int id = db.insert(query, vrednosti);

        if (id > 0) {
            String query2 = "INSERT INTO registrovani_korisnici "
                    + "(korisnik_id,grad,adresa,kontakt_telefon,email)"
                    + " VALUES(?,?,?,?,?)";
            ArrayList<Object> vrednosti2 = new ArrayList();
            vrednosti2.add(id);
            vrednosti2.add(registrovaniKorisnik.getGrad());
            vrednosti2.add(registrovaniKorisnik.getAdresa());
            vrednosti2.add(registrovaniKorisnik.getKontaktTelefon());
            vrednosti2.add(registrovaniKorisnik.getEmail());

            int id2 = db.insert(query2, vrednosti2);
            if (id2 <= 0) {
                id = -1;
            }

        }

        return id;
    }

    private static int update(RegistrovaniKorisnik registrovaniKorisnik) {

        String query = "UPDATE korisnici SET tip = ?, ime = ?, prezime = ?,"
                + " korisnicko_ime = ?, lozinka = ? WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();
        vrednosti.add(registrovaniKorisnik.getTip());
        vrednosti.add(registrovaniKorisnik.getIme());
        vrednosti.add(registrovaniKorisnik.getPrezime());
        vrednosti.add(registrovaniKorisnik.getKorisnickoIme());
        vrednosti.add(registrovaniKorisnik.getLozinka());
        vrednosti.add(registrovaniKorisnik.getId());

        Database db = Database.getInstance();
        boolean uspesno = db.update(query, vrednosti);

        if (uspesno) {
            String query2 = "UPDATE registrovani_korisnici SET grad = ?, adresa = ?,"
                    + " kontakt_telefon = ?, email = ? WHERE korisnik_id = ?";
            ArrayList<Object> vrednosti2 = new ArrayList();

            vrednosti2.add(registrovaniKorisnik.getGrad());
            vrednosti2.add(registrovaniKorisnik.getAdresa());
            vrednosti2.add(registrovaniKorisnik.getKontaktTelefon());
            vrednosti2.add(registrovaniKorisnik.getEmail());
            vrednosti2.add(registrovaniKorisnik.getId());
            uspesno = uspesno && db.update(query2, vrednosti2);

        }
        return uspesno ? registrovaniKorisnik.getId() : -1;

    }

    @Override
    public RegistrovaniKorisnik find(int id) {
        RegistrovaniKorisnik registrovaniKorisnik = new RegistrovaniKorisnik();
        try {

            String query = "SELECT * FROM `korisnici` LEFT JOIN `registrovani_korisnici` "
                    + "ON korisnici.id = registrovani_korisnici.korisnik_id WHERE korisnici.id = " + id;
            Database db = Database.getInstance();
            ResultSet rs = db.select(query);

            if (rs.first()) {
                registrovaniKorisnik.setId(rs.getInt("id"));
                registrovaniKorisnik.setIme(rs.getString("ime"));
                registrovaniKorisnik.setPrezime(rs.getString("prezime"));
                registrovaniKorisnik.setKorisnickoIme(rs.getString("korisnicko_ime"));
                registrovaniKorisnik.setLozinka(rs.getString("lozinka"));
                registrovaniKorisnik.setGrad(rs.getString("grad"));
                registrovaniKorisnik.setAdresa(rs.getString("adresa"));
                registrovaniKorisnik.setKontaktTelefon(rs.getString("kontakt_telefon"));
                registrovaniKorisnik.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrovaniKorisnikBaza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registrovaniKorisnik;

    }

    @Override
    public boolean delete(RegistrovaniKorisnik registrovaniKorisnik) {
        String query = "DELETE FROM korisnici WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();

        vrednosti.add(registrovaniKorisnik.getId());

        Database db = Database.getInstance();
        boolean uspesno = db.delete(query, vrednosti);

        if (uspesno) {
            String query2 = "DELETE FROM registrovani_korisnici WHERE korisnik_id = ?";
            ArrayList<Object> vrednosti2 = new ArrayList();
            vrednosti2.add(registrovaniKorisnik.getId());
            uspesno = uspesno && db.delete(query2, vrednosti2);

        }
        return uspesno;
    }

    public static boolean delete(int id) {
        String query = "DELETE FROM korisnici WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();

        vrednosti.add(id);

        Database db = Database.getInstance();
        boolean uspesno = db.delete(query, vrednosti);

        if (uspesno) {
            String query2 = "DELETE FROM registrovani_korisnici WHERE korisnik_id = ?";
            ArrayList<Object> vrednosti2 = new ArrayList();
            vrednosti2.add(id);
            uspesno = uspesno && db.delete(query2, vrednosti2);
        }
        return uspesno;
    }

    @Override
    public ArrayList<RegistrovaniKorisnik> all() {
        ArrayList<RegistrovaniKorisnik> korisnici = new ArrayList<>();
        try {

            String query = "SELECT * "
                    + "FROM korisnici WHERE korisnici.tip = 'Registrovani korisnik'";

            Database db = Database.getInstance();
            ResultSet rs = db.select(query);

            while (rs.next()) {
                RegistrovaniKorisnik registrovaniKorisnik = new RegistrovaniKorisnik();

                registrovaniKorisnik.setId(rs.getInt("id"));
                registrovaniKorisnik.setIme(rs.getString("ime"));
                registrovaniKorisnik.setPrezime(rs.getString("prezime"));
                registrovaniKorisnik.setKorisnickoIme(rs.getString("korisnicko_ime"));
                registrovaniKorisnik.setLozinka(rs.getString("lozinka"));

                korisnici.add(registrovaniKorisnik);

            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return korisnici;
    }

}
