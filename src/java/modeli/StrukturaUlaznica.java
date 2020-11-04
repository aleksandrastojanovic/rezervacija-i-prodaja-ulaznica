/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import klase.Model;

/**
 *
 * @author iq skola
 */
public class StrukturaUlaznica implements Model {
    protected int id;
    private final String table;
    
    private int idDogadjaja;
    private String kategorija;
    private double cena;
    private int brojDostupnihUlaznica;
    private int preostaloUlaznica;
    private int granicaPoKorisniku;

    public StrukturaUlaznica(int idDogadjaja, String kategorija, double cena, int brojDostupnihUlaznica, int granicaPoKorisniku) {
        this.idDogadjaja = idDogadjaja;
        this.kategorija = kategorija;
        this.cena = cena;
        this.brojDostupnihUlaznica = brojDostupnihUlaznica;
        this.id = -1;
        this.table = "strukture";
        this.preostaloUlaznica = this.brojDostupnihUlaznica;
        this.granicaPoKorisniku = granicaPoKorisniku;
    }
    

    public StrukturaUlaznica() {
        this.id = -1;
        this.table = "strukture";
    }

    public int getIdDogadjaja() {
        return idDogadjaja;
    }

    public void setIdDogadjaja(int idDogadjaja) {
        this.idDogadjaja = idDogadjaja;
    }

    public int getPreostaloUlaznica() {
        return preostaloUlaznica;
    }

    public void setPreostaloUlaznica(int preostaloUlaznica) {
        this.preostaloUlaznica = preostaloUlaznica;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getBrojDostupnihUlaznica() {
        return brojDostupnihUlaznica;
    }

    public void setBrojDostupnihUlaznica(int brojDostupnihUlaznica) {
        this.brojDostupnihUlaznica = brojDostupnihUlaznica;
    }

    public int getGranicaPoKorisniku() {
        return granicaPoKorisniku;
    }

    public void setGranicaPoKorisniku(int granicaPoKorisniku) {
        this.granicaPoKorisniku = granicaPoKorisniku;
    }
        
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTable() {
        return table;
    }
    
    
    
}
