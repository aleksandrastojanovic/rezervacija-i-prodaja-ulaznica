/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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
        /* Prolazi redom kroz korisnike, 
        i u zavisnosti od tipa korisnika prosledjuje na odgovarajuci prijavljenServlet.*/

        KorisnikBaza korisnikBaza = new KorisnikBaza();
        ArrayList<Korisnik> korisnici = korisnikBaza.all();

        for (Korisnik korisnik : korisnici) {
            if (korisnik.getKorisnicko_ime().equals(request.getParameter("username"))
                    && korisnik.getLozinka().equals(request.getParameter("password"))) {
                HttpSession sesija = request.getSession();
                String putanja = "";
                String tip = korisnik.getTip();
                sesija.setAttribute("tip", tip);
                int korisnikId = korisnik.getId();

                switch (tip) {
                    case Korisnik.TIP_REGISTROVANI_KORISNIK:
                        sesija.setAttribute("korisnik_id", korisnikId);
                        putanja = "proveraRegistrovanogKorisnika";
                        break;
                    case Korisnik.TIP_BLAGAJNIK:
                        sesija.setAttribute("korisnik_id", korisnikId);
                        putanja = "prijavljenBlagajnik";
                        break;
                    case Korisnik.TIP_ADMINISTRATOR:
                        sesija.setAttribute("korisnik_id", korisnikId);
                        putanja = "prijavljenAdministrator";
                        break;
                    case Korisnik.TIP_BLOKIRANI_KORISNIK:
                        sesija.setAttribute("korisnik_id", -1);
                        putanja = "index";
                        break;
                    //poruka da je blokiran ili drugi  jsp
                }
                RequestDispatcher rd = request.getRequestDispatcher(putanja);
                rd.forward(request, response);

            }
        }
        response.sendRedirect("proveraPrijavljen");
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
