/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klase;

/**
 *
 * @author iq skola
 */
public class StrukturaUlaznica implements Model {
    protected int id;
    private final String table;
    
    private int id_dogadjaja;
    private String kategorija;
    private double cena;
    private int broj_dostupnih_ulaznica;

    public StrukturaUlaznica(int id_dogadjaja, String kategorija, double cena, int broj_dostupnih_ulaznica) {
        this.id_dogadjaja = id_dogadjaja;
        this.kategorija = kategorija;
        this.cena = cena;
        this.broj_dostupnih_ulaznica = broj_dostupnih_ulaznica;
        this.id = -1;
        this.table = "strukture";
    }

    public StrukturaUlaznica() {
        this.id = -1;
        this.table = "strukture";
    }

    public int getId_dogadjaja() {
        return id_dogadjaja;
    }

    public void setId_dogadjaja(int id_dogadjaja) {
        this.id_dogadjaja = id_dogadjaja;
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

    public int getBroj_dostupnih_ulaznica() {
        return broj_dostupnih_ulaznica;
    }

    public void setBroj_dostupnih_ulaznica(int broj_dostupnih_ulaznica) {
        this.broj_dostupnih_ulaznica = broj_dostupnih_ulaznica;
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
