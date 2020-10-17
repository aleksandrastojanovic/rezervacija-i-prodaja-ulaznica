/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
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
        HttpSession sesija = request.getSession();
        if (sesija.getAttribute("korisnik_id") == null || sesija.getAttribute("tip") == null) {
            response.sendRedirect("proveraPrijavljen");
            return;
        }

        String novaLozinka = request.getParameter("nova_lozinka");
        String novaLozinkaPotvrda = request.getParameter("nova_lozinka_potvrda");

        if (novaLozinka.equals(novaLozinkaPotvrda)) {
            String tip = sesija.getAttribute("tip").toString();
            int korisnikId = (int) sesija.getAttribute("korisnik_id");
            KorisnikBaza korisnikBaza = new KorisnikBaza();
            Korisnik korisnik;
            switch (tip) {
                case Korisnik.TIP_REGISTROVANI_KORISNIK:
                    RegistrovaniKorisnikBaza registrovaniKorisnikBaza = new RegistrovaniKorisnikBaza();
                    korisnik = registrovaniKorisnikBaza.find(korisnikId);
                    break;
                case Korisnik.TIP_BLAGAJNIK:
                    BlagajnikBaza blagajnikBaza = new BlagajnikBaza();
                    korisnik = blagajnikBaza.find(korisnikId);
                    break;
                case Korisnik.TIP_ADMINISTRATOR:

                    korisnik = korisnikBaza.find(korisnikId);
                    break;
                default:
                    response.sendRedirect("proveraPrijavljen");
                    return;

            }
            String lozinka = request.getParameter("lozinka");
            if (korisnik.getLozinka().equals(lozinka)) {
                korisnik.setLozinka(novaLozinka);
                korisnikBaza.save(korisnik);
                response.sendRedirect("index");
            } else {
                //poruka da se ne poklapaju sifre
            }
        } else {
            //poruka greska ne poklapa se nova lozinka i potvrda nove lozinke
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
