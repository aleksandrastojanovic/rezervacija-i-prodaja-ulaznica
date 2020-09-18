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
public class StrukturaUlaznicaBaza implements Baza<StrukturaUlaznica>{
    @Override
    public StrukturaUlaznica save (StrukturaUlaznica strukturaUlaznica){
        int id = strukturaUlaznica.getId() > 0 ? update(strukturaUlaznica) : insert(strukturaUlaznica);
        return find(id);
    }
    
    private static int insert(StrukturaUlaznica strukturaUlaznica){
        String query = "INSERT INTO strukture (id_dogadjaja,kategorija,cena,broj_dostupnih_ulaznica) VALUES(?,?,?,?)";        
        ArrayList<Object> vrednosti = new ArrayList();
        
        vrednosti.add(strukturaUlaznica.getId_dogadjaja());
        vrednosti.add(strukturaUlaznica.getKategorija());
        vrednosti.add(strukturaUlaznica.getCena());
        vrednosti.add(strukturaUlaznica.getBroj_dostupnih_ulaznica());       
              
        Database db = Database.getInstance();
        int id = db.insert(query, vrednosti);
                               
        return id;
    }

    private static int update(StrukturaUlaznica strukturaUlaznica) {
        
        String query = "UPDATE strukture SET id_dogadjaja = ?, kategorija = ?, cena = ?,"
                + " broj_dostupnih_ulaznica = ? WHERE id = ?";
        ArrayList<Object> vrednosti = new ArrayList();
        vrednosti.add(strukturaUlaznica.getId_dogadjaja());
        vrednosti.add(strukturaUlaznica.getKategorija());
        vrednosti.add(strukturaUlaznica.getCena());
        vrednosti.add(strukturaUlaznica.getBroj_dostupnih_ulaznica());        
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
                strukturaUlaznica.setId_dogadjaja(rs.getInt("id_dogadjaja"));
                strukturaUlaznica.setKategorija(rs.getString("kategorija"));
                strukturaUlaznica.setCena(rs.getDouble("cena"));
                strukturaUlaznica.setBroj_dostupnih_ulaznica(rs.getInt("broj_dostupnih_ulaznica"));
               
                                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrovaniKorisnikBaza.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public static boolean delete(int id){
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
                    strukturaUlaznica.setId_dogadjaja(rs.getInt("id_dogadjaja"));
                    strukturaUlaznica.setKategorija(rs.getString("kategorija"));
                    strukturaUlaznica.setCena(rs.getDouble("cena"));
                    strukturaUlaznica.setBroj_dostupnih_ulaznica(rs.getInt("broj_dostupnih_ulaznica"));                                                                                         
                    
                    strukture.add(strukturaUlaznica); 
                
            }
        } catch(Exception e) {
            System.out.println(e.toString());
        }

        return strukture;
    }
}
