/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
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
public class KupovinaUlaznicaServlet extends HttpServlet {

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
        RequestDispatcher rd = request.getRequestDispatcher("blagajnik_prodaja.jsp");
        /*
        -Ocitava podatke iz blagajnik_prodaja
        -Proverava vrstu kupovine - ovo u jspu bira, i svakako dolazi na ovaj servlet
        -Na osnovu kategorije ulaznica, skida sa 'stanja'
        -Ako je bila rezervisana, menja status u njegovim ulaznicama
        */
        if(sesija.getAttribute("korisnik_id") == null || (int)sesija.getAttribute("korisnik_id") < 0){
            response.sendRedirect("proveraPrijavljen");
            return;
        }
        if(Korisnik.TIP_BLAGAJNIK.equals(sesija.getAttribute("tip"))){ 
//        DogadjajBaza dogadjajBaza = new DogadjajBaza();        
//        Dogadjaj dogadjaj = dogadjajBaza.find(Integer.parseInt(request.getParameter("dogadjaj_id")));
        
        RezervacijaBaza rezervacijaBaza = new RezervacijaBaza();
        Rezervacija rezervacija = rezervacijaBaza.find(Integer.parseInt(request.getParameter("rezervacija_id")));
        if(!rezervacija.getStatus().equals(Rezervacija.STATUS_ISTEKLO)){
            rezervacija.setStatus(Rezervacija.STATUS_PLACENO);
            StrukturaUlaznicaBaza strukturaUlaznicaBaza = new StrukturaUlaznicaBaza();
            StrukturaUlaznica struktura = strukturaUlaznicaBaza.find(rezervacija.getStrukturaId());
            struktura.setPreostaloUlaznica(struktura.getPreostaloUlaznica() - rezervacija.getBrojUlaznica());

            strukturaUlaznicaBaza.save(struktura);
            rezervacijaBaza.save(rezervacija);
        }
        response.sendRedirect("prijavljenBlagajnik");
        //salje na novi jsp uspesna kupovina+dugme za povratak/nije moguca kupovina
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
