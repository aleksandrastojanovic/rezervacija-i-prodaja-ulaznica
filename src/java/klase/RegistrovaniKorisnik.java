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
public class RegistrovaniKorisnik extends Korisnik {
        
    private String grad;
    private String adresa;
    private String kontakt_telefon;
    private String email;
    
    
    
    public RegistrovaniKorisnik(String ime, String prezime, String korisnicko_ime, String lozinka, 
            String grad, String adresa, String kontakt_telefon, String email) {
        super(Korisnik.TIP_REGISTROVANI_KORISNIK, ime, prezime, korisnicko_ime, lozinka, "registrovani_korisnici");
        this.grad = grad;
        this.adresa = adresa;
        this.kontakt_telefon = kontakt_telefon;
        this.email = email;
    }

    public RegistrovaniKorisnik() {
        super("registrovani_korisnici", Korisnik.TIP_REGISTROVANI_KORISNIK);
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getKontakt_telefon() {
        return kontakt_telefon;
    }

    public void setKontakt_telefon(String kontakt_telefon) {
        this.kontakt_telefon = kontakt_telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
    
    
    
    
    
    
    
    
    
    
}
