/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import klase.*;

/**
 *
 * @author iq skola
 */
public class OdobravanjeZahtevaServlet extends HttpServlet {
    
    private final KorisnikBaza korisnikBaza = new KorisnikBaza();
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
            
            if (ProvereKorisnik.postojiPrijavljenKorisnik(request)) {
                return;
            }
            if (ProvereKorisnik.postojiPrijavljenKorisnikOdredjenogTipa(request, Korisnik.TIP_ADMINISTRATOR)) {
                Korisnik korisnik = korisnikBaza.find((int) request.getAttribute("korisnik_id"));
                if (Korisnik.TIP_BLOKIRANI_KORISNIK.equals(korisnik.getTip())) {
                    korisnik.setTip(Korisnik.TIP_REGISTROVANI_KORISNIK);
                    ArrayList<Rezervacija> sveRezervacije = rezervacijaBaza.all();
                    for (Rezervacija rezervacija : sveRezervacije) {
                        if (rezervacija.getKorisnikId() == korisnik.getId()
                                && Rezervacija.STATUS_ISTEKLO.equals(rezervacija.getStatus())) {
                            rezervacijaBaza.delete(rezervacija);
                        }
                    }
                    
                    korisnikBaza.save(korisnik);
                    
                }
                if (korisnik.getId() > 0) {
                    //poruka uspesno
                    response.sendRedirect("prijavljenAdministrator");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(OdobravanjeZahtevaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
