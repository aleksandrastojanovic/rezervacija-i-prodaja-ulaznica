
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazaKlase;

import baza.Database;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modeli.Baza;
import modeli.Media;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author iq skola
 */
public class MediaBaza implements Baza<Media> {

    @Override
    public Media save(Media media) {
        int id = media.getId() > 0 ? update(media) : insert(media);
        return find(id);
    }

    private int insert(Media media) {
        String query = "INSERT INTO media (putanja,glavna,tip,dogadjaj_id,ime) VALUES(?,?,?,?,?)";

        ArrayList<Object> vrednosti = new ArrayList();
        vrednosti.add(media.getPutanja());
        vrednosti.add(media.isGlavna());
        vrednosti.add(media.getTip());
        vrednosti.add(media.getDogadjajId());
        vrednosti.add(media.getIme());

        Database db = Database.getInstance();
        int id = db.insert(query, vrednosti);

        // ako je uspesno snimljeno u bazu, snimi fajl
        if (id > 0) {
            media.setId(id);
        }
        try {
            snimiFajl(media.getFajl(), media.getPutanja());
        } catch (Exception e) {
            // ako nije snimljen fajl, izbrisi iz baze
            System.err.println(e);
            delete(id);
            id = -1;
            media.setId(id);
        }

        return id;
    }

    private int update(Media media) {

        String query = "UPDATE media SET putanja = ?, glavna = ?, tip = ?, dogadjaj_id = ?, ime = ? WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();
        vrednosti.add(media.getPutanja());
        vrednosti.add(media.isGlavna());
        vrednosti.add(media.getTip());
        vrednosti.add(media.getDogadjajId());
        vrednosti.add(media.getIme());
        vrednosti.add(media.getId());

        Database db = Database.getInstance();
        boolean uspesno = db.update(query, vrednosti);

        if (uspesno && media.getFajl() != null) {
            try {
                snimiFajl(media.getFajl(), media.getPutanja());
            } catch (Exception ex) {
                Logger.getLogger(MediaBaza.class.getName()).log(Level.SEVERE, null, ex);
                uspesno = false;
            }
        }
        return uspesno ? media.getId() : -1;
    }

    @Override
    public Media find(int id) {
        Media media = new Media();
        try {

            String query = "SELECT * FROM media WHERE id = " + id;
            Database db = Database.getInstance();
            ResultSet rs = db.select(query);

            if (rs.first()) {
                media.setId(rs.getInt("id"));
                media.setPutanja(rs.getString("putanja"));
                media.setGlavna(rs.getBoolean("glavna"));
                media.setTip(rs.getString("tip"));
                media.setDogadjajId(rs.getInt("dogadjaj_id"));
                media.setIme(rs.getString("ime"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MediaBaza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return media;

    }

    @Override
    public boolean delete(Media media) {
        return delete(media.getId());
    }

    public boolean delete(int id) {
        String query = "DELETE FROM media WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();
        vrednosti.add(id);
        Database db = Database.getInstance();
        boolean uspesno = db.delete(query, vrednosti);
        return uspesno;
    }

    @Override
    public ArrayList<Media> all() {
        ArrayList<Media> medie = new ArrayList<>();
        try {
            String query = "SELECT * FROM media";

            Database db = Database.getInstance();
            ResultSet rs = db.select(query);

            while (rs.next()) {
                Media media = new Media();
                media.setId(rs.getInt("id"));
                media.setPutanja(rs.getString("putanja"));
                media.setGlavna(rs.getBoolean("glavna"));
                media.setTip(rs.getString("tip"));
                media.setDogadjajId(rs.getInt("dogadjaj_id"));
                media.setIme(rs.getString("ime"));
                medie.add(media);
            }
        } catch (Exception ex) {
            Logger.getLogger(MediaBaza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medie;
    }

    private void snimiFajl(FileItem sadrzajFajla, String putanja) throws Exception {
        File fajl = new File(putanja);
        if (!fajl.getParentFile().isDirectory()) {
            fajl.getParentFile().mkdir();
        }
        sadrzajFajla.write(new File(putanja));
    }

    public ArrayList<Media> allWhereDogadjajId(int dogadjajId) {
        ArrayList<Media> medie = new ArrayList<>();
        try {
            String query = "SELECT * FROM media WHERE dogadjaj_id = ?";
            ArrayList<Object> vrednosti = new ArrayList();
            vrednosti.add(dogadjajId);

            Database db = Database.getInstance();
            ResultSet rs = db.select(query, vrednosti);

            while (rs.next()) {
                Media media = new Media();
                media.setId(rs.getInt("id"));
                media.setPutanja(rs.getString("putanja"));
                media.setGlavna(rs.getBoolean("glavna"));
                media.setTip(rs.getString("tip"));
                media.setDogadjajId(rs.getInt("dogadjaj_id"));
                media.setIme(rs.getString("ime"));
                medie.add(media);
            }
        } catch (Exception ex) {
            Logger.getLogger(MediaBaza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medie;
    }

}
