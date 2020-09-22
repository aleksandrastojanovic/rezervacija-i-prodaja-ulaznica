/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import klase.Blagajnik;
import klase.BlagajnikBaza;
import klase.Dogadjaj;
import klase.DogadjajBaza;
import klase.StrukturaUlaznica;
import klase.StrukturaUlaznicaBaza;

/**
 *
 * @author iq skola
 */
public class IzmenaStruktureServlet extends HttpServlet {

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
        RequestDispatcher rd = request.getRequestDispatcher("izmena_strukture.jsp");
        BlagajnikBaza blagajnikBaza = new BlagajnikBaza();
        Blagajnik blagajnik = blagajnikBaza.find((int)sesija.getAttribute("korisnik_id"));
        if(blagajnik.getId() == -1){
            response.sendRedirect("index.jsp");
            return;
        }
        
        StrukturaUlaznicaBaza strukturaUlaznicaBaza = new StrukturaUlaznicaBaza();        
        StrukturaUlaznica struktura = strukturaUlaznicaBaza.find(Integer.parseInt(request.getParameter("struktura_id")));
        
        request.setAttribute("korisnik_id", sesija.getAttribute("korisnik_id"));
        //request.setAttribute("strukture", strukture);
        request.setAttribute("struktura", struktura);
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
