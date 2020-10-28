/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import klase.*;

/**
 *
 * @author iq skola
 */
@WebServlet(name = "IzmenaDogadjajaServlet", urlPatterns = {"/izmenaDogadjaja"})
public class IzmenaDogadjajaServlet extends HttpServlet {

    private final DogadjajBaza dogadjajBaza = new DogadjajBaza();

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
            if (!ProvereKorisnik.postojiPrijavljenKorisnik(request)) {
                String poruka = "Morate biti prijavljen blagajnik kako biste pristupili stranici.";
                RequestDispatcher rd = request.getRequestDispatcher("prijava.jsp");
                request.setAttribute("poruka", poruka);
                rd.forward(request, response);
                return;
            }
            if (ProvereKorisnik.postojiPrijavljenKorisnikOdredjenogTipa(request, Korisnik.TIP_BLAGAJNIK)) {
                RequestDispatcher rd = request.getRequestDispatcher("prijavljenBlagajnik");
                
                Dogadjaj dogadjaj = dogadjajBaza.find(Integer.parseInt(request.getParameter("dogadjaj_id")));
                
                dogadjaj.setNaziv(request.getParameter("naziv"));
                dogadjaj.setNazivLokacije(request.getParameter("naziv_lokacije"));
                dogadjaj.setDatumIVreme(LocalDateTime.parse(request.getParameter("vreme_odrzavanja")));
                dogadjaj.setDetalji(request.getParameter("detalji"));
                dogadjajBaza.save(dogadjaj);
                
                request.setAttribute("dogadjaj", dogadjaj);
                rd.forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(IzmenaDogadjajaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
