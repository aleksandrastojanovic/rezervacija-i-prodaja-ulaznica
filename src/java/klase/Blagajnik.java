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
public class Blagajnik extends Korisnik {
    
    private String naziv_lokacije;
    private String grad_lokacije;
    private String adresa_lokacije;

    public Blagajnik(String ime, String prezime, String korisnicko_ime, String lozinka, 
            String naziv_lokacije, String grad_lokacije, String adresa_lokacije) {
        super(Korisnik.TIP_BLAGAJNIK, ime, prezime, korisnicko_ime, lozinka, "blagajnici");
        this.naziv_lokacije = naziv_lokacije;
        this.grad_lokacije = grad_lokacije;
        this.adresa_lokacije = adresa_lokacije;
    }

    public Blagajnik() {
        super("blagajnici", Korisnik.TIP_BLAGAJNIK);
    }

    public String getNaziv_lokacije() {
        return naziv_lokacije;
    }

    public void setNaziv_lokacije(String naziv_lokacije) {
        this.naziv_lokacije = naziv_lokacije;
    }

    public String getGrad_lokacije() {
        return grad_lokacije;
    }

    public void setGrad_lokacije(String grad_lokacije) {
        this.grad_lokacije = grad_lokacije;
    }

    public String getAdresa_lokacije() {
        return adresa_lokacije;
    }

    public void setAdresa_lokacije(String adresa_lokacije) {
        this.adresa_lokacije = adresa_lokacije;
    }

   
    
    
    
    
    
    
}
