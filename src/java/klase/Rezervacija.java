/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klase;

import java.sql.Timestamp;

/**
 *
 * @author iq skola
 */
public class Rezervacija implements Model {
    protected int id;
    private final String table;
    
    public static final String STATUS_REZERVISANO = "rezervisano";
    public static final String STATUS_PLACENO = "placeno";
    public static final String STATUS_ISTEKLO = "isteklo";
    
    private int korisnik_id;
    private String status;
    private Timestamp vreme;
    private int dogadjaj_id;
    private int broj_ulaznica;
    private int struktura_id;

    public Rezervacija(int korisnik_id,int dogadjaj_id, int broj_ulaznica, int struktura_id) {
        this.korisnik_id = korisnik_id;
        this.dogadjaj_id = dogadjaj_id;
        this.broj_ulaznica = broj_ulaznica;
        this.struktura_id = struktura_id;
        this.id = -1;
        this.table = "rezervacije";
        this.status = STATUS_REZERVISANO;
    }
    
    public Rezervacija(){
        this.id = -1;
        this.table = "rezervacije";
        this.status = STATUS_REZERVISANO;
    }

    public int getKorisnik_id() {
        return korisnik_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    public void setKorisnik_id(int korisnik_id) {
        this.korisnik_id = korisnik_id;
    }

    public Timestamp getVreme() {
        return vreme;
    }

    public void setVreme(Timestamp vreme) {
        this.vreme = vreme;
    }

    public int getDogadjaj_id() {
        return dogadjaj_id;
    }

    public void setDogadjaj_id(int dogadjaj_id) {
        this.dogadjaj_id = dogadjaj_id;
    }

    public int getBroj_ulaznica() {
        return broj_ulaznica;
    }

    public void setBroj_ulaznica(int broj_ulaznica) {
        this.broj_ulaznica = broj_ulaznica;
    }

    public int getStruktura_id() {
        return struktura_id;
    }

    public void setStruktura_id(int struktura_id) {
        this.struktura_id = struktura_id;
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
