/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klase;

import baza.Database;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author iq skola
 */
public class KorisnikBaza implements Baza<Korisnik> {

    @Override
    public Korisnik save(Korisnik model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Korisnik model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Korisnik find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Korisnik> all() {
        ArrayList<Korisnik> korisnici = new ArrayList<>();
        try {

            String query = "SELECT * "
                    + "FROM korisnici";

            Database db = Database.getInstance();
            ResultSet rs = db.select(query);

            while (rs.next()) {
                Korisnik korisnik = new Korisnik("korisnici", rs.getString("tip"));

                korisnik.setId(rs.getInt("id"));
                korisnik.setIme(rs.getString("ime"));
                korisnik.setPrezime(rs.getString("prezime"));
                korisnik.setKorisnickoIme(rs.getString("korisnicko_ime"));
                korisnik.setLozinka(rs.getString("lozinka"));

                korisnici.add(korisnik);

            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return korisnici;

    }

}
