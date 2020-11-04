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
    private String kontaktTelefon;
    private String email;
    
    
    
    public RegistrovaniKorisnik(String ime, String prezime, String korisnickoIme, String lozinka, 
            String grad, String adresa, String kontaktTelefon, String email) {
        super(Korisnik.TIP_REGISTROVANI_KORISNIK, ime, prezime, korisnickoIme, lozinka, "registrovani_korisnici");
        this.grad = grad;
        this.adresa = adresa;
        this.kontaktTelefon = kontaktTelefon;
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

    public String getKontaktTelefon() {
        return kontaktTelefon;
    }

    public void setKontaktTelefon(String kontaktTelefon) {
        this.kontaktTelefon = kontaktTelefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
    
    
    
    
    
    
    
    
    
    
}
