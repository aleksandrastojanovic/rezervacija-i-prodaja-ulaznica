/*
- vrsi provere i cuvanje novog korisnika u bazu
 */
package obrada;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import klase.*;

/**
 *
 * @author iq skola
 */
public class RegistracijaServlet extends HttpServlet {

    private final RegistrovaniKorisnikBaza registrovaniKorisnikBaza = new RegistrovaniKorisnikBaza();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            /* Ukoliko ne postoji korisnik sa unetim korisnickim imenom,
            pravi novog korisnika i smesta ga u bazu.
             */
 /*-Ocitava podatke iz forme registracija*
            -Postavlja tip korisnika na registrovani korisnik*
            -Prosledjuje ih adminu na odobravanje?
            - Prikazuje poruku da je zahtev uspesno prihvacen?
             */
            if (!ProvereKorisnik.postojiPrijavljenKorisnik(request)) {
                ArrayList<RegistrovaniKorisnik> korisnici = registrovaniKorisnikBaza.all();

                for (RegistrovaniKorisnik korisnik : korisnici) {
                    RegistrovaniKorisnik k = korisnik;
                    if (k.getKorisnickoIme().equals(request.getParameter("username"))) {

                        //poruka korisnicko ime vec postoji, pokusajte ponovo.
                        //vraca na registracija za sad,
                        //ne znam kako da se to radi i na frontu
                        String poruka = "Uneto korisnicko ime vec postoji.";
                        RequestDispatcher rd1 = request.getRequestDispatcher("registracija.jsp");
                        request.setAttribute("poruka", poruka);
                        rd1.forward(request, response);
                        return;
                    }

                }
                RegistrovaniKorisnik registrovaniKorisnik = new RegistrovaniKorisnik();

                registrovaniKorisnik.setIme(request.getParameter("ime"));
                registrovaniKorisnik.setPrezime(request.getParameter("prezime"));
                registrovaniKorisnik.setKorisnickoIme(request.getParameter("username"));
                /*ne znam da li ova provera treba ovde ili preko js */
                // Najbolje na oba mesta, svakako mora ovde
                // ako nije dobra mora da se vrati na registraciju opet, sa porukom da sifre nisu iste
                String lozinka = request.getParameter("password");
                if (lozinka.equals(request.getParameter("password_check"))) {
                    String sifrovanaLozinka = BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, lozinka.toCharArray());
                    registrovaniKorisnik.setLozinka(sifrovanaLozinka);

                }
                registrovaniKorisnik.setGrad(request.getParameter("grad"));
                registrovaniKorisnik.setAdresa(request.getParameter("adresa"));
                registrovaniKorisnik.setKontaktTelefon(request.getParameter("telefon"));
                if (request.getParameter("email").matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    registrovaniKorisnik.setEmail(request.getParameter("email"));
                } else {
                    String poruka = "Neispravno uneta e-mail adresa";
                    RequestDispatcher rd1 = request.getRequestDispatcher("registracija.jsp");
                    request.setAttribute("poruka", poruka);
                    rd1.forward(request, response);
                    return;
                }

                registrovaniKorisnik = registrovaniKorisnikBaza.save(registrovaniKorisnik);
                if (registrovaniKorisnik.getId() > 0) {
                    RequestDispatcher rd = request.getRequestDispatcher("prijava.jsp");
                    String porukaUspesno = "Uspesna registracija.";
                    request.setAttribute("porukaUspesno", porukaUspesno);
                    rd.forward(request, response);
                } else {
                    String poruka = "Neuspesna registracija. Molimo pokusajte ponovo";
                    RequestDispatcher rd1 = request.getRequestDispatcher("registracija.jsp");
                    request.setAttribute("poruka", poruka);
                    rd1.forward(request, response);
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(RegistracijaServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("error.jsp");
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
