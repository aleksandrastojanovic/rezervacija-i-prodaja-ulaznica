/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ProveraRegistrovanogKorisnikaServlet extends HttpServlet {

    private final RegistrovaniKorisnikBaza korisnikBaza = new RegistrovaniKorisnikBaza();
    private final RezervacijaBaza rezervacijaBaza = new RezervacijaBaza();

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
            RequestDispatcher rd = request.getRequestDispatcher("index");
            if (ProvereKorisnik.postojiPrijavljenKorisnik(request)) {
                response.sendRedirect("index");
                return;
            }
            int korisnikId = (int) sesija.getAttribute("korisnik_id");
            RegistrovaniKorisnik korisnik = korisnikBaza.find(korisnikId);
            LocalDateTime pre48h = LocalDateTime.now().minusDays(2);

            ArrayList<Rezervacija> sveRezervacije = rezervacijaBaza.all();
            ArrayList<Rezervacija> istekleRezervacije = new ArrayList<>();
            for (Rezervacija rezervacija : sveRezervacije) {
                if (pre48h.isEqual(rezervacija.getVreme().toLocalDateTime())
                        || pre48h.isBefore(rezervacija.getVreme().toLocalDateTime())) {
                    rezervacija.setStatus(Rezervacija.STATUS_ISTEKLO);
                    rezervacija = rezervacijaBaza.save(rezervacija);
                }
                if (rezervacija.getKorisnikId() == (int) sesija.getAttribute("korisnik_id")
                        && rezervacija.getStatus().equals(Rezervacija.STATUS_ISTEKLO)) {
                    istekleRezervacije.add(rezervacija);
                }
            }
            if (istekleRezervacije.size() >= 3) {
                korisnik.setTip(Korisnik.TIP_BLOKIRANI_KORISNIK);
                korisnik = korisnikBaza.save(korisnik);
            }
            if (!korisnik.getTip().equals(Korisnik.TIP_REGISTROVANI_KORISNIK)) {
                sesija.setAttribute("korisnik_id", -1);
            }
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ProveraRegistrovanogKorisnikaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
