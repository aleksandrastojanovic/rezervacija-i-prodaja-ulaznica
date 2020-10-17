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
    
    private int korisnikId;
    private String status;
    private Timestamp vreme;
    private int dogadjajId;
    private int brojUlaznica;
    private int strukturaId;

    public Rezervacija(int korisnikId,int dogadjajId, int brojUlaznica, int strukturaId) {
        this.korisnikId = korisnikId;
        this.dogadjajId = dogadjajId;
        this.brojUlaznica = brojUlaznica;
        this.strukturaId = strukturaId;
        this.id = -1;
        this.table = "rezervacije";
        this.status = STATUS_REZERVISANO;
    }
    
    public Rezervacija(){
        this.id = -1;
        this.table = "rezervacije";
        this.status = STATUS_REZERVISANO;
    }

    public int getKorisnikId() {
        return korisnikId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    public void setKorisnikId(int korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Timestamp getVreme() {
        return vreme;
    }

    public void setVreme(Timestamp vreme) {
        this.vreme = vreme;
    }

    public int getDogadjajId() {
        return dogadjajId;
    }

    public void setDogadjajId(int dogadjajId) {
        this.dogadjajId = dogadjajId;
    }

    public int getBrojUlaznica() {
        return brojUlaznica;
    }

    public void setBrojUlaznica(int brojUlaznica) {
        this.brojUlaznica = brojUlaznica;
    }

    public int getStrukturaId() {
        return strukturaId;
    }

    public void setStrukturaId(int strukturaId) {
        this.strukturaId = strukturaId;
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
