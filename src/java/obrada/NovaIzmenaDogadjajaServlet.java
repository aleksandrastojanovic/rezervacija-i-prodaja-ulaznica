/*
- dozvoljava blagajniku da pristupi blagajnik_update.jsp
 */
package obrada;

import modeli.StrukturaUlaznica;
import modeli.Korisnik;
import modeli.Dogadjaj;
import bazaKlase.StrukturaUlaznicaBaza;
import bazaKlase.DogadjajBaza;
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
public class NovaIzmenaDogadjajaServlet extends HttpServlet {

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
        try {
            if (!ProvereKorisnik.postojiPrijavljenKorisnikOdredjenogTipa(request, Korisnik.TIP_BLAGAJNIK)) {
                String poruka = "Morate biti prijavljen blagajnik kako biste pristupili stranici.";
                RequestDispatcher rd1 = request.getRequestDispatcher("prijava.jsp");
                request.setAttribute("poruka", poruka);
                rd1.forward(request, response);
                return;
            }
            int dogadjajId = Integer.parseInt(request.getParameter("dogadjaj_id"));
            DogadjajBaza dogadjajBaza = new DogadjajBaza();
            Dogadjaj dogadjaj = dogadjajBaza.find(dogadjajId);
            RequestDispatcher rd = request.getRequestDispatcher("blagajnik_update.jsp");
            request.setAttribute("dogadjaj", dogadjaj);
            StrukturaUlaznicaBaza strukturaUlaznicaBaza = new StrukturaUlaznicaBaza();
            ArrayList<StrukturaUlaznica> strukture = strukturaUlaznicaBaza.allForDogadjajId(dogadjajId);
            request.setAttribute("strukture", strukture);
            if (request.getAttribute("poruka") != null) {
                request.setAttribute("poruka", request.getAttribute("poruka"));
            }
            if (request.getAttribute("porukaUspesno") != null) {
                request.setAttribute("porukaUspesno", request.getAttribute("porukaUspesno"));
            }
            rd.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(NovaIzmenaDogadjajaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
