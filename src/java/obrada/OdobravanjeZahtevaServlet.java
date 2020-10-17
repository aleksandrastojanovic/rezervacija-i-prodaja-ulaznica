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
public class OdobravanjeZahtevaServlet extends HttpServlet {

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
        if (sesija.getAttribute("korisnik_id") == null) {
            return;
        }
        if(Korisnik.TIP_ADMINISTRATOR.equals(sesija.getAttribute("tip"))){
        KorisnikBaza korisnikBaza = new KorisnikBaza();
        Korisnik korisnik = korisnikBaza.find((int) request.getAttribute("korisnik_id"));
        if (Korisnik.TIP_BLOKIRANI_KORISNIK.equals(korisnik.getTip())) {
            korisnik.setTip(Korisnik.TIP_REGISTROVANI_KORISNIK);
            RezervacijaBaza rezervacijaBaza = new RezervacijaBaza();
            ArrayList<Rezervacija> sveRezervacije = rezervacijaBaza.all();
            for(Rezervacija rezervacija : sveRezervacije){
                if(rezervacija.getKorisnik_id() == korisnik.getId() 
                        && Rezervacija.STATUS_ISTEKLO.equals(rezervacija.getStatus())){
                    rezervacijaBaza.delete(rezervacija);
                }
            }
            
            korisnikBaza.save(korisnik);
            
        }
        if(korisnik.getId() > 0){
            //poruka uspesno
            response.sendRedirect("prijavljenAdministrator");
        }
        
        /*
        -Za admin korisnika
        -Prima objekat iz RegistracijaServlet-a
        -Smesta napravljeni objekat u bazu
         */

 /*Ovaj deo se valjda izvrsava u OdobravanjeZahtevaServlet:
        -Uz izmene klase na RegistrovaniKorisnik*/
 /*korisnik = (Korisnik)korisnik.save();
        if (korisnik.getId() > 0) {
            response.sendRedirect("proveraPrijavljen");
        } else {
            response.sendRedirect("proveraRegistrovan");
        }*/
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
