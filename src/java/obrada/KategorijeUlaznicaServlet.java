/*
        - proverava da li je blagajnik
        - Uzima strukture za dogadjaj iz baze i prosledjuje ih na kategorije_ulaznica.jsp
 */
package obrada;

import modeli.StrukturaUlaznica;
import modeli.Korisnik;
import bazaKlase.StrukturaUlaznicaBaza;
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
public class KategorijeUlaznicaServlet extends HttpServlet {

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
            if (!ProvereKorisnik.postojiPrijavljenKorisnik(request)) {
                String poruka = "Morate biti prijavljeni kako biste pristupili stranici.";
                RequestDispatcher rd = request.getRequestDispatcher("prijava.jsp");
                request.setAttribute("poruka", poruka);
                rd.forward(request, response);
                return;
            }
            if (ProvereKorisnik.postojiPrijavljenKorisnikOdredjenogTipa(request, Korisnik.TIP_BLAGAJNIK)) {
                int dogadjajId = Integer.parseInt(request.getParameter("dogadjaj_id"));

                if (dogadjajId > 0) {
                    RequestDispatcher rd = request.getRequestDispatcher("kategorije_ulaznica.jsp");
                    ArrayList<StrukturaUlaznica> strukture = strukturaUlaznicaBaza.allForDogadjajId(dogadjajId);
                    request.setAttribute("strukture", strukture);
                    request.setAttribute("dogadjaj_id", dogadjajId);
                    if (request.getAttribute("poruka") != null) {
                        request.setAttribute("poruka", request.getAttribute("poruka"));
                    }
                    if (request.getAttribute("porukaUspesno") != null) {
                        request.setAttribute("porukaUspesno", request.getAttribute("porukaUspesno"));
                    }
                    rd.forward(request, response);
                }

            } else {
                String poruka = "Morate biti prijavljen blagajnik kako biste pristupili stranici.";
                RequestDispatcher rd = request.getRequestDispatcher("prijava.jsp");
                request.setAttribute("poruka", poruka);
                rd.forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(KategorijeUlaznicaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
