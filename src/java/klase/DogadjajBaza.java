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

/**
 *
 * @author iq skola
 */
public class DogadjajBaza implements Baza<Dogadjaj> {

    @Override
    public Dogadjaj save(Dogadjaj dogadjaj) {
        int id = dogadjaj.getId() > 0 ? update(dogadjaj) : insert(dogadjaj);
        return find(id);
    }

    private static int insert(Dogadjaj dogadjaj) {
        String query = "INSERT INTO dogadjaji (naziv,naziv_lokacije,datum_i_vreme,detalji,"
                + "glavna_slika_putanja,video_putanja) VALUES(?,?,?,?,?,?)";
        ArrayList<Object> vrednosti = new ArrayList();

        vrednosti.add(dogadjaj.getNaziv());
        vrednosti.add(dogadjaj.getNazivLokacije());
        vrednosti.add(dogadjaj.getDatumIVreme());
        vrednosti.add(dogadjaj.getDetalji());
        vrednosti.add(dogadjaj.getGlavnaSlikaPutanja());
        vrednosti.add(dogadjaj.getVideoPutanja());

        Database db = Database.getInstance();
        int id = db.insert(query, vrednosti);

        return id;
    }

    private static int update(Dogadjaj dogadjaj) {

        String query = "UPDATE dogadjaji SET naziv = ?, naziv_lokacije = ?, datum_i_vreme = ?,"
                + " detalji = ?, glavna_slika_putanja = ?, video_putanja = ?"
                + " WHERE dogadjaj_id = ?";
        ArrayList<Object> vrednosti = new ArrayList();
        vrednosti.add(dogadjaj.getNaziv());
        vrednosti.add(dogadjaj.getNazivLokacije());
        vrednosti.add(dogadjaj.getDatumIVreme());
        vrednosti.add(dogadjaj.getDetalji());
        vrednosti.add(dogadjaj.getGlavnaSlikaPutanja());
        vrednosti.add(dogadjaj.getVideoPutanja());
        vrednosti.add(dogadjaj.getId());

        Database db = Database.getInstance();
        boolean uspesno = db.update(query, vrednosti);

        return uspesno ? dogadjaj.getId() : -1;

    }

    @Override
    public Dogadjaj find(int id) {
        Dogadjaj dogadjaj = new Dogadjaj();
        try {

            String query = "SELECT * FROM `dogadjaji` WHERE dogadjaj_id = " + id;
            Database db = Database.getInstance();
            ResultSet rs = db.select(query);

            if (rs.first()) {
                dogadjaj.setId(rs.getInt("dogadjaj_id"));
                dogadjaj.setNaziv(rs.getString("naziv"));
                dogadjaj.setNazivLokacije(rs.getString("naziv_lokacije"));
                //nisam uspela sama da nadjem, tj nisam sigurna sta sam nasla :D
                dogadjaj.setDatumIVreme(rs.getTimestamp("datum_i_vreme").toLocalDateTime());
                dogadjaj.setDetalji(rs.getString("detalji"));
                dogadjaj.setGlavnaSlikaPutanja(rs.getString("glavna_slika_putanja"));
                dogadjaj.setVideoPutanja(rs.getString("video_putanja"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return dogadjaj;

    }

    @Override
    public boolean delete(Dogadjaj dogadjaj) {
        String query = "DELETE FROM dogadjaji WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();

        vrednosti.add(dogadjaj.getId());

        Database db = Database.getInstance();
        boolean uspesno = db.delete(query, vrednosti);

        return uspesno;
    }

    public static boolean delete(int id) {
        String query = "DELETE FROM dogadjaji WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();

        vrednosti.add(id);

        Database db = Database.getInstance();
        boolean uspesno = db.delete(query, vrednosti);

        return uspesno;
    }

    @Override
    public ArrayList<Dogadjaj> all() {
        ArrayList<Dogadjaj> dogadjaji = new ArrayList<>();
        try {

            String query = "SELECT * "
                    + "FROM dogadjaji";

            Database db = Database.getInstance();
            ResultSet rs = db.select(query);

            while (rs.next()) {
                Dogadjaj dogadjaj = new Dogadjaj();

                dogadjaj.setId(rs.getInt("dogadjaj_id"));
                dogadjaj.setNaziv(rs.getString("naziv"));
                dogadjaj.setNazivLokacije(rs.getString("naziv_lokacije"));
                //nisam uspela sama da nadjem, tj nisam sigurna sta sam nasla :D
                dogadjaj.setDatumIVreme(rs.getTimestamp("datum_i_vreme").toLocalDateTime());
                dogadjaj.setDetalji(rs.getString("detalji"));
                dogadjaj.setGlavnaSlikaPutanja(rs.getString("glavna_slika_putanja"));
                dogadjaj.setVideoPutanja(rs.getString("video_putanja"));

                dogadjaji.add(dogadjaj);

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return dogadjaji;
    }
}
