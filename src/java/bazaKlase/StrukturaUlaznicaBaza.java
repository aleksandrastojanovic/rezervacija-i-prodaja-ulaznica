/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazaKlase;

import baza.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modeli.Baza;
import modeli.StrukturaUlaznica;

/**
 *
 * @author iq skola
 */
public class StrukturaUlaznicaBaza implements Baza<StrukturaUlaznica>{
    @Override
    public StrukturaUlaznica save (StrukturaUlaznica strukturaUlaznica){
        int id = strukturaUlaznica.getId() > 0 ? update(strukturaUlaznica) : insert(strukturaUlaznica);
        return find(id);
    }
    
    private int insert(StrukturaUlaznica strukturaUlaznica){
        String query = "INSERT INTO strukture (id_dogadjaja,kategorija,cena,broj_dostupnih_ulaznica,preostalo_ulaznica,granica_po_korisniku) VALUES(?,?,?,?,?,?)";        
        ArrayList<Object> vrednosti = new ArrayList();
        
        vrednosti.add(strukturaUlaznica.getIdDogadjaja());
        vrednosti.add(strukturaUlaznica.getKategorija());
        vrednosti.add(strukturaUlaznica.getCena());
        vrednosti.add(strukturaUlaznica.getBrojDostupnihUlaznica());
        vrednosti.add(strukturaUlaznica.getPreostaloUlaznica());
        vrednosti.add(strukturaUlaznica.getGranicaPoKorisniku());
              
        Database db = Database.getInstance();
        int id = db.insert(query, vrednosti);
                               
        return id;
    }

    private int update(StrukturaUlaznica strukturaUlaznica) {
        
        String query = "UPDATE strukture SET id_dogadjaja = ?, kategorija = ?, cena = ?,"
                + " broj_dostupnih_ulaznica = ?, preostalo_ulaznica = ?, granica_po_korisniku = ? WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();
        vrednosti.add(strukturaUlaznica.getIdDogadjaja());
        vrednosti.add(strukturaUlaznica.getKategorija());
        vrednosti.add(strukturaUlaznica.getCena());
        vrednosti.add(strukturaUlaznica.getBrojDostupnihUlaznica()); 
        vrednosti.add(strukturaUlaznica.getPreostaloUlaznica());
        vrednosti.add(strukturaUlaznica.getGranicaPoKorisniku());
        vrednosti.add(strukturaUlaznica.getId());        
        
        Database db = Database.getInstance();
        boolean uspesno = db.update(query, vrednosti);     
               
        return uspesno ? strukturaUlaznica.getId() : -1;
        
    }

    @Override
    public StrukturaUlaznica find(int id) {
        StrukturaUlaznica strukturaUlaznica = new StrukturaUlaznica();
        try {
            
            String query = "SELECT * FROM `strukture` WHERE strukture.id = " + id;
            Database db = Database.getInstance();
            ResultSet rs = db.select(query);
            
            if (rs.first()) {
                strukturaUlaznica.setId(rs.getInt("id"));
                strukturaUlaznica.setIdDogadjaja(rs.getInt("id_dogadjaja"));
                strukturaUlaznica.setKategorija(rs.getString("kategorija"));
                strukturaUlaznica.setCena(rs.getDouble("cena"));
                strukturaUlaznica.setBrojDostupnihUlaznica(rs.getInt("broj_dostupnih_ulaznica"));
                strukturaUlaznica.setPreostaloUlaznica(rs.getInt("preostalo_ulaznica"));
                strukturaUlaznica.setGranicaPoKorisniku(rs.getInt("granica_po_korisniku"));
               
                                
            }
        } catch (SQLException ex) {
            Logger.getLogger(StrukturaUlaznicaBaza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return strukturaUlaznica;
        
    }
    
    @Override
    public boolean delete(StrukturaUlaznica strukturaUlaznica){
        String query = "DELETE FROM strukture WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();
        
        vrednosti.add(strukturaUlaznica.getId());
        
        Database db = Database.getInstance();
        boolean uspesno = db.delete(query, vrednosti);
                
        return uspesno;
    }
    
    public boolean delete(int id){
        String query = "DELETE FROM strukture WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();
        
        vrednosti.add(id);
        
        Database db = Database.getInstance();
        boolean uspesno = db.delete(query, vrednosti);
        
        return uspesno;
    }
    
    @Override
    public ArrayList<StrukturaUlaznica> all() {
        ArrayList<StrukturaUlaznica> strukture = new ArrayList<>();
        try {
             
            String query = "SELECT * " +
                    "FROM strukture";
            
            Database db = Database.getInstance();
            ResultSet rs = db.select(query);            
            
            while(rs.next()) {
                StrukturaUlaznica strukturaUlaznica = new StrukturaUlaznica();                            
                                               
                    strukturaUlaznica.setId(rs.getInt("id"));
                    strukturaUlaznica.setIdDogadjaja(rs.getInt("id_dogadjaja"));
                    strukturaUlaznica.setKategorija(rs.getString("kategorija"));
                    strukturaUlaznica.setCena(rs.getDouble("cena"));
                    strukturaUlaznica.setBrojDostupnihUlaznica(rs.getInt("broj_dostupnih_ulaznica"));
                    strukturaUlaznica.setPreostaloUlaznica(rs.getInt("preostalo_ulaznica"));
                    strukturaUlaznica.setGranicaPoKorisniku(rs.getInt("granica_po_korisniku"));
                    
                    strukture.add(strukturaUlaznica); 
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(StrukturaUlaznicaBaza.class.getName()).log(Level.SEVERE, null, ex);
        }

        return strukture;
    }
    
    public ArrayList<StrukturaUlaznica> allForDogadjajId(int dogadjajId) {
        ArrayList<StrukturaUlaznica> strukture = new ArrayList<>();
        try {
             
            String query = "SELECT * " +
                    "FROM strukture WHERE id_dogadjaja = " + dogadjajId;
            
            Database db = Database.getInstance();
            ResultSet rs = db.select(query);            
            
            while(rs.next()) {
                StrukturaUlaznica strukturaUlaznica = new StrukturaUlaznica();                            
                                               
                    strukturaUlaznica.setId(rs.getInt("id"));
                    strukturaUlaznica.setIdDogadjaja(rs.getInt("id_dogadjaja"));
                    strukturaUlaznica.setKategorija(rs.getString("kategorija"));
                    strukturaUlaznica.setCena(rs.getDouble("cena"));
                    strukturaUlaznica.setBrojDostupnihUlaznica(rs.getInt("broj_dostupnih_ulaznica"));
                    strukturaUlaznica.setPreostaloUlaznica(rs.getInt("preostalo_ulaznica"));
                    strukturaUlaznica.setGranicaPoKorisniku(rs.getInt("granica_po_korisniku"));
                    
                    strukture.add(strukturaUlaznica); 
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(StrukturaUlaznicaBaza.class.getName()).log(Level.SEVERE, null, ex);
        }

        return strukture;
    }
}
