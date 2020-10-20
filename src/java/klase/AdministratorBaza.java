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
public class AdministratorBaza implements Baza<Administrator> {

    @Override
    public Administrator save(Administrator administrator) {
        int id = administrator.getId() > 0 ? update(administrator) : insert(administrator);
        return find(id);
    }

    private int insert(Administrator administrator) {
        String query = "INSERT INTO korisnici (tip,ime,prezime,korisnicko_ime,lozinka) VALUES(?,?,?,?,?)";
        ArrayList<Object> vrednosti = new ArrayList();

        vrednosti.add(administrator.getTip());
        vrednosti.add(administrator.getIme());
        vrednosti.add(administrator.getPrezime());
        vrednosti.add(administrator.getKorisnickoIme());
        vrednosti.add(administrator.getLozinka());

        Database db = Database.getInstance();
        int id = db.insert(query, vrednosti);

        return id;
    }

    private int update(Administrator administrator) {

        String query = "UPDATE korisnici SET tip = ?, ime = ?, prezime = ?,"
                + " korisnicko_ime = ?, lozinka = ? WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();
        vrednosti.add(administrator.getTip());
        vrednosti.add(administrator.getIme());
        vrednosti.add(administrator.getPrezime());
        vrednosti.add(administrator.getKorisnickoIme());
        vrednosti.add(administrator.getLozinka());
        vrednosti.add(administrator.getId());

        Database db = Database.getInstance();
        boolean uspesno = db.update(query, vrednosti);

        return uspesno ? administrator.getId() : -1;

    }

    @Override
    public Administrator find(int id) {
        Administrator administrator = new Administrator();
        try {

            String query = "SELECT * FROM `korisnici` WHERE korisnici.id = " + id;
            Database db = Database.getInstance();
            ResultSet rs = db.select(query);

            if (rs.first()) {
                administrator.setId(rs.getInt("id"));
                administrator.setIme(rs.getString("ime"));
                administrator.setPrezime(rs.getString("prezime"));
                administrator.setKorisnickoIme(rs.getString("korisnicko_ime"));
                administrator.setLozinka(rs.getString("lozinka"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return administrator;

    }

    @Override
    public boolean delete(Administrator administrator) {
        String query = "DELETE FROM korisnici WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();

        vrednosti.add(administrator.getId());

        Database db = Database.getInstance();
        boolean uspesno = db.delete(query, vrednosti);

        return uspesno;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM korisnici WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();

        vrednosti.add(id);

        Database db = Database.getInstance();
        boolean uspesno = db.delete(query, vrednosti);

        return uspesno;
    }

    @Override
    public ArrayList<Administrator> all() {
        ArrayList<Administrator> korisnici = new ArrayList<>();
        try {

            String query = "SELECT * "
                    + "FROM korisnici WHERE korisnici.tip = 'Administrator'";

            Database db = Database.getInstance();
            ResultSet rs = db.select(query);

            while (rs.next()) {
                Administrator administrator = new Administrator();

                administrator.setId(rs.getInt("id"));
                administrator.setIme(rs.getString("ime"));
                administrator.setPrezime(rs.getString("prezime"));
                administrator.setKorisnickoIme(rs.getString("korisnicko_ime"));
                administrator.setLozinka(rs.getString("lozinka"));

                korisnici.add(administrator);

            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return korisnici;
    }
}
