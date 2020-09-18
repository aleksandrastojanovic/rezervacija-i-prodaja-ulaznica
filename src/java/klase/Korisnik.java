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
public class Korisnik implements Model{
         
    public static final String TIP_REGISTROVANI_KORISNIK = "Registrovani korisnik";
    public static final String TIP_BLAGAJNIK = "Blagajnik";
    public static final String TIP_ADMINISTRATOR = "Administrator";
    
    protected int id = -1;
    protected String table;
    protected String tip;
    protected String ime;
    protected String prezime;
    protected String korisnicko_ime;
    protected String lozinka;
    
    // mozda treba da imaju status i timestamp
    public Korisnik(String tip, String ime, String prezime, String korisnicko_ime,
                    String lozinka, String table) {
        
        this.tip = tip;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnicko_ime = korisnicko_ime;
        this.lozinka = lozinka;
        this.table = table;
        
    }
    
    
    public Korisnik(String table, String tip) {
        this.table = table;
        this.tip = tip;
    }
    
        
    public String getTip() {
        return tip;
    }
    
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnicko_ime() {
        return korisnicko_ime;
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        this.korisnicko_ime = korisnicko_ime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
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
    public String toString() {
        return id + " " + ime + " " + prezime;
    }

    @Override
    public String getTable() {
        return table;
    }
}
