/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

/**
 *
 * @author iq skola
 */
public class Blagajnik extends Korisnik {
    
    private String nazivLokacije;
    private String gradLokacije;
    private String adresaLokacije;

    public Blagajnik(String ime, String prezime, String korisnickoIme, String lozinka, 
            String nazivLokacije, String gradLokacije, String adresaLokacije) {
        super(Korisnik.TIP_BLAGAJNIK, ime, prezime, korisnickoIme, lozinka, "blagajnici");
        this.nazivLokacije = nazivLokacije;
        this.gradLokacije = gradLokacije;
        this.adresaLokacije = adresaLokacije;
    }

    public Blagajnik() {
        super("blagajnici", Korisnik.TIP_BLAGAJNIK);
    }

    public String getNazivLokacije() {
        return nazivLokacije;
    }

    public void setNazivLokacije(String nazivLokacije) {
        this.nazivLokacije = nazivLokacije;
    }

    public String getGradLokacije() {
        return gradLokacije;
    }

    public void setGradLokacije(String gradLokacije) {
        this.gradLokacije = gradLokacije;
    }

    public String getAdresaLokacije() {
        return adresaLokacije;
    }

    public void setAdresaLokacije(String adresaLokacije) {
        this.adresaLokacije = adresaLokacije;
    }

   
    
    
    
    
    
    
}
