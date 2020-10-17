/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import klase.Korisnik;
import klase.*;
import klase.Rezervacija;
import klase.RezervacijaBaza;

/**
 *
 * @author iq skola
 */
public class ProveraRegistrovanogKorisnikaServlet extends HttpServlet {

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
        RequestDispatcher rd = request.getRequestDispatcher("index");
        if (sesija.getAttribute("korisnik_id") == null) {
            response.sendRedirect("index");
            return;
        }
        int korisnik_id = (int)sesija.getAttribute("korisnik_id");
        RegistrovaniKorisnikBaza korisnikBaza = new RegistrovaniKorisnikBaza();
        RegistrovaniKorisnik korisnik = korisnikBaza.find(korisnik_id);
        LocalDateTime pre48h = LocalDateTime.now().minusDays(2);
        
        RezervacijaBaza rezervacijaBaza = new RezervacijaBaza();
        ArrayList<Rezervacija> sveRezervacije = rezervacijaBaza.all();
        ArrayList<Rezervacija> istekleRezervacije = new ArrayList<>();
        for(Rezervacija rezervacija : sveRezervacije){
            if(pre48h.isEqual(rezervacija.getVreme().toLocalDateTime()) 
                    || pre48h.isBefore(rezervacija.getVreme().toLocalDateTime())){
                rezervacija.setStatus(Rezervacija.STATUS_ISTEKLO);
                rezervacija = rezervacijaBaza.save(rezervacija);
            }
            if(rezervacija.getKorisnik_id() == (int)sesija.getAttribute("korisnik_id")
                    && rezervacija.getStatus().equals(Rezervacija.STATUS_ISTEKLO)){
                istekleRezervacije.add(rezervacija);
            }
        }
        if(istekleRezervacije.size() >= 3){
            korisnik.setTip(Korisnik.TIP_BLOKIRANI_KORISNIK);
            korisnik = korisnikBaza.save(korisnik);
        }
        if(!korisnik.getTip().equals(Korisnik.TIP_REGISTROVANI_KORISNIK)){
            sesija.setAttribute("korisnik_id", -1);
        } 
        rd.forward(request, response);
        
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
