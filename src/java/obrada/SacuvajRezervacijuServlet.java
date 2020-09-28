/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import klase.Blagajnik;
import klase.BlagajnikBaza;
import klase.RegistrovaniKorisnik;
import klase.RegistrovaniKorisnikBaza;
import klase.Rezervacija;
import klase.RezervacijaBaza;
import klase.StrukturaUlaznica;
import klase.StrukturaUlaznicaBaza;

/**
 *
 * @author iq skola
 */
public class SacuvajRezervacijuServlet extends HttpServlet {

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
        RegistrovaniKorisnikBaza registrovaniKorisnikBaza = new RegistrovaniKorisnikBaza();
        RegistrovaniKorisnik registrovaniKorisnik = registrovaniKorisnikBaza.find((int)sesija.getAttribute("korisnik_id"));
        if (registrovaniKorisnik.getId() == -1) {
            response.sendRedirect("prijava.jsp");
            return;
        }
        
        RezervacijaBaza rezervacijaBaza = new RezervacijaBaza();
        Rezervacija rezervacija = new Rezervacija();
        String strukturaId = request.getParameter("kategorijja");
        //strukturaUlaznicaBaza.find((int)sesija.getAttribute("struktura_id"));
        if(request.getParameter("rezrvacija_id") != null){
        rezervacija = rezervacijaBaza.find(Integer.parseInt(request.getParameter("struktura_id")));
        }
        //ovde for petlja da prolazi kroz checkboxove? ili je to preko js?             
       /* ArrayList<>  = .all();
        ArrayList<>  = new <>();
        for (  : ){
            if()
        }
        
        if (strukturaId != null){
            
        }*/
        rezervacija.setDogadjaj_id(Integer.parseInt(request.getParameter("dogadjaj_id")));
        rezervacija.setKorisnik_id(Integer.parseInt("" + sesija.getAttribute("korisnik_id")));
        rezervacija.setStruktura_id(Integer.parseInt(request.getParameter("struktura_id")));
        rezervacija.setBroj_ulaznica(Integer.parseInt(request.getParameter("broj_ulaznica")));
        
      
        rezervacija = rezervacijaBaza.save(rezervacija);
        if (rezervacija.getId() > 0) {
            //uspesno dodata struktura
        } else {
            //poruka da nije uspesno
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
