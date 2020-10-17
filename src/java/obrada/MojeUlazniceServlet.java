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
public class MojeUlazniceServlet extends HttpServlet {

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
        if (sesija.getAttribute("korisnik_id") != null 
                && Korisnik.TIP_REGISTROVANI_KORISNIK.equals(sesija.getAttribute("tip"))){
            RequestDispatcher rd = request.getRequestDispatcher("moje_ulaznice.jsp");
            
            RezervacijaBaza rezervacijaBaza = new RezervacijaBaza();
            ArrayList<Rezervacija> sveRezervacije = rezervacijaBaza.all();
            ArrayList<Rezervacija> rezervacije = new ArrayList<>();
            for(Rezervacija rezervacija : sveRezervacije){
                if(rezervacija.getKorisnik_id() == (int)sesija.getAttribute("korisnik_id")){
                    rezervacije.add(rezervacija);
                }
            }
            request.setAttribute("rezervacije", rezervacije);
            rd.forward(request, response);
            
            /*DogadjajBaza dogadjajBaza = new DogadjajBaza();
            ArrayList<Dogadjaj> dogadjaji = dogadjajBaza.all();
            request.setAttribute("dogadjaji", dogadjaji);
            rd.forward(request, response);*/
        }
        else {
            response.sendRedirect("proveraPrijavljen");
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
