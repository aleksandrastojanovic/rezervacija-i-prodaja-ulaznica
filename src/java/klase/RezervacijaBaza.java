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
public class RezervacijaBaza implements Baza<Rezervacija>{
    @Override
    public Rezervacija save (Rezervacija rezervacija){
        int id = rezervacija.getId() > 0 ? update(rezervacija) : insert(rezervacija);
        return find(id);
    }
    
    private int insert(Rezervacija rezervacija){
        String query = "INSERT INTO rezervacije (korisnik_id,dogadjaj_id,broj_ulaznica,struktura_id,status) VALUES(?,?,?,?,?)";        
        ArrayList<Object> vrednosti = new ArrayList();
        
        vrednosti.add(rezervacija.getKorisnikId());
        vrednosti.add(rezervacija.getDogadjajId());
        vrednosti.add(rezervacija.getBrojUlaznica());
        vrednosti.add(rezervacija.getStrukturaId());
        vrednosti.add(rezervacija.getStatus());
        
              
        Database db = Database.getInstance();
        int id = db.insert(query, vrednosti);
                               
        return id;
    }

    private int update(Rezervacija rezervacija) {
        
        String query = "UPDATE rezervacije SET korisnik_id = ?, dogadjaj_id = ?,"
                + " broj_ulaznica = ?, struktura_id = ?, status = ? WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();
        vrednosti.add(rezervacija.getKorisnikId());
        vrednosti.add(rezervacija.getDogadjajId());
        vrednosti.add(rezervacija.getBrojUlaznica());
        vrednosti.add(rezervacija.getStrukturaId());
        vrednosti.add(rezervacija.getId());
        vrednosti.add(rezervacija.getStatus());
        
        Database db = Database.getInstance();
        boolean uspesno = db.update(query, vrednosti);     
               
        return uspesno ? rezervacija.getId() : -1;
        
    }

    @Override
    public Rezervacija find(int id) {
        Rezervacija rezervacija = new Rezervacija();
        try {
            
            String query = "SELECT * FROM `rezervacije` WHERE rezervacije.id = " + id;
            Database db = Database.getInstance();
            ResultSet rs = db.select(query);
            
            if (rs.first()) {
                rezervacija.setId(rs.getInt("id"));
                rezervacija.setKorisnikId(rs.getInt("korisnik_id"));
                //isto, nisam sigurna
                rezervacija.setVreme(rs.getTimestamp("vreme"));
                rezervacija.setDogadjajId(rs.getInt("dogadjaj_id"));
                rezervacija.setBrojUlaznica(rs.getInt("broj_ulaznica"));
                rezervacija.setStrukturaId(rs.getInt("struktura_id"));
                rezervacija.setStatus(rs.getString("status"));
               
                                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RezervacijaBaza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rezervacija;
        
    }
    
    @Override
    public boolean delete(Rezervacija rezervacija){
        String query = "DELETE FROM rezervacije WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();
        
        vrednosti.add(rezervacija.getId());
        
        Database db = Database.getInstance();
        boolean uspesno = db.delete(query, vrednosti);
                
        return uspesno;
    }
    
    public boolean delete(int id){
        String query = "DELETE FROM rezervacije WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();
        
        vrednosti.add(id);
        
        Database db = Database.getInstance();
        boolean uspesno = db.delete(query, vrednosti);
        
        return uspesno;
    }
    
    @Override
    public ArrayList<Rezervacija> all() {
        ArrayList<Rezervacija> rezervacije = new ArrayList<>();
        try {
             
            String query = "SELECT * " +
                    "FROM rezervacije";
            
            Database db = Database.getInstance();
            ResultSet rs = db.select(query);            
            
            while(rs.next()) {
                Rezervacija rezervacija = new Rezervacija();                            
                                               
                    rezervacija.setId(rs.getInt("id"));
                    rezervacija.setKorisnikId(rs.getInt("korisnik_id"));
                    //isto, nisam sigurna
                    rezervacija.setVreme(rs.getTimestamp("vreme"));
                    rezervacija.setDogadjajId(rs.getInt("dogadjaj_id"));
                    rezervacija.setBrojUlaznica(rs.getInt("broj_ulaznica"));
                    rezervacija.setStrukturaId(rs.getInt("struktura_id"));  
                    rezervacija.setStatus(rs.getString("status"));
                    
                    rezervacije.add(rezervacija); 
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RezervacijaBaza.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rezervacije;
    }
}
