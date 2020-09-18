/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
import java.util.ArrayList;
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
public class PrijavaServlet extends HttpServlet {

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
        /* Prolazi redom kroz registrovane korisnike, blagajnike i admine, 
        i u zavisnosti od tipa korisnika prosledjuje na odgovarajucu pocetnu stranicu.*/

        KorisnikBaza korisnikBaza = new KorisnikBaza();

//        RegistrovaniKorisnikBaza registrovaniKorisnikBaza = new RegistrovaniKorisnikBaza();
//        BlagajnikBaza blagajnikBaza = new BlagajnikBaza();
//        AdministratorBaza administratorBaza = new AdministratorBaza();
        ArrayList<Korisnik> korisnici = korisnikBaza.all();
//        ArrayList<Blagajnik> blagajnici = blagajnikBaza.all();
//        ArrayList<Administrator> administratori = administratorBaza.all();

        for (Korisnik korisnik : korisnici) {
            if (korisnik.getKorisnicko_ime().equals(request.getParameter("username"))
                    && korisnik.getLozinka().equals(request.getParameter("password"))) {
                HttpSession sesija = request.getSession();
                sesija.setAttribute("korisnik_id", korisnik.getId());
                switch (korisnik.getTip()) {
                    case Korisnik.TIP_REGISTROVANI_KORISNIK:
                        response.sendRedirect("prijavljenRegistrovaniKorisnik");
                        return;
                    case Korisnik.TIP_BLAGAJNIK:
                        response.sendRedirect("prijavljenBlagajnik");
                        return;
                    case Korisnik.TIP_ADMINISTRATOR:
                        response.sendRedirect("prijavljenAdministrator");
                        return;
                }
            }
        }

//        for (Blagajnik blagajnik : blagajnici) {
//            Blagajnik b = blagajnik;
//            if (b.getKorisnicko_ime().equals(request.getParameter("username"))
//                    && b.getLozinka().equals(request.getParameter("password"))) {
//                HttpSession sesija = request.getSession();
//                sesija.setAttribute("korisnik_id", b.getId());
//                response.sendRedirect("blagajnik_pocetna.jsp");
//                return;
//            }
//        }
//
//        for (Administrator administrator : administratori) {
//            Administrator a = administrator;
//            if (a.getKorisnicko_ime().equals(request.getParameter("username"))
//                    && a.getLozinka().equals(request.getParameter("password"))) {
//                HttpSession sesija = request.getSession();
//                sesija.setAttribute("korisnik_id", a.getId());
//                response.sendRedirect("admin_pocetna.jsp");
//                return;
//            }
//        }
        response.sendRedirect("prijava.jsp");
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
