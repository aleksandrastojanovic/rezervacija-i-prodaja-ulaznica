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
public class Administrator extends Korisnik {
        
    public Administrator(String ime, String prezime, String korisnickoIme, String lozinka) {
        super(Korisnik.TIP_ADMINISTRATOR, ime, prezime, korisnickoIme, lozinka, "korisnici");
    }

    public Administrator() {
        super("korisnici", Korisnik.TIP_ADMINISTRATOR);
    }
    
    
    
    
    
}
