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
public class BlagajnikBaza implements Baza<Blagajnik> {

    @Override
    public Blagajnik save(Blagajnik blagajnik) {
        int id = blagajnik.getId() > 0 ? update(blagajnik) : insert(blagajnik);
        return find(id);
    }

    private static int insert(Blagajnik blagajnik) {
        String query = "INSERT INTO korisnici (tip,ime,prezime,korisnicko_ime,lozinka) VALUES(?,?,?,?,?)";
        ArrayList<Object> vrednosti = new ArrayList();

        vrednosti.add(blagajnik.getTip());
        vrednosti.add(blagajnik.getIme());
        vrednosti.add(blagajnik.getPrezime());
        vrednosti.add(blagajnik.getKorisnicko_ime());
        vrednosti.add(blagajnik.getLozinka());

        Database db = Database.getInstance();
        int id = db.insert(query, vrednosti);

        if (id > 0) {
            String query2 = "INSERT INTO blagajnici "
                    + "(korisnik_id,naziv_lokacije,grad_lokacije,adresa_lokacije)"
                    + " VALUES(?,?,?,?)";
            ArrayList<Object> vrednosti2 = new ArrayList();
            vrednosti2.add(id);
            vrednosti2.add(blagajnik.getNaziv_lokacije());
            vrednosti2.add(blagajnik.getGrad_lokacije());
            vrednosti2.add(blagajnik.getAdresa_lokacije());

            int id2 = db.insert(query2, vrednosti2);
            if (id2 <= 0) {
                id = -1;
            }

        }

        return id;
    }

    private static int update(Blagajnik blagajnik) {

        String query = "UPDATE korisnici SET tip = ?, ime = ?, prezime = ?,"
                + " korisnicko_ime = ?, lozinka = ? WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();
        vrednosti.add(blagajnik.getTip());
        vrednosti.add(blagajnik.getIme());
        vrednosti.add(blagajnik.getPrezime());
        vrednosti.add(blagajnik.getKorisnicko_ime());
        vrednosti.add(blagajnik.getLozinka());
        vrednosti.add(blagajnik.getId());

        Database db = Database.getInstance();
        boolean uspesno = db.update(query, vrednosti);

        if (uspesno) {
            String query2 = "UPDATE blagajnici SET naziv_lokacije = ?, grad_lokacije = ?,"
                    + " adresa_lokacije = ? WHERE korisnik_id = ?";
            ArrayList<Object> vrednosti2 = new ArrayList();

            vrednosti2.add(blagajnik.getNaziv_lokacije());
            vrednosti2.add(blagajnik.getGrad_lokacije());
            vrednosti2.add(blagajnik.getAdresa_lokacije());
            vrednosti2.add(blagajnik.getId());
            uspesno = uspesno && db.update(query2, vrednosti2);

        }
        return uspesno ? blagajnik.getId() : -1;

    }

    @Override
    public Blagajnik find(int id) {
        Blagajnik blagajnik = new Blagajnik();
        try {

            String query = "SELECT * FROM `korisnici` LEFT JOIN `blagajnici` "
                    + "ON korisnici.id = blagajnici.korisnik_id WHERE korisnici.id = " + id;
            Database db = Database.getInstance();
            ResultSet rs = db.select(query);

            if (rs.first()) {
                blagajnik.setId(rs.getInt("id"));
                blagajnik.setIme(rs.getString("ime"));
                blagajnik.setPrezime(rs.getString("prezime"));
                blagajnik.setKorisnicko_ime(rs.getString("korisnicko_ime"));
                blagajnik.setLozinka(rs.getString("lozinka"));
                blagajnik.setNaziv_lokacije(rs.getString("naziv_lokacije"));
                blagajnik.setGrad_lokacije(rs.getString("grad_lokacije"));
                blagajnik.setAdresa_lokacije(rs.getString("adresa_lokacije"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrovaniKorisnikBaza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blagajnik;

    }

    @Override
    public boolean delete(Blagajnik blagajnik) {
        String query = "DELETE FROM korisnici WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();

        vrednosti.add(blagajnik.getId());

        Database db = Database.getInstance();
        boolean uspesno = db.delete(query, vrednosti);

        if (uspesno) {
            String query2 = "DELETE FROM blagajnici WHERE korisnik_id = ?";
            ArrayList<Object> vrednosti2 = new ArrayList();
            vrednosti2.add(blagajnik.getId());
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
            String query2 = "DELETE FROM blagajnici WHERE korisnik_id = ?";
            ArrayList<Object> vrednosti2 = new ArrayList();
            vrednosti2.add(id);
            uspesno = uspesno && db.delete(query2, vrednosti2);
        }
        return uspesno;
    }

    @Override
    public ArrayList<Blagajnik> all() {
        ArrayList<Blagajnik> korisnici = new ArrayList<>();
        try {

            String query = "SELECT * "
                    + "FROM korisnici WHERE korisnici.tip = 'Blagajnik'";

            Database db = Database.getInstance();
            ResultSet rs = db.select(query);

            while (rs.next()) {
                Blagajnik blagajnik = new Blagajnik();

                blagajnik.setId(rs.getInt("id"));
                blagajnik.setIme(rs.getString("ime"));
                blagajnik.setPrezime(rs.getString("prezime"));
                blagajnik.setKorisnicko_ime(rs.getString("korisnicko_ime"));
                blagajnik.setLozinka(rs.getString("lozinka"));

                korisnici.add(blagajnik);

            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return korisnici;
    }
}
