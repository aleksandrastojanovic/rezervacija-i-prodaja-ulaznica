/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klase;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author iq skola
 */
public class ProvereKorisnik {
    

    public static boolean postojiPrijavljenKorisnik(HttpServletRequest request) throws Exception {
        Object korisnikId = request.getSession().getAttribute("korisnik_id");
        Object tip = request.getSession().getAttribute("tip");
        if(korisnikId == null || tip == null) {
            throw new Exception("Doslo je do greske. Molimo pokusajte ponovo.");
        }
        return ((Integer) korisnikId) >= 0 && !((String) tip).equals(Korisnik.TIP_NEREGISTROVANI_KORISNIK);
    }

    public static boolean postojiPrijavljenKorisnikOdredjenogTipa(HttpServletRequest request, String tip) throws Exception {
        return tip != null && postojiPrijavljenKorisnik(request) && tip.equals(request.getSession().getAttribute("tip"));
    }
}
