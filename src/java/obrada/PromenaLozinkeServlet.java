/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import klase.*;

/**
 *
 * @author iq skola
 */
public class PromenaLozinkeServlet extends HttpServlet {

    private final RegistrovaniKorisnikBaza registrovaniKorisnikBaza = new RegistrovaniKorisnikBaza();
    private final BlagajnikBaza blagajnikBaza = new BlagajnikBaza();
    private final AdministratorBaza administratorBaza = new AdministratorBaza();

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
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession sesija = request.getSession();
            if (!ProvereKorisnik.postojiPrijavljenKorisnik(request)) {
                response.sendRedirect("proveraPrijavljen");
                return;
            }

            String lozinka = request.getParameter("lozinka");
            String novaLozinka = request.getParameter("nova_lozinka");
            String novaLozinkaPotvrda = request.getParameter("nova_lozinka_potvrda");

            if (novaLozinka.equals(novaLozinkaPotvrda)) {
                String tip = sesija.getAttribute("tip").toString();
                int korisnikId = (int) sesija.getAttribute("korisnik_id");
                switch (tip) {
                    case Korisnik.TIP_REGISTROVANI_KORISNIK:
                        RegistrovaniKorisnik korisnik = registrovaniKorisnikBaza.find(korisnikId);
                        if (verifikujIIzmeniLozinku(korisnik, lozinka, novaLozinka)) {
                            registrovaniKorisnikBaza.save(korisnik);

                        } else {
                            //nije dobra stara lozinka
                        }
                        response.sendRedirect("index");
                        break;
                    case Korisnik.TIP_BLAGAJNIK:
                        Blagajnik blagajnik = blagajnikBaza.find(korisnikId);
                        if (verifikujIIzmeniLozinku(blagajnik, lozinka, novaLozinka)) {
                            blagajnikBaza.save(blagajnik);
                        }
                        response.sendRedirect("prijavljenBlagajnik");
                        break;
                    case Korisnik.TIP_ADMINISTRATOR:
                        Administrator administrator = administratorBaza.find(korisnikId);
                        if (verifikujIIzmeniLozinku(administrator, lozinka, novaLozinka)) {
                            administratorBaza.save(administrator);

                        }
                        response.sendRedirect("prijavljenAdministrator");
                        break;
                    default:
                        response.sendRedirect("proveraPrijavljen");
                        break;

                }

            } else {
                //poruka greska ne poklapa se nova lozinka i potvrda nove lozinke
            }
        } catch (Exception ex) {
            Logger.getLogger(PromenaLozinkeServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("error.jsp");
        }

    }

    private boolean verifikujIIzmeniLozinku(Korisnik korisnik, String lozinka, String novaLozinka) {
        if (BCrypt.verifyer().verify(lozinka.toCharArray(), korisnik.getLozinka()).verified) {
            String sifrovanaLozinka = BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, novaLozinka.toCharArray());
            korisnik.setLozinka(sifrovanaLozinka);
            return true;
        }
        return false;
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
