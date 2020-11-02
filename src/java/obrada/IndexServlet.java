/*
        - paginacija i prosledjivanje atributa na index.jsp
 */
package obrada;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import klase.*;

/**
 *
 * @author iq skola
 */
public class IndexServlet extends HttpServlet {

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

            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");

            int trenutniBrojStrane;
            if (request.getParameter("trenutniBrojStrane") != null) {
                trenutniBrojStrane = Integer.parseInt(request.getParameter("trenutniBrojStrane"));
            } else {
                trenutniBrojStrane = 1;
            }
            int grupa = (trenutniBrojStrane - 1) * 9 + 1;
            int ukupnoStrana = dogadjajBaza.all().size() / 9;
            if (dogadjajBaza.all().size() % 9 != 0) {
                ukupnoStrana = ukupnoStrana + 1;
            }
            ArrayList<Dogadjaj> dogadjaji = new ArrayList<>();
            if (ukupnoStrana >= trenutniBrojStrane) {
                dogadjaji = dogadjajBaza.allForPage(grupa);
            } else {
                response.sendRedirect("error.jsp");
            }
            request.setAttribute("dogadjaji", dogadjaji);
            request.setAttribute("ukupnoStrana", ukupnoStrana);
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(IndexServlet.class.getName()).log(Level.SEVERE, null, ex);
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
