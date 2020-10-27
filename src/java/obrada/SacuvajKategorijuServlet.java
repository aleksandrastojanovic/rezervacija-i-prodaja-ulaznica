/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
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
public class SacuvajKategorijuServlet extends HttpServlet {

    private final StrukturaUlaznicaBaza strukturaUlaznicaBaza = new StrukturaUlaznicaBaza();

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
            if (!ProvereKorisnik.postojiPrijavljenKorisnikOdredjenogTipa(request, Korisnik.TIP_BLAGAJNIK)) {
                response.sendRedirect("proveraPrijavljen");
                return;
            }

            StrukturaUlaznica struktura = new StrukturaUlaznica();
            if (request.getParameter("struktura_id") != null) {
                struktura = strukturaUlaznicaBaza.find(Integer.parseInt(request.getParameter("struktura_id")));
                struktura.setId(Integer.parseInt(request.getParameter("struktura_id")));
            }

            struktura.setIdDogadjaja(Integer.parseInt(request.getParameter("dogadjaj_id")));
            struktura.setKategorija(request.getParameter("kategorija"));
            struktura.setCena(Double.parseDouble(request.getParameter("cena")));
            struktura.setBrojDostupnihUlaznica(Integer.parseInt(request.getParameter("broj_ulaznica")));
            struktura.setPreostaloUlaznica(struktura.getBrojDostupnihUlaznica());
            struktura.setGranicaPoKorisniku(Integer.parseInt(request.getParameter("granica_po_korisniku")));

            struktura = strukturaUlaznicaBaza.save(struktura);
            if (struktura.getId() > 0) {
                response.sendRedirect("dogadjajPojedinacno?dogadjaj_id=" + struktura.getIdDogadjaja());
            } else {
                //poruka da nije uspesno
            }
        } catch (Exception ex) {
            Logger.getLogger(SacuvajKategorijuServlet.class.getName()).log(Level.SEVERE, null, ex);
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
